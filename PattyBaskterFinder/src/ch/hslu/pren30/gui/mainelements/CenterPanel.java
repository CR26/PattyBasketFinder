/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui.mainelements;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JPanel;

import ch.hslu.pren30.gui.datatypes.GraphPanel;
import ch.hslu.pren30.gui.datatypes.ImagePanel;

/** @author Christian Roth */
public class CenterPanel extends JPanel {
	private ImagePanel image;
	private GraphPanel graph;

	public CenterPanel() {
		super();
		super.setLayout(new GridLayout(1, 1));
		super.setPreferredSize(new Dimension(960, 640));
		initializeComponents();
	}

	private void initializeComponents() {
		image = new ImagePanel();
		graph = new GraphPanel();
	}

	public void showImage(Image newImage) {
		super.removeAll();
		super.revalidate();
		image.changeImage(newImage);
		image.repaint();
		super.add(image);
		super.repaint();
	}

	public void showGraph(int[] data, int position, int average, int left, int right) {
		super.removeAll();
		super.revalidate();
		graph.setData(data);
		graph.setBasket(position);
		graph.setLeftBorder(left);
		graph.setRightBorder(right);
		graph.setAverage(average);
		graph.repaint();
		super.add(graph);
		super.repaint();
	}
}
