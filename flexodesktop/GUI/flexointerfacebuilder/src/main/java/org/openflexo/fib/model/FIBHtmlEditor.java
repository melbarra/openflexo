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

import java.util.List;
import java.util.Vector;

import org.openflexo.model.annotations.ModelEntity;

import com.metaphaseeditor.MetaphaseEditorPanel;

@ModelEntity
public interface FIBHtmlEditor extends FIBWidget {

	public static final String[] option_keys = {
		MetaphaseEditorPanel.SOURCE_PANEL_KEY,
		MetaphaseEditorPanel.SOURCE_BUTTON_KEY,

		MetaphaseEditorPanel.PAGE_PANEL_KEY,
		MetaphaseEditorPanel.OPEN_BUTTON_KEY,
		MetaphaseEditorPanel.SAVE_BUTTON_KEY,
		MetaphaseEditorPanel.NEW_BUTTON_KEY,
		MetaphaseEditorPanel.PREVIEW_BUTTON_KEY,

		MetaphaseEditorPanel.EDIT_PANEL_KEY,
		MetaphaseEditorPanel.CUT_BUTTON_KEY,
		MetaphaseEditorPanel.COPY_BUTTON_KEY,
		MetaphaseEditorPanel.PASTE_BUTTON_KEY,
		MetaphaseEditorPanel.PASTE_AS_TEXT_BUTTON_KEY,

		MetaphaseEditorPanel.TOOLS_PANEL_KEY,
		MetaphaseEditorPanel.PRINT_BUTTON_KEY,
		MetaphaseEditorPanel.SPELL_CHECK_BUTTON_KEY,

		MetaphaseEditorPanel.UNDO_REDO_PANEL_KEY,
		MetaphaseEditorPanel.UNDO_BUTTON_KEY,
		MetaphaseEditorPanel.REDO_BUTTON_KEY,

		MetaphaseEditorPanel.SEARCH_PANEL_KEY,
		MetaphaseEditorPanel.FIND_BUTTON_KEY,
		MetaphaseEditorPanel.REPLACE_BUTTON_KEY,

		MetaphaseEditorPanel.FORMAT_PANEL_KEY,
		MetaphaseEditorPanel.SELECT_ALL_BUTTON_KEY,
		MetaphaseEditorPanel.CLEAR_FORMATTING_BUTTON_KEY,

		MetaphaseEditorPanel.TEXT_EFFECT_PANEL_KEY,
		MetaphaseEditorPanel.BOLD_BUTTON_KEY,
		MetaphaseEditorPanel.ITALIC_BUTTON_KEY,
		MetaphaseEditorPanel.UNDERLINE_BUTTON_KEY,
		MetaphaseEditorPanel.STRIKE_BUTTON_KEY,

		MetaphaseEditorPanel.SUB_SUPER_SCRIPT_PANEL_KEY,
		MetaphaseEditorPanel.SUB_SCRIPT_BUTTON_KEY,
		MetaphaseEditorPanel.SUPER_SCRIPT_BUTTON_KEY,

		MetaphaseEditorPanel.LIST_PANEL_KEY,
		MetaphaseEditorPanel.NUMBERED_LIST_BUTTON_KEY,
		MetaphaseEditorPanel.BULLETED_BUTTON_KEY,

		MetaphaseEditorPanel.BLOCK_PANEL_KEY,
		MetaphaseEditorPanel.DECREASE_INDENT_BUTTON_KEY,
		MetaphaseEditorPanel.INCREASE_INDENT_BUTTON_KEY,
		MetaphaseEditorPanel.BLOCK_QUOTE_BUTTON_KEY,
		MetaphaseEditorPanel.DIV_BUTTON_KEY,
		MetaphaseEditorPanel.PARAGRAPH_BUTTON_KEY,

		MetaphaseEditorPanel.JUSTIFICATION_PANEL_KEY,
		MetaphaseEditorPanel.LEFT_JUSTIFY_BUTTON_KEY,
		MetaphaseEditorPanel.CENTER_JUSTIFY_BUTTON_KEY,
		MetaphaseEditorPanel.RIGHT_JUSTIFY_BUTTON_KEY,
		MetaphaseEditorPanel.BLOCK_JUSTIFY_BUTTON_KEY,

		MetaphaseEditorPanel.LINK_PANEL_KEY,
		MetaphaseEditorPanel.LINK_BUTTON_KEY,
		MetaphaseEditorPanel.UNLINK_BUTTON_KEY,
		MetaphaseEditorPanel.ANCHOR_BUTTON_KEY,

		MetaphaseEditorPanel.MISC_PANEL_KEY,
		MetaphaseEditorPanel.IMAGE_BUTTON_KEY,
		MetaphaseEditorPanel.TABLE_BUTTON_KEY,
		MetaphaseEditorPanel.HORIZONTAL_LINE_BUTTON_KEY,
		MetaphaseEditorPanel.SPECIAL_CHAR_BUTTON_KEY,

		MetaphaseEditorPanel.FONT_PANEL_KEY,
		MetaphaseEditorPanel.FONT_SIZE_PANEL_KEY,
		MetaphaseEditorPanel.PARAGRAPH_FORMAT_PANEL_KEY,

		MetaphaseEditorPanel.COLOR_PANEL_KEY,
		MetaphaseEditorPanel.TEXT_COLOR_BUTTON_KEY,
		MetaphaseEditorPanel.BACKGROUND_COLOR_BUTTON_KEY,

		MetaphaseEditorPanel.ABOUT_PANEL_KEY,
		MetaphaseEditorPanel.ABOUT_BUTTON_KEY
	};

	public static enum Parameters implements FIBModelAttribute
	{
		optionsInLine1,
		optionsInLine2,
		optionsInLine3,
		firstLevelOptionsInLine1,
		firstLevelOptionsInLine2,
		firstLevelOptionsInLine3,
		availableOptions,
		visibleAndUnusedOptions
	}

	public void makeFullHtmlEditor();

	public void makeEmbeddedHtmlEditor();

	public void makeLightHtmlEditor();

	public void makeUltraLightHtmlEditor();

	public List<FIBHtmlEditorOption> getFirstLevelOptionsInLine1();

	public List<FIBHtmlEditorOption> getOptionsInLine1();

	public void setOptionsInLine1(List<FIBHtmlEditorOption> optionsInLine1);

	public void addToOptionsInLine1(FIBHtmlEditorOption anOption);

	public void addToOptionsInLine1(List<FIBHtmlEditorOption> options);

	public void removeFromOptionsInLine1(FIBHtmlEditorOption anOption);

	public void removeFromOptionsInLine1(List<FIBHtmlEditorOption> options);

	public List<FIBHtmlEditorOption> getFirstLevelOptionsInLine2();

	public List<FIBHtmlEditorOption> getOptionsInLine2();

	public void setOptionsInLine2(List<FIBHtmlEditorOption> optionsInLine2);

	public void addToOptionsInLine2(FIBHtmlEditorOption anOption);

	public void addToOptionsInLine2(List<FIBHtmlEditorOption> options);

	public void removeFromOptionsInLine2(FIBHtmlEditorOption anOption);

	public void removeFromOptionsInLine2(List<FIBHtmlEditorOption> options);

	public List<FIBHtmlEditorOption> getFirstLevelOptionsInLine3();

	public List<FIBHtmlEditorOption> getOptionsInLine3();

	public void setOptionsInLine3(Vector<FIBHtmlEditorOption> optionsInLine3);

	public void addToOptionsInLine3(FIBHtmlEditorOption anOption);

	public void addToOptionsInLine3(List<FIBHtmlEditorOption> options);

	public void removeFromOptionsInLine3(FIBHtmlEditorOption anOption);

	public void removeFromOptionsInLine3(List<FIBHtmlEditorOption> options);

	public List<FIBHtmlEditorOption> getAvailableOptions();

	public List<FIBHtmlEditorOption> getVisibleAndUnusedOptions();

	public void indexChanged();

}
