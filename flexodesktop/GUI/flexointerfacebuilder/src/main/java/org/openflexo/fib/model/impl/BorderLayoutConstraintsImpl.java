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

import javax.swing.JComponent;

import org.openflexo.fib.model.BorderLayoutConstraints;
import org.openflexo.fib.model.ComponentConstraints;
import org.openflexo.fib.model.FIBComponent;
import org.openflexo.fib.model.FIBPanel.Layout;

public abstract class BorderLayoutConstraintsImpl extends ComponentConstraintsImpl implements BorderLayoutConstraints {

	private static final Logger logger = Logger.getLogger(FIBComponent.class.getPackage().getName());

	private static final String LOCATION = "location";

	public BorderLayoutLocation getLocation()
	{
		return getEnumValue(LOCATION,BorderLayoutLocation.class,BorderLayoutLocation.center);
	}

	public void setLocation(BorderLayoutLocation location)
	{
		setEnumValue(LOCATION,location);
	}

	public BorderLayoutConstraintsImpl()
	{
		super();
	}

	public BorderLayoutConstraintsImpl(BorderLayoutLocation location)
	{
		super();
		setLocation(location);
	}

	protected BorderLayoutConstraintsImpl(String someConstraints)
	{
		super(someConstraints);
	}

	BorderLayoutConstraintsImpl(ComponentConstraints someConstraints)
	{
		super(someConstraints);
	}

	@Override
	public Layout getType()
	{
		return Layout.border;
	}

	@Override
	public void performConstrainedAddition(JComponent container,
			JComponent contained)
	{
		container.add(contained,getLocation().getConstraint());
	}

}
