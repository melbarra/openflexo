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

import java.io.File;

import javax.swing.JFileChooser;

import org.openflexo.model.annotations.ModelEntity;

@ModelEntity
public interface FIBFile extends FIBWidget {

	public static enum  FileMode
	{
		OpenMode { @Override
			public int getMode() { return JFileChooser.OPEN_DIALOG; } },
			SaveMode { @Override
				public int getMode() { return JFileChooser.SAVE_DIALOG; } };
				public abstract int getMode();
	}

	public FileMode getMode();

	public void setMode(FileMode mode);

	public String getFilter();

	public void setFilter(String filter);

	public String getTitle();

	public void setTitle(String title);

	public boolean isDirectory();

	public void setDirectory(boolean isDirectory);

	public File getDefaultDirectory();

	public void setDefaultDirectory(File defaultDirectory);

	public Integer getColumns();

	public void setColumns(Integer columns);

}
