/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui.mainelements;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.hslu.pren30.dataAccess.config.Config;
import ch.hslu.pren30.gui.ButtonListener;

/** @author Christian Roth */
public class LeftPanel extends JPanel {

	private JPanel itemPanel;

	private JTextField row;
	private JButton showRowButton;

	private JTextField leftBorder;
	private JTextField rightBorder;
	private JButton showBorderButton;

	private JLabel basketPosition;
	private JButton showBasketPositionButton;

	private final ButtonListener buttonListener;

	public LeftPanel(ButtonListener bl) {
		super();
		super.setPreferredSize(new Dimension(160, 640));
		buttonListener = bl;
		initializeComponents();
	}

	private void initializeComponents() {
		itemPanel = new JPanel();

		itemPanel.setLayout(new GridLayout(0, 2));

		itemPanel.add(new JLabel("row:"));
		row = new JTextField(Config.getInstance().getMiddleRow());
		itemPanel.add(row);
		itemPanel.add(new JLabel());
		showRowButton = new JButton("show");
		showRowButton.addActionListener(buttonListener);
		showRowButton.setActionCommand("ShowRow");
		itemPanel.add(showRowButton);

		itemPanel.add(new JLabel());
		itemPanel.add(new JLabel());

		itemPanel.add(new JLabel("left border:"));
		leftBorder = new JTextField(Config.getInstance().getLeftBorder());
		itemPanel.add(leftBorder);
		itemPanel.add(new JLabel("right border:"));
		rightBorder = new JTextField(Config.getInstance().getRightBorder());
		itemPanel.add(rightBorder);
		itemPanel.add(new JLabel());
		showBorderButton = new JButton("show");
		showBorderButton.addActionListener(buttonListener);
		showBorderButton.setActionCommand("ShowBorders");
		itemPanel.add(showBorderButton);

		itemPanel.add(new JLabel());
		itemPanel.add(new JLabel());
		itemPanel.add(new JLabel());
		itemPanel.add(new JLabel());

		itemPanel.add(new JLabel("basket position:"));
		basketPosition = new JLabel("-");
		itemPanel.add(basketPosition);
		itemPanel.add(new JLabel());
		showBasketPositionButton = new JButton("find basket");
		showBasketPositionButton.addActionListener(buttonListener);
		showBasketPositionButton.setActionCommand("FindBasket");
		itemPanel.add(showBasketPositionButton);

		super.add(itemPanel);
	}

	public int getRow() {
		return Integer.parseInt(row.getText());
	}

	public String getBasketPosition() {
		return basketPosition.getText();
	}

	public void setBasketPosition(int pos) {
		basketPosition.setText("" + pos);
	}

	public int getLeftBorder() {
		return Integer.parseInt(leftBorder.getText());
	}

	public int getRigthBorder() {
		return 640 - Integer.parseInt(rightBorder.getText());
	}
}
