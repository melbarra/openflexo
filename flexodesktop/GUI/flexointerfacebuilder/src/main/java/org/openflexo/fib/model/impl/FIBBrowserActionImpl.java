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
package org.openflexo.fib.model.impl;

import java.util.logging.Logger;

import org.openflexo.antar.binding.BindingModel;
import org.openflexo.fib.model.DataBinding;
import org.openflexo.fib.model.FIBBrowserAction;
import org.openflexo.fib.model.FIBBrowserElement;
import org.openflexo.fib.model.FIBComponent;


public abstract class FIBBrowserActionImpl extends FIBModelObjectImpl implements FIBBrowserAction {

	private static final Logger logger = Logger.getLogger(FIBBrowserAction.class.getPackage().getName());

	@Override
	public FIBBrowserElement getBrowserElement()
	{
		return element;
	}

	public void setBrowserElement(FIBBrowserElement element)
	{
		this.element = element;
	}

	@Override
	public FIBComponent getRootComponent()
	{
		if (getBrowserElement() != null) {
			return getBrowserElement().getRootComponent();
		}
		return null;
	}

	@Override
	public DataBinding getMethod()
	{
		if (method == null) {
			method = new DataBinding(this,Parameters.method,METHOD);
		}
		return method;
	}

	@Override
	public void setMethod(DataBinding method)
	{
		method.setOwner(this);
		method.setBindingAttribute(Parameters.method);
		method.setBindingDefinition(METHOD);
		this.method = method;
	}

	@Override
	public DataBinding getIsAvailable()
	{
		if (isAvailable == null) {
			isAvailable = new DataBinding(this,Parameters.isAvailable,IS_AVAILABLE);
		}
		return isAvailable;
	}

	@Override
	public void setIsAvailable(DataBinding isAvailable)
	{
		isAvailable.setOwner(this);
		isAvailable.setBindingAttribute(Parameters.isAvailable);
		isAvailable.setBindingDefinition(IS_AVAILABLE);
		this.isAvailable = isAvailable;
	}

	@Override
	public BindingModel getBindingModel()
	{
		if (getBrowserElement() != null) {
			return getBrowserElement().getActionBindingModel();
		}
		return null;
	}

	@Override
	public void finalizeDeserialization()
	{
		logger.fine("finalizeDeserialization() for FIBTableAction "+getName());
		super.finalizeDeserialization();
		if (method != null) {
			method.finalizeDeserialization();
		}
	}

	@Override
	public abstract ActionType getActionType();

	public static class FIBAddAction extends FIBBrowserAction {

		@Override
		public ActionType getActionType()
		{
			return ActionType.Add;
		}
	}

	public static class FIBRemoveAction extends FIBBrowserAction {

		@Override
		public ActionType getActionType()
		{
			return ActionType.Delete;
		}
	}

	public static class FIBCustomAction extends FIBBrowserAction {

		public boolean isStatic = false;

		@Override
		public ActionType getActionType()
		{
			return ActionType.Custom;
		}
	}


}