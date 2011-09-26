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

import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.Setter;
import org.openflexo.model.annotations.XMLAttribute;

@ModelEntity
public interface FIBParameter {

	public static final String NAME = "name";
	public static final String VALUE = "value";

	@Getter(id = NAME)
	@XMLAttribute(xmlTag = NAME)
	public String getName();

	@Setter(id = NAME)
	public void setName(String name);

	@Getter(id = VALUE)
	@XMLAttribute(xmlTag = VALUE)
	public String getValue();

	@Setter(id = VALUE)
	public void setValue(String value);

}
