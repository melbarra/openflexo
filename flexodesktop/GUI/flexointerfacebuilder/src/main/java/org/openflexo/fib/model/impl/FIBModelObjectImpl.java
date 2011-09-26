package org.openflexo.fib.model.impl;

import org.openflexo.antar.binding.BindingFactory;
import org.openflexo.antar.binding.BindingModel;
import org.openflexo.fib.FIBLibrary;
import org.openflexo.fib.model.FIBModelObject;
import org.openflexo.fib.model.FIBParameter;

public abstract class FIBModelObjectImpl implements FIBModelObject {
	@Override
	public String getParameter(String parameterName) {
		for (FIBParameter p : getParameters()) {
			if (parameterName.equals(p.getName())) {
				return p.getValue();
			}
		}
		return null;
	}

	@Override
	public FIBParameter createNewParameter() {
		FIBParameter returned = new FIBParameter("param", "value");
		addToParameters(returned);
		System.out.println("getParameters()=" + getParameters());
		return returned;
	}

	@Override
	public void deleteParameter(FIBParameter p) {
		removeFromParameters(p);
	}

	@Override
	public boolean isParameterAddable() {
		return true;
	}

	@Override
	public boolean isParameterDeletable(FIBParameter p) {
		return true;
	}

	@Override
	public BindingModel getBindingModel() {
		if (getRootComponent() != null && getRootComponent() != this) {
			return getRootComponent().getBindingModel();
		}
		return null;
	}

	@Override
	public BindingFactory getBindingFactory() {
		return FIBLibrary.instance().getBindingFactory();
	}

}
