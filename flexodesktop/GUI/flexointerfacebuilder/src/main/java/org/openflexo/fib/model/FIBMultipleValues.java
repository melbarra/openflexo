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

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Logger;

import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.antar.binding.GenericArrayTypeImpl;
import org.openflexo.antar.binding.ParameterizedTypeImpl;
import org.openflexo.antar.binding.TypeUtils;
import org.openflexo.antar.binding.WilcardTypeImpl;


public abstract class FIBMultipleValues extends FIBWidget {

	private static final Logger logger = Logger.getLogger(FIBMultipleValues.class.getPackage().getName());

	public static enum Parameters implements FIBModelAttribute
	{
		staticList,
		list,
		array,
		showIcon,
		showText,
		iteratorClass
	}

	public BindingDefinition LIST = new BindingDefinition("list", new ParameterizedTypeImpl(List.class, new WilcardTypeImpl(Object.class)) , BindingDefinitionType.GET, false)
	{
		@Override
		public Type getType() {
			return getListBindingType();
		}
	};
	public BindingDefinition ARRAY = new BindingDefinition("array", new GenericArrayTypeImpl(new WilcardTypeImpl(Object.class)), BindingDefinitionType.GET, false)
	{
		@Override
		public Type getType() {
			return getArrayBindingType();
		}
	};

	private BindingDefinition DATA = new BindingDefinition("data", Object.class, BindingDefinitionType.GET_SET, false) {
		@Override
		public Type getType() {
			return getDataType();
		};
	};

	public String staticList;

	private DataBinding list;
	private DataBinding array;

	private Class iteratorClass;
	private Class expectedIteratorClass;

	public boolean showIcon = false;
	public boolean showText = true;

	public FIBMultipleValues()
	{
	}

	private Type LIST_BINDING_TYPE;
	private Type ARRAY_BINDING_TYPE;

	private Type getListBindingType()
	{
		if (LIST_BINDING_TYPE == null) {
			LIST_BINDING_TYPE = new ParameterizedTypeImpl(List.class, new WilcardTypeImpl(getIteratorClass()));
		}
		return LIST_BINDING_TYPE;
	}

	private Type getArrayBindingType()
	{
		if (ARRAY_BINDING_TYPE == null) {
			ARRAY_BINDING_TYPE = new GenericArrayTypeImpl(new WilcardTypeImpl(getIteratorClass()));
		}
		return ARRAY_BINDING_TYPE;
	}


	public DataBinding getList()
	{
		if (list == null) {
			list = new DataBinding(this,Parameters.list,LIST);
		}
		return list;
	}

	public void setList(DataBinding list)
	{
		list.setOwner(this);
		list.setBindingAttribute(Parameters.list);
		list.setBindingDefinition(LIST);
		this.list = list;
	}

	public DataBinding getArray()
	{
		if (array == null) {
			array = new DataBinding(this,Parameters.array,ARRAY);
		}
		return array;
	}

	public void setArray(DataBinding array)
	{
		array.setOwner(this);
		array.setBindingAttribute(Parameters.array);
		array.setBindingDefinition(ARRAY);
		this.array = array;
	}

	@Override
	public void finalizeDeserialization()
	{
		super.finalizeDeserialization();
		if (list != null) {
			list.finalizeDeserialization();
		}
		if (array != null) {
			array.finalizeDeserialization();
		}
	}

	public Class getIteratorClass()
	{
		if (iteratorClass == null) {
			if (expectedIteratorClass != null) {
				return expectedIteratorClass;
			} else {
				return Object.class;
			}
		}
		return iteratorClass;
	}

	public void setIteratorClass(Class iteratorClass)
	{
		FIBAttributeNotification<Class> notification = requireChange(
				Parameters.iteratorClass, iteratorClass);
		if (notification != null) {
			LIST_BINDING_TYPE = null;
			ARRAY_BINDING_TYPE = null;
			this.iteratorClass = iteratorClass;
			hasChanged(notification);
		}
	}


	@Override
	public Type getDataType()
	{
		if (iteratorClass != null) {
			return iteratorClass;
		}
		return super.getDataType();
	}

	@Override
	public Type getFormattedObjectType()
	{
		if (iteratorClass != null) {
			return iteratorClass;
		}
		return getDataType();
	}

	@Override
	public BindingDefinition getDataBindingDefinition() {
		return DATA;
	}

	/*@Override
	public final Type getDefaultDataClass()
	{
		return getIteratorClass();
	}*/

	@Override
	public final Type getDefaultDataClass()
	{
		return Object.class;
	}

	@Override
	public void notifyBindingChanged(DataBinding binding)
	{
		//logger.info("******* notifyBindingChanged with "+binding);
		if (binding == getList()) {
			if (getList() != null && getList().getBinding() != null) {
				Type accessedType = getList().getBinding().getAccessedType();
				if (accessedType instanceof ParameterizedType
						&& ((ParameterizedType)accessedType).getActualTypeArguments().length > 0) {
					Class newIteratorClass = TypeUtils.getBaseClass(((ParameterizedType)accessedType).getActualTypeArguments()[0]);
					if (getIteratorClass() == null || !TypeUtils.isClassAncestorOf(newIteratorClass,getIteratorClass())) {
						setIteratorClass(newIteratorClass);
					}
				}
			}
		}
		else if (binding == getArray()) {
			if (getArray() != null && getArray().getBinding() != null) {
				Type accessedType = getArray().getBinding().getAccessedType();
				if (accessedType instanceof GenericArrayType) {
					Class newIteratorClass = TypeUtils.getBaseClass(((GenericArrayType)accessedType).getGenericComponentType());
					if (getIteratorClass() == null || !TypeUtils.isClassAncestorOf(newIteratorClass,getIteratorClass())) {
						setIteratorClass(newIteratorClass);
					}
				}
			}
		}
		else if (binding == getData()) {
			if (getData() != null && getData().getBinding() != null) {
				Type accessedType = getData().getBinding().getAccessedType();
				/*if (accessedType instanceof Class && ((Class)accessedType).isEnum()) {
					setIteratorClass((Class)accessedType);
				}*/
				if (accessedType instanceof Class) {
					expectedIteratorClass = (Class)accessedType;
				}

			}
		}

	}

}
