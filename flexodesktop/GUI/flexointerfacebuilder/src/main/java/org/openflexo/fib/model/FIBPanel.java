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
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

public interface FIBPanel extends FIBContainer {

	public static enum Parameters implements FIBModelAttribute
	{
		layout,
		flowAlignment,
		boxLayoutAxis,
		hGap,
		vGap,
		cols,
		rows,
		border,
		borderColor,
		borderTitle,
		borderTop,
		borderBottom,
		borderLeft,
		borderRight,
		protectContent
	}


	public static enum Layout
	{
		none,
		flow,
		border,
		grid,
		box,
		twocols,
		gridbag
	}

	public static enum FlowLayoutAlignment
	{
		LEFT {
			@Override
			public int getAlign() {
				return FlowLayout.LEFT;
			}
		},
		RIGHT {
			@Override
			public int getAlign() {
				return FlowLayout.RIGHT;
			}
		},
		CENTER {
			@Override
			public int getAlign() {
				return FlowLayout.CENTER;
			}
		},
		LEADING {
			@Override
			public int getAlign() {
				return FlowLayout.LEADING;
			}
		},
		TRAILING {
			@Override
			public int getAlign() {
				return FlowLayout.TRAILING;
			}
		};

		public abstract int getAlign();
	}

	public static enum BoxLayoutAxis
	{
		X_AXIS {
			@Override
			public int getAxis() {
				return BoxLayout.X_AXIS;
			}
		},
		Y_AXIS {
			@Override
			public int getAxis() {
				return BoxLayout.Y_AXIS;
			}
		};

		public abstract int getAxis();
	}

	public static enum Border
	{
		empty,
		line,
		etched,
		raised,
		lowered,
		titled
	}



	public Border getBorder();

	public void setBorder(Border border);

	public Integer getHGap();

	public void setHGap(Integer hGap);

	public Integer getVGap();

	public void setVGap(Integer vGap);

	public FlowLayoutAlignment getFlowAlignment();

	public void setFlowAlignment(FlowLayoutAlignment flowAlignment);

	public Integer getCols();

	public void setCols(Integer cols);

	public Integer getRows();

	public void setRows(Integer rows);

	public Color getBorderColor();

	public void setBorderColor(Color borderColor);

	public String getBorderTitle();

	public void setBorderTitle(String borderTitle);

	public BoxLayoutAxis getBoxLayoutAxis();

	public void setBoxLayoutAxis(BoxLayoutAxis boxLayoutAxis);

	public boolean getProtectContent();

	public void setProtectContent(boolean protectContent);

	public Integer getBorderTop();

	public void setBorderTop(Integer borderTop);

	public Integer getBorderBottom();

	public void setBorderBottom(Integer borderBottom);

	public Integer getBorderLeft();

	public void setBorderLeft(Integer borderLeft);

	public Integer getBorderRight();

	public void setBorderRight(Integer borderRight);

}
