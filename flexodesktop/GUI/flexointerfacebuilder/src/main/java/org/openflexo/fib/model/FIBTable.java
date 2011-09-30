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
import java.util.List;

import javax.swing.ListSelectionModel;

import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingModel;
import org.openflexo.fib.model.FIBTableAction.FIBAddAction;
import org.openflexo.fib.model.FIBTableAction.FIBCustomAction;
import org.openflexo.fib.model.FIBTableAction.FIBRemoveAction;
import org.openflexo.model.annotations.ModelEntity;

@ModelEntity
public interface FIBTable extends FIBWidget /*implements DynamicAccess*/{

	public BindingDefinition getSelectedBindingDefinition();


	public static enum Parameters implements FIBModelAttribute
	{
		iteratorClass,
		visibleRowCount,
		rowHeight,
		createNewRowOnClick,
		autoSelectFirstRow,
		boundToSelectionManager,
		selectionMode,
		selected,
		columns,
		actions,
		showFooter,
		textSelectionColor,
		textNonSelectionColor,
		backgroundSelectionColor,
		backgroundSecondarySelectionColor,
		backgroundNonSelectionColor
	}

	public enum SelectionMode
	{
		SingleSelection { @Override
			public int getMode() { return ListSelectionModel.SINGLE_SELECTION;} },
			SingleIntervalSelection { @Override
				public int getMode() { return ListSelectionModel.SINGLE_INTERVAL_SELECTION;} },
				MultipleIntervalSelection { @Override
					public int getMode() { return ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;} };

					public abstract int getMode();
	}

	public FIBTableColumn getColumnWithTitle(String title);

	public List<FIBTableColumn> getColumns();

	public void setColumns(List<FIBTableColumn> columns);

	public void addToColumns(FIBTableColumn aColumn);

	public void removeFromColumns(FIBTableColumn aColumn);

	public List<FIBTableAction> getActions();

	public void setActions(List<FIBTableAction> actions);

	public void addToActions(FIBTableAction anAction);

	public void removeFromActions(FIBTableAction anAction);

	public BindingModel getTableBindingModel();

	public BindingModel getActionBindingModel();

	public DataBinding getSelected();

	public void setSelected(DataBinding selected);

	public Class getIteratorClass();

	public void setIteratorClass(Class iteratorClass);

	public boolean getAutoSelectFirstRow();

	public void setAutoSelectFirstRow(boolean autoSelectFirstRow);

	public int getVisibleRowCount();

	public void setVisibleRowCount(int visibleRowCount);

	public int getRowHeight();

	public void setRowHeight(int rowHeight);

	public boolean getCreateNewRowOnClick();

	public void setCreateNewRowOnClick(boolean createNewRowOnClick);

	public boolean getShowFooter();

	public void setShowFooter(boolean showFooter);

	public boolean getBoundToSelectionManager();

	public void setBoundToSelectionManager(boolean boundToSelectionManager);

	public FIBAddAction createAddAction();

	public FIBRemoveAction createRemoveAction();

	public FIBCustomAction createCustomAction();

	public FIBTableAction deleteAction(FIBTableAction actionToDelete);

	public FIBLabelColumn createLabelColumn();

	public FIBTextFieldColumn createTextFieldColumn();

	public FIBCheckBoxColumn createCheckBoxColumn();

	public FIBDropDownColumn createDropDownColumn();

	public FIBNumberColumn createNumberColumn();

	public FIBIconColumn createIconColumn();

	public FIBCustomColumn createCustomColumn();

	public FIBTableColumn deleteColumn(FIBTableColumn columnToDelete);

	public void moveToTop(FIBTableColumn c);

	public void moveUp(FIBTableColumn c);

	public void moveDown(FIBTableColumn c);

	public void moveToBottom(FIBTableColumn c);

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
