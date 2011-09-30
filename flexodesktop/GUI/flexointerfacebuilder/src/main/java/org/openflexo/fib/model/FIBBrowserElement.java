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
package org.openflexo.fib.model;

import java.awt.Font;
import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.openflexo.antar.binding.Bindable;
import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.antar.binding.BindingModel;
import org.openflexo.fib.model.FIBBrowserAction.FIBAddAction;
import org.openflexo.fib.model.FIBBrowserAction.FIBCustomAction;
import org.openflexo.fib.model.FIBBrowserAction.FIBRemoveAction;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.ModelEntity;


@ModelEntity
public interface FIBBrowserElement extends FIBModelObject {

	public static enum Parameters implements FIBModelAttribute
	{
		dataClass,
		label,
		icon,
		tooltip,
		enabled,
		visible,
		imageIconFile,
		isEditable,
		editableLabel,
		font,
		filtered,
		defaultVisible,
		children,
		actions
	}

	public static final String DATA_CLASS = "dataClass";
	public static final String LABEL = "label";
	public static final String ICON = "icon";
	public static final String TOOLTIP = "tooltip";
	public static final String ENABLED = "enabled";
	public static final String VISIBLE = "visible";
	public static final String IMAGE_ICON_FILE = "imageIconFile";
	public static final String EDITABLE = "ditable";
	public static final String EDITABLE_LABEL = "editableLabel";
	public static final String FONT = "font";
	public static final String FILTERED = "filtered";
	public static final String DEFAULT_VISIBLE = "defaultVisible";
	public static final String CHILDREN = "children";
	public static final String ACTIONS = "actions";
	public static final String BROWSER = "browser";

	public static final BindingDefinition LABEL_BD = new BindingDefinition("label", String.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition ICON_BD = new BindingDefinition("icon", Icon.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition TOOLTIP_BD = new BindingDefinition("tooltip", String.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition ENABLED_BD = new BindingDefinition("enabled", Boolean.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition VISIBLE_BD = new BindingDefinition("visible", Boolean.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition EDITABLE_LABEL_BD = new BindingDefinition("editableLabel", String.class,
			BindingDefinitionType.GET_SET, false);

	@Getter(id=BROWSER, inverse=FIBBrowser.)
	public FIBBrowser getBrowser();

	public void setBrowser(FIBBrowser browser);

	@Override
	public FIBComponent getRootComponent();

	public DataBinding getLabel();

	public void setLabel(DataBinding label);

	public DataBinding getIcon();

	public void setIcon(DataBinding icon);

	public DataBinding getTooltip();

	public void setTooltip(DataBinding tooltip);

	public DataBinding getEnabled();

	public void setEnabled(DataBinding enabled);

	public DataBinding getVisible();

	public void setVisible(DataBinding visible);

	public boolean getIsEditable();

	public void setIsEditable(boolean isEditable);

	public DataBinding getEditableLabel();

	public void setEditableLabel(DataBinding editableLabel);

	public boolean getFiltered();

	public void setFiltered(boolean filtered);

	public boolean getDefaultVisible();

	public void setDefaultVisible(boolean defaultVisible);

	public void finalizeBrowserDeserialization();

	public BindingModel getActionBindingModel();

	public void notifiedBindingModelRecreated();

	public Font retrieveValidFont();

	public Font getFont();

	public void setFont(Font font);

	public boolean getHasSpecificFont();

	public void setHasSpecificFont(boolean aFlag);

	public File getImageIconFile();

	public void setImageIconFile(File imageIconFile);

	public ImageIcon getImageIcon();

	public FIBBrowserElementIterator getIterator();

	public Class getDataClass();

	public void setDataClass(Class dataClass);

	public List<FIBBrowserAction> getActions();

	public void setActions(List<FIBBrowserAction> actions);

	public void addToActions(FIBBrowserAction anAction);

	public void removeFromActions(FIBBrowserAction anAction);

	public FIBAddAction createAddAction();

	public FIBRemoveAction createRemoveAction();

	public FIBCustomAction createCustomAction();

	public FIBBrowserAction deleteAction(FIBBrowserAction actionToDelete);

	public List<FIBBrowserElementChildren> getChildren();

	public void setChildren(List<FIBBrowserElementChildren> children);

	public void addToChildren(FIBBrowserElementChildren aChildren);

	public void removeFromChildren(FIBBrowserElementChildren aChildren);

	public FIBBrowserElementChildren createChildren();

	public FIBBrowserElementChildren deleteChildren(FIBBrowserElementChildren elementToDelete);

	public void moveToTop(FIBBrowserElementChildren e);

	public void moveUp(FIBBrowserElementChildren e);

	public void moveDown(FIBBrowserElementChildren e);

	public void moveToBottom(FIBBrowserElementChildren e);

	@ModelEntity
	public interface FIBBrowserElementIterator extends FIBModelObject, Bindable
	{

	}

	@ModelEntity
	public interface FIBBrowserElementChildren extends FIBModelObject
	{
		public static enum Parameters implements FIBModelAttribute
		{
			data,
			visible
		}

		public FIBBrowser getBrowser();

		public DataBinding getData();

		public void setData(DataBinding data);

		public DataBinding getVisible();

		public void setVisible(DataBinding visible);

		public FIBBrowserElement getBrowserElement();

		public void setBrowserElement(FIBBrowserElement browserElement);

		public void finalizeBrowserDeserialization();

		public ImageIcon getImageIcon();

		public Type getAccessedType();

		public boolean isMultipleAccess();

		public Class getBaseClass();
	}


}
