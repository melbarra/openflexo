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
import java.util.Vector;

import javax.swing.tree.TreeSelectionModel;

import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.Setter;

@ModelEntity
public interface FIBBrowser extends FIBWidget {

	public static final String ROOT = "root";
	public static final String ITERATOR_CLASS = "iteratorClass";
	public static final String VISIBLE_ROW_COUNT = "visibleRowCount";
	public static final String ROW_HEIGHT = "rowHeight";
	public static final String BOUND_TO_SELECTION_MANAGER = "boundToSelectionManager";
	public static final String SELECTION_MODE = "selectionMode";
	public static final String SELECTED = "selected";
	public static final String ELEMENTS = "elements";
	public static final String SHOW_FOOTER = "showFooter";
	public static final String ROOT_VISIBLE = "rootVisible";
	public static final String SHOW_ROOTS_HANDLE = "showRootsHandle";
	public static final String TEXT_SELECTION_COLOR = "textSelectionColor";
	public static final String TEXT_NON_SELECTION_COLOR = "textNonSelectionColor";
	public static final String BACKGROUND_SELECTION_COLOR = "backgroundSelectionColor";
	public static final String BACKGROUND_SECONDARY_SELECTION_COLOR = "backgroundSecondarySelectionColor";
	public static final String BACKGROUND_NON_SELECTION_COLOR = "backgroundNonSelectionColor";
	public static final String BORDER_SELECTION_COLOR = "borderSelectionColor";

	public BindingDefinition getSelectedBindingDefinition();

	public BindingDefinition getRootBindingDefinition();

	public static enum Parameters implements FIBModelAttribute
	{
		root,
		iteratorClass,
		visibleRowCount,
		rowHeight,
		boundToSelectionManager,
		selectionMode,
		selected,
		elements,
		showFooter,
		rootVisible,
		showRootsHandle,
		textSelectionColor,
		textNonSelectionColor,
		backgroundSelectionColor,
		backgroundSecondarySelectionColor,
		backgroundNonSelectionColor,
		borderSelectionColor
	}


	public enum SelectionMode
	{
		SingleTreeSelection { @Override
			public int getMode() {
			return TreeSelectionModel.SINGLE_TREE_SELECTION;
		}
		},
		ContiguousTreeSelection {
			@Override
			public int getMode() {
				return TreeSelectionModel.CONTIGUOUS_TREE_SELECTION;
			}
		},
		DiscontiguousTreeSelection {
			@Override
			public int getMode() {
				return TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION;
			}
		};

		public abstract int getMode();
	}

	@Getter(id = ROOT, inverse = DataBinding.OWNER)
	public DataBinding getRoot();

	@Setter(id = ROOT)
	public void setRoot(DataBinding root);

	public DataBinding getSelected();

	public void setSelected(DataBinding selected);

	public Class getIteratorClass();

	public void setIteratorClass(Class iteratorClass);

	public int getVisibleRowCount();

	public void setVisibleRowCount(int visibleRowCount);

	public int getRowHeight();

	public void setRowHeight(int rowHeight);

	public boolean getBoundToSelectionManager();

	public void setBoundToSelectionManager(boolean boundToSelectionManager);

	public Vector<FIBBrowserElement> getElements();

	public void setElements(Vector<FIBBrowserElement> elements);

	public void addToElements(FIBBrowserElement anElement);

	public void removeFromElements(FIBBrowserElement anElement);

	public FIBBrowserElement createElement();

	public FIBBrowserElement deleteElement(FIBBrowserElement elementToDelete);

	public void moveToTop(FIBBrowserElement e);

	public void moveUp(FIBBrowserElement e);

	public void moveDown(FIBBrowserElement e);

	public void moveToBottom(FIBBrowserElement e);

	public SelectionMode getSelectionMode();

	public void setSelectionMode(SelectionMode selectionMode);

	public FIBBrowserElement elementForClass(Class aClass);

	public boolean getShowFooter();

	public void setShowFooter(boolean showFooter);

	public boolean getRootVisible();

	public void setRootVisible(boolean rootVisible);

	public boolean getShowRootsHandle();

	public void setShowRootsHandle(boolean showRootsHandle);

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

	public Color getBorderSelectionColor();

	public void setBorderSelectionColor(Color borderSelectionColor);

}
