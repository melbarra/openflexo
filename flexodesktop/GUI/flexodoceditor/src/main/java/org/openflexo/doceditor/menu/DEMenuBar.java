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
package org.openflexo.doceditor.menu;

import org.openflexo.doceditor.controller.DEController;
import org.openflexo.module.Module;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.menu.FileMenu;
import org.openflexo.view.menu.FlexoMenuBar;


/**
 * @author gpolet
 */
public class DEMenuBar extends FlexoMenuBar
{

    public DEFileMenu _fileMenu;
    
    public DEMenuBar(DEController controller)
    {
        super(controller, Module.DE_MODULE);
    }
    
    public DEMenuBar(DEController controller, Module module)
    {
        super(controller, module);
    }

    /**
     * Build if required and return CG 'File' menu.
     * This method overrides the default one defined on superclass
     * 
     * @param controller
     * @return a GeneratorFileMenu instance
     */
    @Override
	public FileMenu getFileMenu(FlexoController controller)
    {
        if (_fileMenu == null) {
            _fileMenu = new DEFileMenu((DEController)controller);
        }
        return _fileMenu;
    }

}
