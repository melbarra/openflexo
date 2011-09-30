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

import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JComponent;

import org.openflexo.fib.model.FIBPanel.Layout;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.Setter;
import org.openflexo.model.annotations.StringConverter;
import org.openflexo.model.factory.AccessibleProxyObject;
import org.openflexo.model.factory.ModelFactory;
import org.openflexo.model.xml.DefaultStringEncoder.Converter;

@ModelEntity(isAbstract = true)
public interface ComponentConstraints extends AccessibleProxyObject, Map<String, String> {

	public static final String INDEX = "index";
	public static final String COMPONENT = "component";

	@StringConverter
	public static final ComponentConstraintsConverter CONVERTER = new ComponentConstraintsConverter();

	public static class ComponentConstraintsConverter extends Converter<ComponentConstraints>
	{
		private static final Logger logger = Logger.getLogger(ComponentConstraints.class.getPackage().getName());

		public ComponentConstraintsConverter()
		{
			super(ComponentConstraints.class);
		}

		@Override
		public ComponentConstraints convertFromString(String aValue, ModelFactory factory)
		{
			try {
				//System.out.println("aValue="+aValue);
				String constraintType = aValue.substring(0,aValue.indexOf("("));
				String someConstraints = aValue.substring(aValue.indexOf("(")+1,aValue.length()-1);
				//System.out.println("constraintType="+constraintType);
				//System.out.println("someConstraints="+someConstraints);
				Layout layout = Layout.valueOf(constraintType);
				switch (layout) {
				case border:
					break;
				case box:
					break;
				case flow:
					break;
				case grid:
					break;
				case gridbag:
					break;
				case none:
					break;
				case twocols:
					break;

				}
				if (constraintType.equals(Layout.border.name())) {
					return factory.newInstance(BorderLayoutConstraints.class, someConstraints);
				}
				else if (constraintType.equals(Layout.flow.name())) {
					return factory.newInstance(FlowLayoutConstraints.class, someConstraints);
				}
				else if (constraintType.equals(Layout.grid.name())) {
					return factory.newInstance(GridLayoutConstraints.class, someConstraints);
				}
				else if (constraintType.equals(Layout.box.name())) {
				}
				else if (constraintType.equals(Layout.border.name())) {
					return factory.newInstance(BorderLayoutConstraints.class, someConstraints);
				}
				else if (constraintType.equals(Layout.twocols.name())) {
					return factory.newInstance(TwoColsLayoutConstraints.class, someConstraints);
				}
				else if (constraintType.equals(Layout.gridbag.name())) {
					return factory.newInstance(GridBagLayoutConstraints.class, someConstraints);
				}
			}
			catch (StringIndexOutOfBoundsException e) {
				logger.warning("Syntax error in ComponentConstraints: "+aValue);
			} catch (IllegalArgumentException e) {
				logger.warning("Syntax error in ComponentConstraints: " + aValue);

			}
			return null;
		}

		@Override
		public String convertToString(ComponentConstraints value)
		{
			if (value == null) {
				return null;
			}
			return value.getStringRepresentation();
		};
	}

	public String getStringRepresentation();

	public Layout getType();

	public <E extends Enum<E>> E getEnumValue(String key, Class<E> enumType, E defaultValue);

	public <E extends Enum<E>> void setEnumValue(String key, E value);

	public int getIntValue(String key, int defaultValue);

	public void setIntValue(String key, int value);

	public float getFloatValue(String key, float defaultValue);

	public void setFloatValue(String key, float value);

	public double getDoubleValue(String key, double defaultValue);

	public void setDoubleValue(String key, double value);

	public boolean getBooleanValue(String key, boolean defaultValue);

	public void setBooleanValue(String key, boolean value);

	@Getter(id = COMPONENT, inverse = FIBComponent.CONSTRAINTS)
	public FIBComponent getComponent();

	@Setter(id = COMPONENT)
	public void setComponent(FIBComponent component);

	public abstract void performConstrainedAddition(JComponent container, JComponent contained);

	public int getIndex();

	public void setIndex(int x);

	public void setIndexNoNotification(int x);

	public boolean hasIndex();

}
