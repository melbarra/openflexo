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
package org.openflexo.view.menu;

/*
 * FlexoMenuItem.java
 * Project WorkflowEditor
 * 
 * Created by benoit on Mar 12, 2004
 */

import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoModelObject;
import org.openflexo.foundation.action.FlexoActionSource;
import org.openflexo.foundation.action.FlexoActionType;
import org.openflexo.foundation.action.FlexoGUIAction;
import org.openflexo.localization.FlexoLocalization;
import org.openflexo.logging.FlexoLogger;
import org.openflexo.selection.SelectionManager;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.controller.SelectionManagingController;

/**
 * Give a shortcut to the item and register the action near the
 * FlexoMainController
 * 
 * @author benoit
 */
public class FlexoMenuItem extends JMenuItem implements FlexoActionSource
{

    private static final Logger logger = FlexoLogger.getLogger(FlexoMenuItem.class.getPackage().getName());

    private final FlexoController _controller;

    protected AbstractAction _action;

    public FlexoMenuItem(FlexoController controller,String unlocalizedMenuName) {
    	super();
    	_controller = controller;
    	setText(FlexoLocalization.localizedForKey(unlocalizedMenuName,this));
    }
    
    public FlexoMenuItem(AbstractAction action, String flexoActionName, KeyStroke accelerator, FlexoController controller,
            boolean localizeActionName)
    {
        super(action);
        _controller = controller;
        _action = action;
        if (accelerator != null) {
            setAccelerator(accelerator);
            _controller.registerActionForKeyStroke(action, accelerator);
        }
        if (localizeActionName) {
			setText(FlexoLocalization.localizedForKey(flexoActionName, this));
		} else {
			setText(flexoActionName);
		}
    }

    public FlexoMenuItem(AbstractAction action, String flexoActionName, KeyStroke accelerator, Icon icon, FlexoController controller)
    {
        this(action, flexoActionName, accelerator, controller, true);
        setIcon(icon);
    }

    public FlexoMenuItem(FlexoActionType actionType, Icon icon, FlexoController controller)
    {
    	this(actionType,controller);
        setIcon(icon);
    }

    public FlexoMenuItem(FlexoActionType actionType, FlexoController controller)
    {
        super(actionType);
        _controller = controller;
        _action = actionType;
        if (actionType.getKeyStroke() != null) {
            setAccelerator(actionType.getKeyStroke());
        }
		if (controller.getEditor().getEnabledIconFor(actionType) != null) {
			setIcon(controller.getEditor().getEnabledIconFor(actionType));
		}
		if (controller.getEditor().getDisabledIconFor(actionType) != null) {
			setDisabledIcon(controller.getEditor().getDisabledIconFor(actionType));
		}		
        setText(FlexoLocalization.localizedForKey(actionType.getUnlocalizedName(), this));
    }

    @Override
	public FlexoModelObject getFocusedObject()
    {
        if (_controller instanceof SelectionManagingController) {
            return (((SelectionManagingController) _controller).getSelectionManager().getLastSelectedObject());
        }
        return null;
    }

    @Override
	public Vector<FlexoModelObject> getGlobalSelection()
    {
        if (_controller instanceof SelectionManagingController) {
            return (((SelectionManagingController) _controller).getSelectionManager().getSelection());
        }
        return null;
    }

    @Override
	public FlexoEditor getEditor()
    {
        return _controller.getEditor();
    }

    private SelectionManager getSelectionManager()
    {
        if (_controller instanceof SelectionManagingController) {
			return ((SelectionManagingController) _controller).getSelectionManager();
		}
        return null;
    }

    /**
     * 
     */
    public void itemWillShow()
    {
    	if (_action instanceof FlexoGUIAction) {
    		setEnabled(true);
    		return;
    	}
        if ((_action instanceof FlexoActionType) && (getSelectionManager() != null)) {
            if (((getFocusedObject()==null) || (getFocusedObject().getActionList().indexOf((_action)) > -1))) {
				setEnabled(((FlexoActionType) _action).isEnabled(getFocusedObject(), getGlobalSelection(), _controller.getEditor()));
			} else {
				setEnabled(false);
			}
        }
    }

}
