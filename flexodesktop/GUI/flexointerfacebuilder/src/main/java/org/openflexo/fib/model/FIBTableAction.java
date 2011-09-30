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

import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.model.annotations.ModelEntity;


@ModelEntity(isAbstract = true)
public interface FIBTableAction extends FIBModelObject {

	public static enum Parameters implements FIBModelAttribute
	{
		method,
		isAvailable
	}

	public static enum ActionType
	{
		Add,
		Delete,
		Custom
	}

	public static final BindingDefinition METHOD = new BindingDefinition("method", Object.class, BindingDefinitionType.EXECUTE, false);
	public static final BindingDefinition IS_AVAILABLE = new BindingDefinition("isAvailable", Boolean.class, BindingDefinitionType.GET,
			false);

	public FIBTable getTable();

	public void setTable(FIBTable table);

	public DataBinding getMethod();

	public void setMethod(DataBinding method);

	public DataBinding getIsAvailable();

	public void setIsAvailable(DataBinding isAvailable);

	public ActionType getActionType();

	@ModelEntity
	public interface FIBAddAction extends FIBTableAction {
	}

	@ModelEntity
	public interface FIBRemoveAction extends FIBTableAction {

	}

	@ModelEntity
	public interface FIBCustomAction extends FIBTableAction {

	}

}
