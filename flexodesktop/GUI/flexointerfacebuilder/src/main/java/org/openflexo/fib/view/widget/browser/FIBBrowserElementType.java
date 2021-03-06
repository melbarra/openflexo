/*
 * (c) Copyright 2010-2011 AgileBirds
 *
 * This file is part of OpenFlexo.
 *
 * OpenFlexo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * OpenFlexo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenFlexo. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openflexo.fib.view.widget.browser;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.Icon;

import org.openflexo.antar.binding.AbstractBinding.BindingEvaluationContext;
import org.openflexo.antar.binding.AbstractBinding.TargetObject;
import org.openflexo.antar.binding.BindingVariable;
import org.openflexo.fib.controller.FIBController;
import org.openflexo.fib.model.DataBinding;
import org.openflexo.fib.model.FIBAttributeNotification;
import org.openflexo.fib.model.FIBBrowserElement;
import org.openflexo.fib.model.FIBBrowserElement.FIBBrowserElementChildren;
import org.openflexo.fib.view.widget.FIBBrowserWidget;
import org.openflexo.localization.FlexoLocalization;

public class FIBBrowserElementType implements BindingEvaluationContext, Observer
{

	private static final Logger logger = Logger.getLogger(FIBBrowserElementType.class.getPackage().getName());


    private FIBBrowserModel fibBrowserModel;
    private FIBBrowserElement browserElementDefinition;
    private boolean isFiltered = false;
    
	private FIBController controller;
    
    public FIBBrowserElementType(FIBBrowserElement browserElementDefinition, FIBBrowserModel browserModel, FIBController controller)
    {
        super();
        this.controller = controller;
        this.fibBrowserModel = browserModel;
        this.browserElementDefinition = browserElementDefinition;
 
        browserElementDefinition.addObserver(this);
    }
    
	public void delete()
	{
		if (browserElementDefinition != null)
			browserElementDefinition.deleteObserver(this);

        this.controller = null;
        this.browserElementDefinition = null;
 	}

	public FIBBrowserModel getBrowserModel()
	{
		return fibBrowserModel;
	}
	
	public FIBBrowserWidget getBrowserWidget()
	{
		return getBrowserModel().getBrowserWidget();
	}
	
    @Override
	public void update(Observable o, Object arg)
    {
    	if (arg instanceof FIBAttributeNotification && o == browserElementDefinition) {
    		FIBAttributeNotification dataModification = (FIBAttributeNotification)arg;
    		((FIBBrowserWidget)controller.viewForComponent(browserElementDefinition.getBrowser())).updateBrowser();
    	}
    }

    public FIBController getController() 
    {
		return controller;
	}

	public String getLocalized(String key)
	{
		return FlexoLocalization.localizedForKey(getController().getLocalizer(),key);
	}

    protected void setModel(FIBBrowserModel model)
    {
        fibBrowserModel = model;
    }

    protected FIBBrowserModel getModel()
    {
        return fibBrowserModel;
    }

    /*public Object elementAt(int row)
    {
        return fibBrowserModel.elementAt(row);
    }*/

    private void appendToDependingObjects(DataBinding binding, List<TargetObject> returned)
    {
       	if (binding.isSet()) {
    		List<TargetObject> list = binding.getBinding().getTargetObjects(this);
    		if (list != null) 
    			for (TargetObject t : list) 
    				if (!returned.contains(t)) returned.add(t);
    	}
    }
    
    public List<TargetObject> getDependingObjects(final Object object)
    {
    	if (browserElementDefinition == null) return null;
    	iteratorObject = object;
    	List<TargetObject> returned = new ArrayList<TargetObject>();
    	appendToDependingObjects(browserElementDefinition.getLabel(),returned);
    	appendToDependingObjects(browserElementDefinition.getIcon(),returned);
    	appendToDependingObjects(browserElementDefinition.getTooltip(),returned);
    	appendToDependingObjects(browserElementDefinition.getEnabled(),returned);
    	appendToDependingObjects(browserElementDefinition.getVisible(),returned);
      	for (FIBBrowserElementChildren children : browserElementDefinition.getChildren()) {
      		appendToDependingObjects(children.getData(),returned);
     	}
      	return returned;
    }
    
    public synchronized String getLabelFor(final Object object)
    {
 	   if (browserElementDefinition == null) return "???"+object.toString();
    	if (browserElementDefinition.getLabel().isSet()) {
    		iteratorObject = object;
    		return (String)browserElementDefinition.getLabel().getBindingValue(this);
    	}
    	return object.toString();
    }

    public synchronized String getTooltipFor(final Object object)
    {
    	if (browserElementDefinition == null) return "???"+object.toString();
    	if (browserElementDefinition.getTooltip().isSet()) {
    		iteratorObject = object;
    		return (String)browserElementDefinition.getTooltip().getBindingValue(this);
    	}
    	return browserElementDefinition.getName();
    }

   public synchronized Icon getIconFor(final Object object)
   {
	   if (browserElementDefinition == null) return null;
	   	   if (browserElementDefinition.getIcon().isSet()) {
		   iteratorObject = object;
		   return (Icon)browserElementDefinition.getIcon().getBindingValue(this);
	   }
	   else {
		   return browserElementDefinition.getImageIcon();
	   }
   }

   public synchronized boolean isEnabled(final Object object)
   {
	   if (browserElementDefinition == null) return false;
	   if (browserElementDefinition.getEnabled().isSet()) {
		   iteratorObject = object;
		   Object enabledValue = browserElementDefinition.getEnabled().getBindingValue(this);
		   if (enabledValue != null) {
			   return (Boolean)enabledValue;
		   }
		   return true;
	   }
	   else {
		   return true;
	   }
   }

   public synchronized boolean isVisible(final Object object)
   {
	   if (browserElementDefinition == null) return false;
	   if (isFiltered()) return false;
	   if (browserElementDefinition.getVisible().isSet()) {
		   iteratorObject = object;
		   return (Boolean)browserElementDefinition.getVisible().getBindingValue(this);
	   }
	   else {
		   return true;
	   }
   }

   private Vector<?> EMPTY_LIST = new Vector();
   
   public synchronized List<?> getChildrenFor(final Object object)
   {
	   if (browserElementDefinition == null) return EMPTY_LIST;
	   List returned = new ArrayList();
	   for (FIBBrowserElementChildren children : browserElementDefinition.getChildren()) {
		   if (children.isMultipleAccess()) {
			   //System.out.println("add all children for "+browserElementDefinition.getName()+" children "+children.getName()+" data="+children.getData());
			   //System.out.println("Obtain "+getChildrenListFor(children, object));
			   List<?> childrenObjects = getChildrenListFor(children, object);
			   // Might be null if some visibility was declared
			   if (childrenObjects != null) returned.addAll(childrenObjects);
		   }
		   else {
			   //System.out.println("add children for "+browserElementDefinition.getName()+" children "+children.getName()+" data="+children.getData());
			   //System.out.println("Obtain "+getChildrenFor(children, object));
			   //System.out.println("accessed type="+children.getAccessedType());
			   Object childrenObject = getChildrenFor(children, object);
			   // Might be null if some visibility was declared
			   if (childrenObject != null) returned.add(childrenObject);
		   }
	   }
	   return returned;
   }


   protected synchronized Object getChildrenFor(FIBBrowserElementChildren children, final Object object)
   {
	   if (children.getData().isSet()) {
		   iteratorObject = object;
		   if (children.getVisible().isSet()) {
			   boolean visible = (Boolean)children.getVisible().getBindingValue(this);
			   if (!visible) {
				   // Finally we dont want to see it
				   return null;
			   }
		   }
		   return children.getData().getBindingValue(this);
	   }
	   else {
		   return null;
	   }
   }

   protected synchronized List<?> getChildrenListFor(FIBBrowserElementChildren children, final Object object)
   {
	   if (children.getData().isSet() && children.isMultipleAccess()) {
		   iteratorObject = object;
		   if (children.getVisible().isSet()) {
			   boolean visible = (Boolean)children.getVisible().getBindingValue(this);
			   if (!visible) {
				   // Finally we dont want to see it
				   return null;
			   }
		   }
		   return (List<?>)children.getData().getBindingValue(this);
	   }
	   else {
		   return null;
	   }
   }

   public boolean isLabelEditable()
   {
	   return getBrowserElement().getIsEditable()
			&& getBrowserElement().getEditableLabel().isSet()
			&& getBrowserElement().getEditableLabel().getBinding().isSettable();
   }

   public synchronized String getEditableLabelFor(final Object object)
   {
	   if (isLabelEditable()) {
		   iteratorObject = object;
		   return (String)browserElementDefinition.getEditableLabel().getBindingValue(this);
	   }
	   return object.toString();
   }

   public synchronized void setEditableLabelFor(final Object object, String value)
   {
	   if (isLabelEditable()) {
		   iteratorObject = object;
		   browserElementDefinition.getEditableLabel().setBindingValue(value,this);
	   }
   }
 
   protected Object iteratorObject;
    
    @Override
	public synchronized Object getValue(BindingVariable variable) 
    {
		if (variable.getVariableName().equals(browserElementDefinition.getName())) return iteratorObject;
		else if (variable.getVariableName().equals("object")) return iteratorObject;
		else return getController().getValue(variable);
    }
    
    public FIBBrowserElement getBrowserElement()
	{
		return browserElementDefinition;
	}

	public Font getFont()
	{
		if (getBrowserElement() != null)
			return getBrowserElement().retrieveValidFont();
		return null;
	}

    public boolean isFiltered() 
    {
		return isFiltered;
	}

    public void setFiltered(boolean isFiltered)
    {
    	System.out.println("Element "+getBrowserElement().getName()+" filtered: "+isFiltered);
    	if (this.isFiltered != isFiltered) {
    		this.isFiltered = isFiltered;
    		// Later, try to implement a way to rebuild tree with same expanded nodes
    		fibBrowserModel.fireTreeRestructured();
    	}
	}
    

}
