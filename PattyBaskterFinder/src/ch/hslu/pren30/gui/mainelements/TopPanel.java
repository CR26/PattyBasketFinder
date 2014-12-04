/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui.mainelements;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.hslu.pren30.gui.ButtonListener;

/** @author Patrik */
public class TopPanel extends JPanel {

	private JPanel itemPanel;
	private JButton pictureButton;
	private JButton graphButton;

	private final ButtonListener buttonListener;

	public TopPanel(ButtonListener bl) {
		super();
		super.setPreferredSize(new Dimension(1280, 40));
		buttonListener = bl;
		initializeComponents();
	}

	private void initializeComponents() {
		itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(1, 8));

		pictureButton = new JButton("picture");
		pictureButton.addActionListener(buttonListener);
		pictureButton.setActionCommand("PictureView");
		itemPanel.add(pictureButton);

		graphButton = new JButton("graph");
		graphButton.addActionListener(buttonListener);
		graphButton.setActionCommand("GraphView");
		itemPanel.add(graphButton);

		super.add(itemPanel);
	}
}
