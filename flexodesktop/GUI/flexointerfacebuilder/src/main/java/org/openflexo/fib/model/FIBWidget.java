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

import java.lang.reflect.Type;

import javax.swing.Icon;

import org.openflexo.antar.binding.Bindable;
import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.XMLElement;

@ModelEntity(isAbstract = true)
@XMLElement()
public interface FIBWidget extends FIBComponent {

	public static final BindingDefinition TOOLTIP = new BindingDefinition("tooltip", String.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition ENABLE = new BindingDefinition("enable", Boolean.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition FORMAT = new BindingDefinition("format", String.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition ICON = new BindingDefinition("icon", Icon.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition VALUE_CHANGED_ACTION = new BindingDefinition("valueChangedAction", Void.class,
			BindingDefinitionType.EXECUTE, false);
	public static final BindingDefinition CLICK_ACTION = new BindingDefinition("clickAction", Void.class, BindingDefinitionType.EXECUTE,
			false);
	public static final BindingDefinition DOUBLE_CLICK_ACTION = new BindingDefinition("doubleClickAction", Void.class,
			BindingDefinitionType.EXECUTE, false);

	public static enum Parameters implements FIBModelAttribute
	{
		enable,
		format,
		icon,
		tooltip,
		tooltipText,
		localize,
		manageDynamicModel,
		readOnly,
		clickAction,
		doubleClickAction,
		valueChangedAction
	}

	public DataBinding getTooltip();

	public void setTooltip(DataBinding tooltip);

	public DataBinding getEnable();

	public void setEnable(DataBinding enable);

	public Boolean getManageDynamicModel();

	public void setManageDynamicModel(Boolean manageDynamicModel);

	public Boolean getReadOnly();

	public void setReadOnly(Boolean readOnly);

	public String getTooltipText();

	public void setTooltipText(String tooltipText);

	public DataBinding getFormat();

	public void setFormat(DataBinding format);

	public DataBinding getIcon();

	public void setIcon(DataBinding icon);

	public Boolean getLocalize();

	public void setLocalize(Boolean localize);

	public FIBFormatter getFormatter();

	public Type getFormattedObjectType();

	public interface FIBFormatter extends FIBModelObject, Bindable
	{

	}

	public DataBinding getValueChangedAction();

	public void setValueChangedAction(DataBinding valueChangedAction);

	public boolean hasClickAction();

	public DataBinding getClickAction();

	public void setClickAction(DataBinding clickAction);

	public boolean hasDoubleClickAction();

	public DataBinding getDoubleClickAction();

	public void setDoubleClickAction(DataBinding doubleClickAction);

	public boolean isPaletteElement();

}
