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

import javax.swing.JComponent;

import org.openflexo.fib.model.BoxLayoutConstraints;
import org.openflexo.fib.model.ComponentConstraints;
import org.openflexo.fib.model.FIBPanel.Layout;



public abstract class BoxLayoutConstraintsImpl extends ComponentConstraintsImpl implements BoxLayoutConstraints {

	private static final String ALIGNMENT_X = "alignmentX";
	private static final String ALIGNMENT_Y = "alignmentY";

	public BoxLayoutConstraintsImpl()
	{
		super();
	}

	protected BoxLayoutConstraintsImpl(String someConstraints)
	{
		super(someConstraints);
	}

	BoxLayoutConstraintsImpl(ComponentConstraints someConstraints)
	{
		super(someConstraints);
	}

	public BoxLayoutConstraintsImpl(int index)
	{
		super();
		setIndex(index);
	}

	@Override
	public Layout getType()
	{
		return Layout.box;
	}

	@Override
	public float getAlignmentX()
	{
		return getFloatValue(ALIGNMENT_X,0.5f);
	}

	@Override
	public void setAlignmentX(float x)
	{
		setFloatValue(ALIGNMENT_X,x);
	}

	@Override
	public float getAlignmentY()
	{
		return getFloatValue(ALIGNMENT_Y,0.5f);
	}

	@Override
	public void setAlignmentY(float y)
	{
		setFloatValue(ALIGNMENT_Y,y);
	}


	@Override
	public void performConstrainedAddition(JComponent container,
			JComponent contained)
	{
		contained.setAlignmentX(getAlignmentX());
		contained.setAlignmentY(getAlignmentY());
		container.add(contained);
	}
}
