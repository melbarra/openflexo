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

import java.awt.GridBagConstraints;



public interface GridBagLayoutConstraints extends ComponentConstraints {

	public static final String ANCHOR = "anchor";
	public static final String FILL = "fill";
	public static final String GRID_X = "gridX";
	public static final String GRID_Y = "gridY";
	public static final String GRID_WIDTH = "gridWidth";
	public static final String GRID_HEIGHT = "gridHeight";
	public static final String INSETS_TOP = "insetsTop";
	public static final String INSETS_BOTTOM = "insetsBottom";
	public static final String INSETS_LEFT = "insetsLeft";
	public static final String INSETS_RIGHT = "insetsRight";
	public static final String WEIGHT_X = "weightX";
	public static final String WEIGHT_Y = "weightY";
	public static final String PAD_X = "padX";
	public static final String PAD_Y = "padY";

	public static enum AnchorType
	{
		north { @Override
			public int getAnchor() { return GridBagConstraints.NORTH; } },
			south { @Override
				public int getAnchor() { return GridBagConstraints.SOUTH; } },
				east { @Override
					public int getAnchor() { return GridBagConstraints.EAST; } },
					west { @Override
						public int getAnchor() { return GridBagConstraints.WEST; } },
						center { @Override
							public int getAnchor() { return GridBagConstraints.CENTER; } },
							north_east { @Override
								public int getAnchor() { return GridBagConstraints.NORTHEAST; } },
								north_west { @Override
									public int getAnchor() { return GridBagConstraints.NORTHWEST; } },
									south_east { @Override
										public int getAnchor() { return GridBagConstraints.SOUTHEAST; } },
										south_west { @Override
											public int getAnchor() { return GridBagConstraints.SOUTHWEST; } };
											public abstract int getAnchor();
	}

	public static enum FillType
	{
		none { @Override
			public int getFill() { return GridBagConstraints.NONE; } },
			horizontal { @Override
				public int getFill() { return GridBagConstraints.HORIZONTAL; } },
				vertical { @Override
					public int getFill() { return GridBagConstraints.VERTICAL; } },
					both { @Override
						public int getFill() { return GridBagConstraints.BOTH; } };
						public abstract int getFill();
	}

	public AnchorType getAnchor();

	public void setAnchor(AnchorType location);

	public FillType getFill();

	public void setFill(FillType fill);

	public int getGridX();

	public void setGridX(int gridX);

	public boolean getGridXRelative();

	public void setGridXRelative(boolean flag);

	public int getGridY();

	public void setGridY(int gridY);

	public boolean getGridYRelative();

	public void setGridYRelative(boolean flag);

	public int getGridWidth();

	public void setGridWidth(int gridWidth);

	public boolean getGridWidthRelative();

	public void setGridWidthRelative(boolean flag);

	public boolean getGridWidthRemainder();

	public void setGridWidthRemainder(boolean flag);

	public int getGridHeight();

	public void setGridHeight(int gridHeight);

	public boolean getGridHeightRelative();

	public void setGridHeightRelative(boolean flag);

	public boolean getGridHeightRemainder();

	public void setGridHeightRemainder(boolean flag);

	public double getWeightX();

	public void setWeightX(double weightX);

	public double getWeightY();

	public void setWeightY(double weightY);

	public int getPadX();

	public void setPadX(int padX);

	public int getPadY();

	public void setPadY(int padY);

	public int getInsetsTop();

	public void setInsetsTop(int insetsTop);

	public int getInsetsBottom();

	public void setInsetsBottom(int insetsBottom);

	public int getInsetsLeft();

	public void setInsetsLeft(int insetsLeft);

	public int getInsetsRight();

	public void setInsetsRight(int insetsRight);

}
