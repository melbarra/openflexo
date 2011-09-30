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

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ScrollPaneConstants;
import javax.swing.tree.TreeNode;

import org.openflexo.antar.binding.BindingDefinition;
import org.openflexo.antar.binding.BindingDefinition.BindingDefinitionType;
import org.openflexo.model.annotations.Adder;
import org.openflexo.model.annotations.Getter;
import org.openflexo.model.annotations.Getter.Cardinality;
import org.openflexo.model.annotations.ModelEntity;
import org.openflexo.model.annotations.Remover;
import org.openflexo.model.annotations.Setter;
import org.openflexo.model.annotations.XMLAttribute;
import org.openflexo.model.annotations.XMLElement;

@ModelEntity(isAbstract = true)
public interface FIBComponent extends FIBModelObject, TreeNode {

	public static final Color SECONDARY_SELECTION_COLOR = new Color(173, 215, 255);
	public static final Color DISABLED_COLOR = Color.GRAY;

	public static final String INDEX = "index";
	public static final String DATA = "data";
	public static final String VISIBLE = "visible";
	public static final String DATA_CLASS = "dataClass";
	public static final String CONTROLLER_CLASS = "controllerClass";
	public static final String FONT = "font";
	public static final String BACKGROUND_COLOR = "backgroundColor";
	public static final String FOREGROUND_COLOR = "foregroundColor";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String MIN_WIDTH = "minWidth";
	public static final String MIN_HEIGHT = "minHeight";
	public static final String MAX_WIDTH = "maxWidth";
	public static final String MAX_HEIGHT = "maxHeight";
	public static final String USE_SCROLLBAR = "useScrollBar";
	public static final String HORIZONTAL_SCROLLBAR_POLICY = "horizontalScrollbarPolicy";
	public static final String VERTICAL_SCROLLBAR_POLICY = "verticalScrollbarPolicy";
	public static final String CONSTRAINTS = "constraints";
	public static final String EXPLICIT_DEPENDENCIES = "explicitDependencies";
	public static final String LOCALIZED_DICTIONARY = "localizedDictionary";
	public static final String PARENT = "parent";


	public static final BindingDefinition VISIBLE_BINDING_DEFINITION = new BindingDefinition("visible", Boolean.class,
			BindingDefinitionType.GET, false);

	public BindingDefinition getDataBindingDefinition();

	public static enum Parameters implements FIBModelAttribute
	{
		index,
		data,
		visible,
		dataClass,
		controllerClass,
		font,
		backgroundColor,
		foregroundColor,
		width,
		height,
		minWidth,
		minHeight,
		maxWidth,
		maxHeight,
		useScrollBar,
		horizontalScrollbarPolicy,
		verticalScrollbarPolicy,
		constraints,
		explicitDependancies
	}

	public static enum VerticalScrollBarPolicy
	{
		VERTICAL_SCROLLBAR_AS_NEEDED(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED), VERTICAL_SCROLLBAR_NEVER(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER), VERTICAL_SCROLLBAR_ALWAYS(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		private int policy;

		private VerticalScrollBarPolicy(int policy) {
			this.policy = policy;
		}

		public int getPolicy() {
			return policy;
		}
	}

	public static enum HorizontalScrollBarPolicy
	{
		HORIZONTAL_SCROLLBAR_AS_NEEDED(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED), HORIZONTAL_SCROLLBAR_NEVER(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER), HORIZONTAL_SCROLLBAR_ALWAYS(
						ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		private int policy;

		private HorizontalScrollBarPolicy(int policy) {
			this.policy = policy;
		}

		public int getPolicy() {
			return policy;
		}
	}

	@Override
	@Getter(id = PARENT, inverse=FIBContainer.SUB_COMPONENTS)
	public FIBContainer getParent();

	@Setter(id = PARENT)
	public void setParent(FIBContainer parent);

	@Getter(id = CONSTRAINTS, inverse = ComponentConstraints.COMPONENT)
	@XMLAttribute
	public ComponentConstraints getConstraints();

	@Setter(id=CONSTRAINTS)
	public void setConstraints(ComponentConstraints someConstraints);

	@Getter(id = LOCALIZED_DICTIONARY, inverse = FIBLocalizedDictionary.COMPONENT)
	@XMLElement
	public FIBLocalizedDictionary getLocalizedDictionary();

	@Setter(id = LOCALIZED_DICTIONARY)
	public void setLocalizedDictionary(FIBLocalizedDictionary localizedDictionary);

	@Getter(id = EXPLICIT_DEPENDENCIES, cardinality = Cardinality.LIST, inverse = FIBDependency.OWNER)
	@XMLElement
	public List<FIBDependency> getExplicitDependencies();

	@Setter(id = EXPLICIT_DEPENDENCIES)
	public void setExplicitDependencies(List<FIBDependency> explicitDependencies);

	@Adder(id = EXPLICIT_DEPENDENCIES)
	public void addToExplicitDependancies(FIBDependency p);

	@Remover(id = EXPLICIT_DEPENDENCIES)
	public void removeFromExplicitDependancies(FIBDependency p);

	@Getter(id = DATA)
	@XMLAttribute
	public DataBinding getData();

	@Setter(id = DATA)
	public void setData(DataBinding data);

	@Getter(id = VISIBLE, inverse = DataBinding.OWNER)
	@XMLAttribute
	public DataBinding getVisible();

	@Setter(id = VISIBLE)
	public void setVisible(DataBinding visible);

	@Getter(id = DATA_CLASS)
	@XMLAttribute
	public Class<?> getDataClass();

	@Setter(id = DATA_CLASS)
	public void setDataClass(Class<?> dataClass);

	@Getter(id = CONTROLLER_CLASS)
	@XMLAttribute
	public Class<?> getControllerClass();

	@Setter(id = CONTROLLER_CLASS)
	public void setControllerClass(Class<?> controllerClass);

	@Getter(id = FONT)
	@XMLAttribute
	public Font getFont();

	@Setter(id = FONT)
	public void setFont(Font font);

	@Getter(id = BACKGROUND_COLOR)
	@XMLAttribute
	public Color getBackgroundColor();

	@Setter(id = BACKGROUND_COLOR)
	public void setBackgroundColor(Color backgroundColor);

	@Getter(id = FOREGROUND_COLOR)
	@XMLAttribute
	public Color getForegroundColor();

	@Setter(id = FOREGROUND_COLOR)
	public void setForegroundColor(Color foregroundColor);

	@Getter(id = HORIZONTAL_SCROLLBAR_POLICY)
	@XMLAttribute
	public HorizontalScrollBarPolicy getHorizontalScrollbarPolicy();

	@Setter(id = HORIZONTAL_SCROLLBAR_POLICY)
	public void setHorizontalScrollbarPolicy(
			HorizontalScrollBarPolicy horizontalScrollbarPolicy);

	@Getter(id = VERTICAL_SCROLLBAR_POLICY)
	@XMLAttribute
	public VerticalScrollBarPolicy getVerticalScrollbarPolicy();

	@Setter(id = VERTICAL_SCROLLBAR_POLICY)
	public void setVerticalScrollbarPolicy(
			VerticalScrollBarPolicy verticalScrollbarPolicy);

	@Getter(id = WIDTH)
	@XMLAttribute
	public Integer getWidth();

	@Setter(id = WIDTH)
	public void setWidth(Integer width);

	@Getter(id = HEIGHT)
	@XMLAttribute
	public Integer getHeight();

	@Setter(id = HEIGHT)
	public void setHeight(Integer height);

	@Getter(id = MIN_WIDTH)
	@XMLAttribute
	public Integer getMinWidth();

	@Setter(id = MIN_WIDTH)
	public void setMinWidth(Integer minWidth);

	@Getter(id = MIN_HEIGHT)
	@XMLAttribute
	public Integer getMinHeight();

	@Setter(id = MIN_HEIGHT)
	public void setMinHeight(Integer minHeight);

	@Getter(id = MAX_WIDTH)
	@XMLAttribute
	public Integer getMaxWidth();

	@Setter(id = MAX_WIDTH)
	public void setMaxWidth(Integer maxWidth);

	@Getter(id = MAX_HEIGHT)
	@XMLAttribute
	public Integer getMaxHeight();

	@Setter(id = MAX_HEIGHT)
	public void setMaxHeight(Integer maxHeight);

	@Getter(id = USE_SCROLLBAR)
	@XMLAttribute
	public boolean getUseScrollBar();

	@Setter(id = USE_SCROLLBAR)
	public void setUseScrollBar(boolean useScrollBar);

	public boolean isRootComponent();

	@Override
	public FIBComponent getRootComponent();

	public void updateBindingModel();

	public List<FIBComponent> getNamedComponents();

	public FIBComponent getComponentNamed(String name);

	public List<FIBComponent> retrieveAllSubComponents();

	public Iterator<FIBComponent> subComponentIterator();

	public List<FIBComponent> getMayDepends();

	public Iterator<FIBComponent> getMayDependsIterator();

	public Iterator<FIBComponent> getMayAltersIterator();

	public void declareDependantOf(FIBComponent aComponent) throws DependancyLoopException;

	public static class DependancyLoopException extends Exception {
		private final Vector<FIBComponent> dependancies;

		public DependancyLoopException(Vector<FIBComponent> dependancies) {
			this.dependancies = dependancies;
		}

		@Override
		public String getMessage() {
			return "DependancyLoopException: " + dependancies;
		}
	}

	public String getIdentifier();

	public Type getDataType();

	public Type getDefaultDataClass();

	// Default behaviour: only data is managed
	public Type getDynamicAccessType();

	public void clearParameters();

	public Font retrieveValidFont();

	public Color retrieveValidForegroundColor();

	public Color retrieveValidBackgroundColor();

	public boolean getHasSpecificFont();

	public void setHasSpecificFont(boolean aFlag);

	public boolean getHasSpecificBackgroundColor();

	public void setHasSpecificBackgroundColor(boolean aFlag);

	public boolean getHasSpecificForegroundColor();

	public void setHasSpecificForegroundColor(boolean aFlag);

	@Override
	public void addToParameters(FIBParameter p);

	public boolean definePreferredDimensions();

	public void setDefinePreferredDimensions(boolean definePreferredDimensions);

	public boolean defineMaxDimensions();

	public void setDefineMaxDimensions(boolean defineMaxDimensions);

	public boolean defineMinDimensions();

	public void setDefineMinDimensions(boolean defineMinDimensions);

	public FIBDependency createNewExplicitDependancy();

	public void deleteExplicitDependancy(FIBDependency p);

	public FIBLocalizedDictionary retrieveFIBLocalizedDictionary();


}
