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

import org.openflexo.model.annotations.ModelEntity;

@ModelEntity
public interface FIBNumber extends FIBWidget {

	public static enum Parameters implements FIBModelAttribute
	{
		numberType,
		validateOnReturn,
		minValue,
		maxValue,
		increment,
		columns
	}

	public static enum NumberType
	{
		ByteType,
		ShortType,
		IntegerType,
		LongType,
		FloatType,
		DoubleType;
	}

	public Number retrieveMinValue();

	public Number getMinValue();

	public void setMinValue(Number minValue);

	public Number retrieveMaxValue();

	public Number getMaxValue();

	public void setMaxValue(Number maxValue);

	public Number retrieveIncrement();

	public Number getIncrement();

	public void setIncrement(Number increment);

	public NumberType getNumberType();

	public void setNumberType(NumberType numberType);

	public boolean getValidateOnReturn();

	public void setValidateOnReturn(boolean validateOnReturn);

	public Integer getColumns();

	public void setColumns(Integer columns);

	public int getIncrementAsInteger();

	public void setIncrementAsInteger(int incrementAsInteger);

	public int getMinValueAsInteger();

	public void setMinValueAsInteger(int minValueAsInteger);

	public int getMaxValueAsInteger();

	public void setMaxValueAsInteger(int maxValueAsInteger);

}
