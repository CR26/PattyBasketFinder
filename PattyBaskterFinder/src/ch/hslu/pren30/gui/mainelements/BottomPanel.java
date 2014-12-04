/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui.mainelements;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/** @author Christian Roth */
public class BottomPanel extends JPanel {
	private JLabel label;

	public BottomPanel() {
		super();
		super.setPreferredSize(new Dimension(1280, 40));
		initializeComponents();
	}

	private void initializeComponents() {
		label = new JLabel("bottom");
		super.add(label);
	}

	public void changeLabel(String Msg) {
		label.setText(Msg);
	}
}
