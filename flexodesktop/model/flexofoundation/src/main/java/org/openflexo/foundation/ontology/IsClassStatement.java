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
package org.openflexo.foundation.ontology;

import java.util.logging.Logger;

import com.hp.hpl.jena.rdf.model.Statement;

public class IsClassStatement extends IsAStatement {

	private static final Logger logger = Logger.getLogger(IsClassStatement.class.getPackage().getName());

	public static final String CLASS_URI = "http://www.w3.org/2002/07/owl#Class";
	
	public IsClassStatement(OntologyObject subject, Statement s)
	{
		super(subject,s);
	}

	@Override
	public String getClassNameKey()
	{
		return "is_class_statement";
	}

	@Override
	public String getFullyQualifiedName()
	{
		return "IsClassStatement: "+getStatement();
	}


	@Override
	public String toString() 
	{
		return getSubject().getName()+" is a Class";
	}

}
