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

import org.openflexo.fib.model.FIBPanel.Layout;
import org.openflexo.model.annotations.Adder;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.Getter.Cardinality;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.Remover;
import org.openflexo.model.annotations.Setter;
import org.openflexo.model.annotations.XMLElement;

@ModelEntity
public interface FIBContainer extends FIBComponent {

	public static final String SUB_COMPONENTS="subComponents";

	public static enum Parameters implements FIBModelAttribute
	{
		subComponents
	}

	@Getter(id = SUB_COMPONENTS, cardinality = Cardinality.LIST)
	@XMLElement
	public List<FIBComponent> getSubComponents();

	@Setter(id = SUB_COMPONENTS)
	public void setSubComponents(List<FIBComponent> someComponents);

	@Adder(id = SUB_COMPONENTS)
	public void addToSubComponents(FIBComponent aComponent);

	public void addToSubComponents(FIBComponent aComponent, ComponentConstraints someConstraints);

	public FIBComponent getSubComponentNamed(String name);

	@Remover(id=SUB_COMPONENTS)
	public void removeFromSubComponents(FIBComponent aComponent);

	public void removeFromSubComponentsNoNotification(FIBComponent aComponent);



	public void append(FIBContainer container);

	// Default layout is built-in: only FIBPanel manage a custom layout,
	// where this method is overriden
	public Layout getLayout();

	// Not permitted since default layout is built-in: only FIBPanel
	// manage a custom layout, where this method is overriden
	public void setLayout(Layout layout);

	public void oldFinalizeDeserialization();

	public void componentFirst(FIBComponent c);

	public void componentUp(FIBComponent c);

	public void componentDown(FIBComponent c);

	public void componentLast(FIBComponent c);

	public void recursivelyReorderComponents();

	public void reorderComponents();

}
