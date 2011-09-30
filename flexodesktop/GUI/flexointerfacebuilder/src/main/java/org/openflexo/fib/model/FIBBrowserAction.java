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
import org.openflexo.antar.binding.BindingModel;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.ModelEntity;

@ModelEntity(isAbstract = true)
public interface FIBBrowserAction extends FIBModelObject {

	public static final String BROWSER_ELEMENT = "browserElement";

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
	public static final BindingDefinition IS_AVAILABLE = new BindingDefinition("isAvailable", Boolean.class, BindingDefinitionType.EXECUTE,
			false);

	@Getter(id = BROWSER_ELEMENT, inverse=FIBBrowserElement.)
	public FIBBrowserElement getBrowserElement();

	public void setBrowserElement(FIBBrowserElement element);



	public DataBinding getMethod();

	public void setMethod(DataBinding method);

	public DataBinding getIsAvailable();

	public void setIsAvailable(DataBinding isAvailable);

	@Override
	public BindingModel getBindingModel();

	public abstract ActionType getActionType();

	@ModelEntity
	public static interface FIBAddAction extends FIBBrowserAction {

	}

	@ModelEntity
	public static interface FIBRemoveAction extends FIBBrowserAction {

	}

	@ModelEntity
	public static interface FIBCustomAction extends FIBBrowserAction {

	}


}
