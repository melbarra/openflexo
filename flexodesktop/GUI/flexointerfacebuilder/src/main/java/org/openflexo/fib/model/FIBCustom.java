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

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Vector;

import javax.swing.JComponent;

import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.antar.binding.BindingModel;
import org.openflexo.fib.controller.FIBController;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.swing.CustomPopup.ApplyCancelListener;

@ModelEntity
public interface FIBCustom extends FIBWidget {

	public static enum Parameters implements FIBModelAttribute
	{
		componentClass,
		assignments
	}

	public static final String COMPONENT_NAME = "component";


	public static interface FIBCustomComponent<T,C extends JComponent>
	{
		@Documented
		@Retention(RetentionPolicy.RUNTIME)
		public @interface CustomComponentParameter {
			/** name of parameter */
			String name();
			/** type of parameter */
			Type  type();
			/** Enumeration of different types */
			public static enum Type { MANDATORY, OPTIONAL };
		}

		public C getJComponent();

		public T getEditedObject();

		public void setEditedObject(T object);

		public T getRevertValue();

		public void setRevertValue(T object);

		public void addApplyCancelListener(ApplyCancelListener l);

		public void removeApplyCancelListener(ApplyCancelListener l);

		public Class<T> getRepresentedType();

		public void init(FIBCustom component, FIBController controller);

	}

	public Class getComponentClass();

	public void setComponentClass(Class componentClass);

	public boolean hasAssignment(String variableName);

	public FIBCustomAssignment getAssignent(String variableName);

	public Vector<FIBCustomAssignment> getAssignments();

	public void setAssignments(Vector<FIBCustomAssignment> assignments);

	public void addToAssignments(FIBCustomAssignment a);

	public void removeFromAssignments(FIBCustomAssignment a);

	public BindingModel getCustomComponentBindingModel();


	public FIBCustomAssignment createAssignment();

	public FIBCustomAssignment deleteAssignment(FIBCustomAssignment assignment);


	@ModelEntity
	public static interface FIBCustomAssignment extends FIBModelObject
	{
		public static final BindingDefinition VARIABLE = new BindingDefinition("variable", Object.class, BindingDefinitionType.GET_SET,
				true);
		public BindingDefinition VALUE = new BindingDefinition("value", Object.class, BindingDefinitionType.GET, true);

		public static enum Parameters implements FIBModelAttribute
		{
			variable,
			value
		}

		public boolean isMandatory();

		public FIBCustom getCustom();

		public void setVariable(DataBinding variable);

		public DataBinding getValue();

		public void setValue(DataBinding value);

	}

}
