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

import java.util.Vector;

import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.antar.binding.BindingModel;
import org.openflexo.model.annotations.ModelEntity;

@ModelEntity
public interface FIBCustomColumn extends FIBTableColumn {

	public static enum Parameters implements FIBModelAttribute
	{
		componentClass,
		assignments,
		customRendering,
		disableTerminateEditOnFocusLost
	}

	public Class<?> getComponentClass();

	public void setComponentClass(Class componentClass);

	public boolean hasAssignment(String variableName);

	public FIBCustomAssignment getAssignent(String variableName);

	public Vector<FIBCustomAssignment> getAssignments();

	public void setAssignments(Vector<FIBCustomAssignment> assignments);

	public void addToAssignments(FIBCustomAssignment p);

	public void removeFromAssignments(FIBCustomAssignment p);

	public BindingModel getCustomComponentBindingModel();

	@ModelEntity
	public interface FIBCustomAssignment extends FIBModelObject
	{
		public static final BindingDefinition VARIABLE = new BindingDefinition("variable", Object.class, BindingDefinitionType.GET_SET,
				true);

		public static enum Parameters implements FIBModelAttribute
		{
			variable,
			value
		}

		public boolean isMandatory();

		public FIBCustomColumn getCustomColumn();

		public DataBinding getVariable();

		public void setVariable(DataBinding variable);

		public DataBinding getValue();

		public void setValue(DataBinding value);

	}

}
