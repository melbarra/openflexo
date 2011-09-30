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

import org.openflexo.localization.Language;
import org.openflexo.localization.LocalizedDelegate;
import org.openflexo.model.annotations.ModelEntity;

@ModelEntity
public interface FIBLocalizedDictionary extends FIBModelObject, LocalizedDelegate {

	public static final String COMPONENT = "component";

	public FIBComponent getComponent();

	public void setComponent(FIBComponent component);

	public List<FIBLocalizedEntry> getEntries();

	public void setEntries(List<FIBLocalizedEntry> someEntries);

	public void addToEntries(FIBLocalizedEntry entry);

	public void removeFromEntries(FIBLocalizedEntry entry);

	public void append(FIBLocalizedDictionary aDict);

	public String getDefaultValue(String key, Language language);

	public void setLocalizedForKeyAndLanguage(String key, String value, Language language);

	@ModelEntity
	public interface DynamicEntry
	{

		public String getEnglish();

		public void setEnglish(String value);

		public String getFrench();

		public void setFrench(String value);

		public String getDutch();

		public void setDutch(String value);

	}

	public void refresh();

	public DynamicEntry addEntry();

	public void deleteEntry(DynamicEntry entry);

	public void beginSearchNewLocalizationEntries();

	public void endSearchNewLocalizationEntries();

}
