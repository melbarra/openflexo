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

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.swing.JComponent;

import org.openflexo.fib.model.ComponentConstraints;
import org.openflexo.fib.model.FIBComponent;


public abstract class ComponentConstraintsImpl implements ComponentConstraints {

	private static final Logger logger = Logger.getLogger(FIBComponent.class.getPackage().getName());

	private boolean ignoreNotif = false;

	private Map<String, String> values = new HashMap<String, String>();

	@Override
	public String getStringRepresentation()
	{
		StringBuffer returned = new StringBuffer();
		returned.append(getType().name() + "(");
		boolean isFirst = true;
		for (String key : keySet()) {
			String v = get(key);
			returned.append((isFirst ? "" : ";") + key + "=" + v);
			isFirst = false;
		}
		returned.append(")");
		return returned.toString();
	}

	public ComponentConstraintsImpl()
	{
		super();
	}

	protected ComponentConstraintsImpl(String someConstraints)
	{
		this();
		StringTokenizer st = new StringTokenizer(someConstraints,";");
		while (st.hasMoreTokens()) {
			String next = st.nextToken();
			StringTokenizer st2 = new StringTokenizer(next,"=");
			String key = null;
			String value = null;
			if (st2.hasMoreTokens()) {
				key = st2.nextToken();
			}
			if (st2.hasMoreTokens()) {
				value = st2.nextToken();
			}
			if (key != null && value != null) {
				put(key,value);
			}
		}
	}

	ComponentConstraintsImpl(ComponentConstraints someConstraints)
	{
		this();
		ignoreNotif = true;
		putAll(someConstraints);
		setComponent(someConstraints.getComponent());
		ignoreNotif = false;
	}

	@Override
	public String put(String key, String value) {
		if (value != null) {
			return values.put(key, value);
		}
		else {
			return values.remove(key);
		}
		// TODO: Fix notifications here (but the current solution kinda sucks)
		/*
		if (getComponent() != null && !ignoreNotif) {
			FIBAttributeNotification<ComponentConstraints> notification = new FIBAttributeNotification<ComponentConstraints>(
					FIBComponent.Parameters.constraints, this, this);
			getComponent().notify(notification);
		}

		return returned;
		 */
	}

	@Override
	public <E extends Enum<E>> E getEnumValue(String key, Class<E> enumType, E defaultValue) {
		String stringValue = get(key);
		if (stringValue == null) {
			ignoreNotif = true;
			setEnumValue(key, defaultValue);
			ignoreNotif = false;
			return defaultValue;
		}
		for (E en : enumType.getEnumConstants()) {
			if (en.name().equals(stringValue)) {
				return en;
			}
		}
		logger.warning("Found inconsistent value '" + stringValue + "' as " + enumType.getName());
		return defaultValue;
	}

	@Override
	public <E extends Enum<E>> void setEnumValue(String key, E value) {
		put(key, value.name());
	}

	@Override
	public int getIntValue(String key, int defaultValue) {
		String stringValue = get(key);
		if (stringValue == null) {
			ignoreNotif = true;
			setIntValue(key, defaultValue);
			ignoreNotif = false;
			return defaultValue;
		}
		return Integer.parseInt(stringValue);
	}

	@Override
	public void setIntValue(String key, int value) {
		put(key, String.valueOf(value));
	}

	@Override
	public float getFloatValue(String key, float defaultValue) {
		String stringValue = get(key);
		if (stringValue == null) {
			ignoreNotif = true;
			setFloatValue(key, defaultValue);
			ignoreNotif = false;
			return defaultValue;
		}
		return Float.parseFloat(stringValue);
	}

	@Override
	public void setFloatValue(String key, float value) {
		put(key, String.valueOf(value));
	}

	@Override
	public double getDoubleValue(String key, double defaultValue) {
		String stringValue = get(key);
		if (stringValue == null) {
			ignoreNotif = true;
			setDoubleValue(key, defaultValue);
			ignoreNotif = false;
			return defaultValue;
		}
		return Double.parseDouble(stringValue);
	}

	@Override
	public void setDoubleValue(String key, double value) {
		put(key, String.valueOf(value));
	}

	@Override
	public boolean getBooleanValue(String key, boolean defaultValue) {
		String stringValue = get(key);
		if (stringValue == null) {
			ignoreNotif = true;
			setBooleanValue(key, defaultValue);
			ignoreNotif = false;
			return defaultValue;
		}
		return stringValue.equalsIgnoreCase("true");
	}

	@Override
	public void setBooleanValue(String key, boolean value) {
		put(key, String.valueOf(value));
	}

	@Override
	public abstract void performConstrainedAddition(JComponent container, JComponent contained);

	@Override
	public final int getIndex() {
		return getIntValue(INDEX, 0);
	}

	@Override
	public final void setIndex(int x) {
		setIntValue(INDEX, x);
	}

	@Override
	public final void setIndexNoNotification(int x) {
		ignoreNotif = true;
		setIntValue(INDEX, x);
		ignoreNotif = false;
	}

	@Override
	public final boolean hasIndex() {
		return get(INDEX) != null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + values.toString();
	}


}
