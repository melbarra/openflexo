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

import org.openflexo.antar.binding.AbstractBinding;
import org.openflexo.antar.binding.AbstractBinding.BindingEvaluationContext;
import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.fib.model.FIBModelObject.FIBModelAttribute;
import org.openflexo.model.annotations.Constructor;
import org.openflexo.model.annotations.Constructors;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.NotEmptyChecker;
import org.openflexo.model.annotations.Parameter;
import org.openflexo.model.annotations.Setter;
import org.openflexo.model.annotations.StringConverter;
import org.openflexo.model.factory.AccessibleProxyObject;
import org.openflexo.model.factory.ModelFactory;
import org.openflexo.model.xml.DefaultStringEncoder.Converter;

@ModelEntity
@Constructors({ @Constructor({ @Parameter(type = FIBModelObject.class, name = DataBinding.OWNER),
	@Parameter(type = FIBModelAttribute.class, name = DataBinding.BINDING_ATTRIBUTE),
	@Parameter(type = BindingDefinition.class, name = DataBinding.BINDING_DEFINITION) }),
	@Constructor({
		@Parameter(type = String.class, name=DataBinding.UNPARSED_BINDING)
	})
})
public interface DataBinding extends AccessibleProxyObject
{

	public static final String OWNER = "owner";
	public static final String BINDING_ATTRIBUTE = "bindingAttribute";
	public static final String BINDING_DEFINITION = "bindingDefinition";
	public static final String BINDING = "binding";
	public static final String UNPARSED_BINDING = "unparsedBinding";

	@StringConverter
	public static final DataBinding.DataBindingConverter CONVERTER = new DataBindingConverter();

	public static class DataBindingConverter extends Converter<DataBinding>
	{
		public DataBindingConverter()
		{
			super(DataBinding.class);
		}

		@Override
		public DataBinding convertFromString(String value, ModelFactory factory)
		{
			return factory.newInstance(DataBinding.class, value);
		}

		@Override
		public String convertToString(DataBinding value)
		{
			return value.toString();
		};
	}

	public Object execute(BindingEvaluationContext context);

	public Object getBindingValue(BindingEvaluationContext context);

	public void setBindingValue(Object value, BindingEvaluationContext context);

	@Getter(id = BINDING_DEFINITION)
	public BindingDefinition getBindingDefinition();

	@Setter(id = BINDING_DEFINITION)
	public void setBindingDefinition(BindingDefinition bindingDefinition);

	@Getter(id = BINDING)
	public AbstractBinding getBinding();

	public AbstractBinding getBinding(boolean silentMode);

	@Setter(id = BINDING)
	public void setBinding(AbstractBinding value);

	@NotEmptyChecker(id = BINDING)
	public boolean hasBinding();

	public boolean isValid();

	public boolean isValid(boolean silentMode);

	public boolean isSet();

	public boolean isUnset();

	@Getter(id = UNPARSED_BINDING)
	public String getUnparsedBinding();

	@Setter(id = UNPARSED_BINDING)
	public void setUnparsedBinding(String unparsedBinding);

	@Getter(id = OWNER)
	public FIBModelObject getOwner();

	@Setter(id = OWNER)
	public void setOwner(FIBModelObject owner);

	public void finalizeDeserialization(boolean silentMode);

	public void finalizeDeserialization();

	public void updateDependancies();

	@Getter(id=BINDING_ATTRIBUTE)
	public FIBModelAttribute getBindingAttribute();

	@Setter(id=BINDING_ATTRIBUTE)
	public void setBindingAttribute(FIBModelAttribute bindingAttribute);

}