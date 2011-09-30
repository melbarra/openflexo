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

import java.awt.Font;
import java.lang.reflect.Type;

import org.openflexo.antar.binding.Bindable;
import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.antar.binding.BindingModel;
import org.openflexo.model.annotations.ModelEntity;

@ModelEntity(isAbstract = true)
public interface FIBTableColumn extends FIBModelObject {

	public static enum Parameters implements FIBModelAttribute
	{
		data,
		format,
		tooltip,
		title,
		tooltipText,
		columnWidth,
		resizable,
		displayTitle,
		font
	}

	public static enum ColumnType
	{
		CheckBox,
		Custom,
		DropDown,
		Icon,
		Label,
		Number,
		TextField
	}


	public static final BindingDefinition DATA = new BindingDefinition("data", Object.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition TOOLTIP = new BindingDefinition("tooltip", String.class, BindingDefinitionType.GET, false);
	public static final BindingDefinition FORMAT = new BindingDefinition("format", String.class, BindingDefinitionType.GET, false);

	public FIBTable getTable();

	public void setTable(FIBTable table);

	@Override
	public FIBComponent getRootComponent();

	public DataBinding getData();

	public void setData(DataBinding data);

	public void finalizeTableDeserialization();

	@Override
	public BindingModel getBindingModel();

	public Type getDataClass();

	public abstract Type getDefaultDataClass();

	public String getTitle();

	public void setTitle(String title);

	public Integer getColumnWidth();

	public void setColumnWidth(Integer columnWidth);

	public Boolean getResizable();

	public void setResizable(Boolean resizable);

	public Boolean getDisplayTitle();

	public void setDisplayTitle(Boolean displayTitle);

	public Font retrieveValidFont();

	public Font getFont();

	public void setFont(Font font);

	public abstract ColumnType getColumnType();

	public DataBinding getFormat();

	public void setFormat(DataBinding format);

	public FIBFormatter getFormatter();

	@ModelEntity
	public interface FIBFormatter extends FIBModelObject, Bindable
	{

	}

	public int getIndex();

	public String getTooltipText();

	public void setTooltipText(String tooltipText);

	public DataBinding getTooltip();

	public void setTooltip(DataBinding tooltip);

}
