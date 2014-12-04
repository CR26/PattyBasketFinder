/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui.datatypes;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/** @author Christian Roth */
public class ImagePanel extends JPanel {

	private Image image;

	public ImagePanel(Image image) {
		this.image = image;
		this.setVisible(true);
	}

	public ImagePanel() {
		this.image = null;
	}

	public void changeImage(Image image) {
		this.image = image;
		this.setVisible(true);
		this.repaint();
		this.revalidate();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
