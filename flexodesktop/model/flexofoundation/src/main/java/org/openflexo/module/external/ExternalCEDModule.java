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
package org.openflexo.module.external;

import javax.swing.JComponent;

import org.openflexo.foundation.viewpoint.ExampleDrawingShema;
import org.openflexo.foundation.viewpoint.ViewPointPalette;



/**
 * External view of the module.
 * Declare here all features that you want to be available from outside
 * of this module.
 * Put here as less methods as possible.
 * 
 * @author yourname
 */
public interface ExternalCEDModule extends ExternalModule
{
    public JComponent createScreenshotForShema(ExampleDrawingShema shema);
    public JComponent createScreenshotForPalette(ViewPointPalette palette);
    public float getScreenshotQuality();
    public void finalizeScreenshotGeneration();

}
