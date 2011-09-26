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

import java.util.List;

import org.openflexo.antar.binding.Bindable;
import org.openflexo.model.annotations.Adder;
import org.openflexo.model.annotations.Deleter;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.Getter.Cardinality;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.Remover;
import org.openflexo.model.annotations.Setter;
import org.openflexo.model.annotations.XMLAttribute;
import org.openflexo.model.annotations.XMLElement;
import org.openflexo.model.factory.AccessibleProxyObject;
import org.openflexo.model.factory.CloneableProxyObject;
import org.openflexo.model.factory.ObservableObject;


@ModelEntity(isAbstract = true)
public interface FIBModelObject extends AccessibleProxyObject, ObservableObject, CloneableProxyObject, Bindable {

	public static final String PARAMETERS = "parameters";
	public static final String DESCRIPTION = "description";
	public static final String NAME = "name";

	public static interface FIBModelAttribute
	{
	}

	public static enum Parameters implements FIBModelAttribute
	{
		name,
		description,
		parameters
	}


	@Getter(id = NAME)
	@XMLAttribute(xmlTag = NAME)
	public String getName();

	@Setter(id = NAME)
	public void setName(String name);

	@Getter(id = DESCRIPTION)
	@XMLAttribute(xmlTag = DESCRIPTION)
	public String getDescription();

	@Setter(id = DESCRIPTION)
	public void setDescription(String description);

	@Getter(id = PARAMETERS, cardinality = Cardinality.LIST)
	@XMLElement
	public List<FIBParameter> getParameters();

	@Setter(id = PARAMETERS)
	public void setParameters(List<FIBParameter> parameters);

	@Adder(id = PARAMETERS)
	public void addToParameters(FIBParameter p);

	@Remover(id = PARAMETERS)
	public void removeFromParameters(FIBParameter p);

	@Deleter
	public void delete();

	public abstract FIBComponent getRootComponent();

	public String getParameter(String parameterName);

	public FIBParameter createNewParameter();

	public void deleteParameter(FIBParameter p);

	public boolean isParameterAddable();

	public boolean isParameterDeletable(FIBParameter p);

}
