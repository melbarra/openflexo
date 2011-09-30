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

import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

import org.openflexo.antar.binding.AbstractBinding;
import org.openflexo.antar.binding.AbstractBinding.BindingEvaluationContext;
import org.openflexo.antar.binding.BindingFactory;
import org.openflexo.antar.expr.Expression;
import org.openflexo.antar.expr.Function;
import org.openflexo.antar.expr.TypeMismatchException;
import org.openflexo.antar.expr.Variable;
import org.openflexo.antar.expr.parser.ParseException;
import org.openflexo.fib.model.DataBinding;
import org.openflexo.fib.model.FIBAttributeNotification;
import org.openflexo.fib.model.FIBComponent;
import org.openflexo.fib.model.FIBComponent.DependancyLoopException;
import org.openflexo.fib.model.FIBModelObject;
import org.openflexo.fib.model.FIBWidget;
import org.openflexo.model.factory.AccessibleProxyObject;
import org.openflexo.toolbox.StringUtils;


public abstract class DataBindingImpl implements DataBinding, AccessibleProxyObject
{

	private static final Logger logger = Logger.getLogger(FIBComponent.class.getPackage().getName());

	@Override
	public Object execute(BindingEvaluationContext context)
	{
		return getBindingValue(context);
	}

	@Override
	public Object getBindingValue(BindingEvaluationContext context)
	{
		//logger.info("getBindingValue() "+this);
		if (getBinding() != null) {
			return getBinding().getBindingValue(context);
		}
		return null;
	}

	@Override
	public void setBindingValue(Object value, BindingEvaluationContext context)
	{
		if (getBinding() != null && getBinding().isSettable()) {
			getBinding().setBindingValue(value,context);
		}
	}

	@Override
	public String toString()
	{
		if (getBinding() != null) {
			return getBinding().getStringRepresentation();
		}
		return getUnparsedBinding();
	}

	@Override
	public AbstractBinding getBinding() {
		if (performSuperGetter(BINDING) == null) {
			finalizeDeserialization();
		}
		return (AbstractBinding) performSuperGetter(BINDING);
	}

	@Override
	public AbstractBinding getBinding(boolean silentMode)
	{
		if (performSuperGetter(BINDING) == null) {
			finalizeDeserialization(silentMode);
		}
		return (AbstractBinding) performSuperGetter(BINDING);
	}

	/*public void setBinding(AbstractBinding binding)
	{
		this.binding = binding;
	}*/

	@Override
	public void setBinding(AbstractBinding value)
	{
		AbstractBinding oldValue = this.binding;
		if (oldValue == null) {
			if (value == null) {
				return; // No change
			} else {
				this.binding = value;
				unparsedBinding = value != null ? value.getStringRepresentation() : null;
				updateDependancies();
				if (bindingAttribute != null) {
					owner.notify(new FIBAttributeNotification<AbstractBinding>(bindingAttribute,oldValue,value));
				}
				getOwner().notifyBindingChanged(this);
				return;
			}
		}
		else {
			if (oldValue.equals(value)) {
				return; // No change
			} else {
				this.binding = value;
				unparsedBinding = value != null ? value.getStringRepresentation() : null;
				logger.info("Binding takes now value "+value);
				updateDependancies();
				if (bindingAttribute != null) {
					owner.notify(new FIBAttributeNotification<AbstractBinding>(bindingAttribute,oldValue,value));
				}
				owner.notifyBindingChanged(this);
				return;
			}
		}
	}

	@Override
	public boolean hasBinding()
	{
		return binding != null;
	}

	@Override
	public boolean isValid()
	{
		return getBinding() != null && getBinding().isBindingValid();
	}

	@Override
	public boolean isValid(boolean silentMode) {
		return getBinding(silentMode) != null && getBinding(silentMode).isBindingValid();
	}

	@Override
	public boolean isSet()
	{
		return unparsedBinding != null || binding != null;
	}

	@Override
	public boolean isUnset()
	{
		return unparsedBinding == null && binding == null;
	}

	@Override
	public String getUnparsedBinding() {
		return unparsedBinding;
	}

	@Override
	public void setUnparsedBinding(String unparsedBinding) {
		this.unparsedBinding = unparsedBinding;
		binding = null;
	}

	@Override
	public FIBModelObject getOwner() {
		return owner;
	}

	@Override
	public void setOwner(FIBModelObject owner)
	{
		this.owner = owner;
	}

	@Override
	public void finalizeDeserialization(boolean silentMode)
	{
		if (getUnparsedBinding() == null) {
			return;
		}

		/*if (getUnparsedBinding().equals("data.isAcceptableAsSubProcess(SubProcess.component.candidateValue)")) {
			System.out.println("finalizeDeserialization for "+getUnparsedBinding());
			System.out.println("Owner: "+getOwner()+" of "+getOwner().getClass());
			System.out.println("BindingModel: "+getOwner().getBindingModel());
		}*/

		// System.out.println("BindingModel: "+getOwner().getBindingModel());
		if (getOwner() != null) {
			BindingFactory factory = getOwner().getBindingFactory();
			factory.setBindable(getOwner());
			binding = factory.convertFromString(getUnparsedBinding());
			binding.setBindingDefinition(getBindingDefinition());
			// System.out.println(">>>>>>>>>>>>>> Binding: "+binding.getStringRepresentation()+" owner="+binding.getOwner());
			// System.out.println("binding.isBindingValid()="+binding.isBindingValid());
		}

		if (!binding.isBindingValid()) {
			if (!silentMode) {
				logger.warning("Binding not valid: " + binding + " for owner " + getOwner() + " context="
						+ (getOwner() != null ? getOwner().getRootComponent() : null));
				// Dev note: Uncomment following to get more informations
				/*logger.warning("Binding not valid: "+binding+" for owner "+getOwner()+" context="+(getOwner()!=null?(getOwner()).getRootComponent():null));			logger.info("BindingModel="+getOwner().getBindingModel());
				binding.debugIsBindingValid();
				BindingExpression.logger.setLevel(Level.FINE);
				binding = AbstractBinding.abstractBindingConverter.convertFromString(getUnparsedBinding());
				binding.setBindingDefinition(getBindingDefinition());
				binding.isBindingValid();
				//(new Exception("prout")).printStackTrace();
				System.exit(-1);*/
			}
		}

		updateDependancies();
	}

	@Override
	protected void finalizeDeserialization() {
		finalizeDeserialization(false);
		if (owner != null && hasBinding() && isValid()) {
			owner.notifyBindingChanged(this);
		}
	}

	@Override
	protected void updateDependancies()
	{
		if (getOwner() instanceof FIBComponent) {

			if (binding == null) {
				return;
			}

			Vector<Expression> primitives;
			try {
				primitives = Expression.extractPrimitives(binding.getStringRepresentation());

				FIBComponent component = (FIBComponent)getOwner();
				FIBComponent rootComponent = component.getRootComponent();
				Iterator<FIBComponent> subComponents = rootComponent.subComponentIterator();
				while (subComponents.hasNext()) {
					FIBComponent next = subComponents.next();
					if (next != getOwner()) {
						if (next instanceof FIBWidget
								&& ((FIBWidget)next).getData() != null
								&& ((FIBWidget)next).getData().isSet()) {
							String data = ((FIBWidget)next).getData().toString();
							if (data != null) {
								for (Expression p : primitives) {
									String primitiveValue = null;
									if (p instanceof Variable) {
										primitiveValue = ((Variable) p).getName();
									}
									if (p instanceof Function) {
										primitiveValue = ((Function)p).getName();
									}
									if (primitiveValue != null && primitiveValue.startsWith(data)) {
										try {
											component.declareDependantOf(next);
										} catch (DependancyLoopException e) {
											logger.warning("DependancyLoopException raised while declaring dependancy (data lookup)"
													+"in the context of binding: "+binding.getStringRepresentation()
													+" primitive: "+primitiveValue
													+" component: "+component
													+" dependancy: "+next
													+" data: "+data
													+" message: "+e.getMessage());
										}
									}
								}

							}
						}
						if (next.getName() != null) {
							for (Expression p : primitives) {
								String primitiveValue = null;
								if (p instanceof Variable) {
									primitiveValue = ((Variable) p).getName();
								}
								if (p instanceof Function) {
									primitiveValue = ((Function)p).getName();
								}
								if (primitiveValue != null && StringUtils.isNotEmpty(next.getName()) && primitiveValue.startsWith(next.getName())) {
									try {
										component.declareDependantOf(next);
									} catch (DependancyLoopException e) {
										logger.warning("DependancyLoopException raised while declaring dependancy (name lookup)"
												+"in the context of binding: "+binding.getStringRepresentation()
												+" primitive: "+primitiveValue
												+" component: "+component
												+" dependancy: "+next
												+" message: "+e.getMessage());
									}
								}
							}
						}
					}
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}