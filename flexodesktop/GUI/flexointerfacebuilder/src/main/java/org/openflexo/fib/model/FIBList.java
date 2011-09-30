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

import java.awt.Color;

import org.openflexo.fib.model.FIBTable.SelectionMode;
import org.openflexo.model.annotations.ModelEntity;

@ModelEntity
public interface FIBList extends FIBMultipleValues {

	public static enum Parameters implements FIBModelAttribute
	{
		visibleRowCount,
		rowHeight,
		createNewRowOnClick,
		autoSelectFirstRow,
		boundToSelectionManager,
		selectionMode,
		selected,
		textSelectionColor,
		textNonSelectionColor,
		backgroundSelectionColor,
		backgroundSecondarySelectionColor,
		backgroundNonSelectionColor
	}

	public DataBinding getSelected();

	public void setSelected(DataBinding selected);

	public int getVisibleRowCount();

	public void setVisibleRowCount(int visibleRowCount);

	public int getRowHeight();

	public void setRowHeight(int rowHeight);

	public boolean getCreateNewRowOnClick();

	public void setCreateNewRowOnClick(boolean createNewRowOnClick);

	public boolean getAutoSelectFirstRow();

	public void setAutoSelectFirstRow(boolean autoSelectFirstRow);

	public boolean getBoundToSelectionManager();

	public void setBoundToSelectionManager(boolean boundToSelectionManager);

	public SelectionMode getSelectionMode();

	public void setSelectionMode(SelectionMode selectionMode);

	public Color getTextSelectionColor();

	public void setTextSelectionColor(Color textSelectionColor);

	public Color getTextNonSelectionColor();

	public void setTextNonSelectionColor(Color textNonSelectionColor);

	public Color getBackgroundSelectionColor();

	public void setBackgroundSelectionColor(Color backgroundSelectionColor);

	public Color getBackgroundSecondarySelectionColor();

	public void setBackgroundSecondarySelectionColor(Color backgroundSecondarySelectionColor);

	public Color getBackgroundNonSelectionColor();

	public void setBackgroundNonSelectionColor(Color backgroundNonSelectionColor);

}
