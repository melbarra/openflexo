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

import org.openflexo.model.annotations.ModelEntity;

import com.metaphaseeditor.MetaphaseEditorConfiguration.MetaphaseEditorOption;

@ModelEntity
public interface FIBHtmlEditorOption extends FIBModelObject {

	public static enum Parameters implements FIBModelAttribute
	{
		isVisible,
		index
	}

	public int getLevel();

	public FIBHtmlEditor getFIBHtmlEditor();

	public void setFIBHtmlEditor(FIBHtmlEditor editor);

	public boolean getIsVisible();

	public void setIsVisible(boolean isVisible);

	public int getIndex();

	public void setIndex(int index);

	public void setIndexNoEditorNotification(int index);

	public FIBHtmlEditorOption getParentOption();

	public List<FIBHtmlEditorOption> getSubOptions();

	public int getLine();

	public MetaphaseEditorOption makeMetaphaseEditorOption(int line);
}
