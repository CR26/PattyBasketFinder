/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui.datatypes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/** @author Christian Roth */
public class GraphPanel extends JPanel {

	private int[] data;
	private int basket;
	private int leftBorder;
	private int rightBorder;
	private int average;
	final int PAD = 20;

	public GraphPanel() {
		this.basket = 0;
		this.leftBorder = 0;
		this.rightBorder = 0;
		this.average = 0;
	}

	public GraphPanel(int[] data) {
		this.basket = 0;
		this.leftBorder = 0;
		this.rightBorder = data.length;
		this.average = 0;
		this.data = data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public void setBasket(int basket) {
		this.basket = basket;
	}

	public void setLeftBorder(int left) {
		this.leftBorder = left;
	}

	public void setRightBorder(int right) {
		this.rightBorder = right;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int w = getWidth();
		int h = getHeight();
		// Draw ordinate.
		g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
		// Draw abcissa.
		g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));

		double xInc = (double) (w - 2 * PAD) / (data.length - 1);
		double scale = (double) (h - 2 * PAD) / getMax();
		// Mark data points.
		g2.setPaint(Color.red);
		for (int i = 0; i < data.length; i++) {
			double x = PAD + i * xInc;
			double y = h - PAD - scale * data[i];
			g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
		}

		// LeftBorder:
		g2.setPaint(Color.black);
		g2.draw(new Line2D.Double(PAD + leftBorder * xInc, PAD, PAD + leftBorder * xInc, h - PAD));
		// RightBorder:
		g2.setPaint(Color.black);
		g2.draw(new Line2D.Double(PAD + rightBorder * xInc, PAD, PAD + rightBorder * xInc, h - PAD));
		// BasketPosition:
		g2.setPaint(Color.green);
		g2.draw(new Line2D.Double(PAD + basket * xInc, PAD, PAD + basket * xInc, h - PAD));
		// Average:
		g2.setPaint(Color.blue);
		g2.draw(new Line2D.Double(PAD, h - PAD - scale * average, w - PAD, h - PAD - scale * average));
	}

	private int getMax() {
		int max = -Integer.MAX_VALUE;
		for (int i = 0; i < data.length; i++) {
			if (data[i] > max)
				max = data[i];
		}
		return max;
	}
}
