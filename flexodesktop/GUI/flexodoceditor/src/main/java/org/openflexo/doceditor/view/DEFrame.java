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
package org.openflexo.doceditor.view;

import java.awt.Font;
import java.util.logging.Logger;

import javax.swing.JPanel;

import org.openflexo.ColorCst;
import org.openflexo.FlexoCst;
import org.openflexo.doceditor.DECst;
import org.openflexo.doceditor.controller.DEController;
import org.openflexo.doceditor.menu.DEMenuBar;
import org.openflexo.doceditor.view.listener.DEKeyEventListener;
import org.openflexo.foundation.DataModification;
import org.openflexo.foundation.FlexoObservable;
import org.openflexo.view.FlexoFrame;


/**
 * Frame of Generator Module
 * 
 * @author bmangez
 */

public class DEFrame extends FlexoFrame
{

    private static final Logger logger = Logger.getLogger(DEFrame.class.getPackage().getName());

    // ==========================================================================
    // ============================= Static variables
    // ==================================
    // ==========================================================================

    public static final Font LABEL_FONT = new Font("Verdana", Font.PLAIN, 11);

    // ==========================================================================
    // ============================= Instance variables
    // =========================
    // ==========================================================================

    protected DEController _controller;

    protected DEMenuBar _menuBar;

    protected DEKeyEventListener _keyEventListener;

    protected JPanel dataModelClassPanel;

    // private CategorySelector categorySelector;

    // ==========================================================================
    // ============================= Constructor
    // ================================
    // ==========================================================================

    /**
     * Constructor for GeneratorFrame
     */
    public DEFrame(String title, DEController controller, DEKeyEventListener generatorKeyEventListener,
            DEMenuBar menuBar)
    {
        super(title, controller, generatorKeyEventListener, menuBar);
        setBackground(ColorCst.GUI_BACK_COLOR);
        _controller = controller;
        _menuBar = menuBar;
        _keyEventListener = generatorKeyEventListener;
        updateTitle();
        setSize(DECst.DEFAULT_DE_WIDTH, DECst.DEFAULT_DE_HEIGHT);
    }

    @Override
	public void update(FlexoObservable observable, DataModification dataModification)
    {
       super.update(observable,dataModification);
    }

    /*
     * protected void selectBrowserView() {
     * categorySelector.remove(metaClassPanel);
     * categorySelector.remove(dataModelClassPanel);
     * categorySelector.add(_browserView, BorderLayout.CENTER);
     * splitPane.setRightComponent(_content); }
     * 
     * protected void selectMetaClasses() {
     * categorySelector.remove(_browserView);
     * categorySelector.remove(dataModelClassPanel);
     * categorySelector.add(metaClassPanel, BorderLayout.CENTER);
     * splitPane.setRightComponent(_content); }
     * 
     * protected void selectDataModelClasses() {
     * categorySelector.remove(_browserView);
     * categorySelector.remove(metaClassPanel);
     * categorySelector.add(dataModelClassPanel, BorderLayout.CENTER);
     * splitPane.setRightComponent(_content); }
     */
    /*
     * private class CategorySelector extends JPanel implements ActionListener {
     * private Vector categoryList;
     * 
     * JComboBox selector;
     * 
     * JComboBox genType;
     * 
     * public CategorySelector() { super(new BorderLayout());
     * setBackground(FlexoCst.GUI_BACK_COLOR); categoryList = new Vector();
     * categoryList.add("Components"); categoryList.add("MetaClasses");
     * categoryList.add("DataModel"); selector = new JComboBox(categoryList);
     * selector.addActionListener(this);
     * 
     * genType = new JComboBox(GenerationTarget.availableValues());
     * genType.setRenderer(new GenerationTargetListCellRenderer());
     * genType.setSelectedItem(_generatorController.getGenerationTarget());//
     * Calling // this // line // causes // the // component // to // call //
     * actionPerformed // on // every // ActionListener
     * genType.addActionListener(this);// Do not move this line above the //
     * setSelectedItem (otherwise, it // will launch the actionPerformed JLabel
     * target = new JLabel();
     * target.setText(FlexoLocalization.localizedForKey("target", target) +
     * ":");
     * 
     * JPanel targetPanel = new JPanel(new FlowLayout());
     * targetPanel.add(target); targetPanel.add(genType);
     * 
     * JPanel top = new JPanel(new BorderLayout()); top.add(selector,
     * BorderLayout.SOUTH); top.add(targetPanel, BorderLayout.NORTH);
     * 
     * add(top, BorderLayout.NORTH); /* generationType =
     * Generator.getGenerationTypes(_generatorController.getProject()); genType =
     * new JComboBox(generationType); add(genType,BorderLayout.CENTER);
     * add(_browserView,BorderLayout.SOUTH);
     */

    /*
     * }
     * 
     * public void actionPerformed(ActionEvent arg0) { if
     * (selector.getSelectedItem().equals("Components")) { selectBrowserView(); }
     * else if (selector.getSelectedItem().equals("MetaClasses")) {
     * selectMetaClasses(); } else if
     * (selector.getSelectedItem().equals("DataModel")) {
     * selectDataModelClasses(); }
     * 
     * _generatorController.setGenerationTarget((GenerationTarget)
     * genType.getSelectedItem()); } }
     */
}
