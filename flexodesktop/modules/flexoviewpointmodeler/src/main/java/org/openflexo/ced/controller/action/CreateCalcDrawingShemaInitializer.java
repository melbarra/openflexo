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
package org.openflexo.ced.controller.action;

import java.awt.event.ActionEvent;
import java.util.logging.Logger;

import javax.swing.Icon;

import org.openflexo.ced.CEDCst;
import org.openflexo.ced.controller.CEDController;
import org.openflexo.fib.controller.FIBDialog;
import org.openflexo.fib.controller.FIBController.Status;
import org.openflexo.foundation.action.FlexoActionFinalizer;
import org.openflexo.foundation.action.FlexoActionInitializer;
import org.openflexo.foundation.viewpoint.action.CreateCalcDrawingShema;
import org.openflexo.icon.VPMIconLibrary;
import org.openflexo.view.controller.ActionInitializer;
import org.openflexo.view.controller.ControllerActionInitializer;


public class CreateCalcDrawingShemaInitializer extends ActionInitializer {

	private static final Logger logger = Logger.getLogger(ControllerActionInitializer.class.getPackage().getName());

	CreateCalcDrawingShemaInitializer(CEDControllerActionInitializer actionInitializer)
	{
		super(CreateCalcDrawingShema.actionType,actionInitializer);
	}

	@Override
	protected CEDControllerActionInitializer getControllerActionInitializer() 
	{
		return (CEDControllerActionInitializer)super.getControllerActionInitializer();
	}

	@Override
	public CEDController getController()
	{
		return (CEDController)super.getController();
	}
	
	@Override
	protected FlexoActionInitializer<CreateCalcDrawingShema> getDefaultInitializer() 
	{
		return new FlexoActionInitializer<CreateCalcDrawingShema>() {
			@Override
			public boolean run(ActionEvent e, CreateCalcDrawingShema action)
			{
				
				FIBDialog dialog = FIBDialog.instanciateComponent(
						CEDCst.CREATE_CALC_DRAWING_SHEMA_DIALOG_FIB,
						action, null, true);
				return (dialog.getStatus() == Status.VALIDATED);
			}
		};
	}
	

	@Override
	protected FlexoActionFinalizer<CreateCalcDrawingShema> getDefaultFinalizer() 
	{
		return new FlexoActionFinalizer<CreateCalcDrawingShema>() {
			@Override
			public boolean run(ActionEvent e, CreateCalcDrawingShema action)
			{
				getController().setCurrentEditedObjectAsModuleView(action.getNewShema(),getController().CALC_PERSPECTIVE);
				return true;
			}
		};
	}

	@Override
	protected Icon getEnabledIcon() 
	{
		return VPMIconLibrary.EXAMPLE_DIAGRAM_ICON;
	}

}
