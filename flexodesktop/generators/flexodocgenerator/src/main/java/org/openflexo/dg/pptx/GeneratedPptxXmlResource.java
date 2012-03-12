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
package org.openflexo.dg.pptx;
/**
 * MOS
 * @author MOSTAFA
 * TODO_MOS
 */

import org.openflexo.dg.rm.PptxXmlFileResource;
import org.openflexo.foundation.cg.generator.GeneratedCodeResult;
import org.openflexo.foundation.ptoc.PSlide;

public class GeneratedPptxXmlResource extends GeneratedCodeResult
{
	public GeneratedPptxXmlResource(String name)
	{
		super(name);
	}
	
	public void addCode(PptxTemplatesEnum template, String content)
	{
		addCode(template.toString(), content);
	}
	
	//SUPPR supprimer
	public void addSlideCode(PptxTemplatesEnum template,PptxXmlFileResource resource, String content)
	{
		addCode(resource.getFileName(), content);
	}
}