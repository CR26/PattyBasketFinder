/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui;

import static java.awt.Component.CENTER_ALIGNMENT;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import ch.hslu.pren30.finder.BusinessLogic;
import ch.hslu.pren30.gui.mainelements.BottomPanel;
import ch.hslu.pren30.gui.mainelements.CenterPanel;
import ch.hslu.pren30.gui.mainelements.LeftPanel;
import ch.hslu.pren30.gui.mainelements.RightPanel;
import ch.hslu.pren30.gui.mainelements.TopPanel;

/** @author Christian Roth */
public class MainWindow {

	private JFrame mainFrame;
	private GridBagLayout gridbag;

	private TopPanel theTopPanel;
	private CenterPanel theCenterPanel;
	private BottomPanel theBottomPanel;
	private LeftPanel theLeftPanel;
	private RightPanel theRightPanel;

	private final ButtonListener buttonListener;

	private final BusinessLogic businessLogic;

	public MainWindow(BusinessLogic businessLogic) {
		this.businessLogic = businessLogic;
		buttonListener = new ButtonListener(this);
		createMainFrame();
	}

	private void createMainFrame() {
		mainFrame = new JFrame("Korberkennung");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1270, 625);
		mainFrame.setResizable(false);

		gridbag = new GridBagLayout();
		mainFrame.setLayout(gridbag);

		initiateComponents();
		setConstraints();
		addComponentsToMainFrame();
		mainFrame.setVisible(true);
	}

	private void initiateComponents() {
		theTopPanel = new TopPanel(buttonListener);
		theCenterPanel = new CenterPanel();
		theCenterPanel.setAlignmentX(CENTER_ALIGNMENT);
		theCenterPanel.setAlignmentY(CENTER_ALIGNMENT);
		theBottomPanel = new BottomPanel();
		theLeftPanel = new LeftPanel(buttonListener);
		theRightPanel = new RightPanel(buttonListener);
	}

	private void setConstraints() {
		GridBagConstraints topConstraints = new GridBagConstraints();
		topConstraints.weightx = 1;
		topConstraints.weighty = 1;
		topConstraints.gridx = 0;
		topConstraints.gridy = 0;
		topConstraints.fill = GridBagConstraints.BOTH;
		topConstraints.gridwidth = 3;
		gridbag.setConstraints(theTopPanel, topConstraints);

		GridBagConstraints leftConstraints = new GridBagConstraints();
		leftConstraints.weightx = 1;
		leftConstraints.weighty = 1;
		leftConstraints.gridx = 0;
		leftConstraints.gridy = 1;
		leftConstraints.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(theLeftPanel, leftConstraints);

		GridBagConstraints centerConstraints = new GridBagConstraints();
		centerConstraints.anchor = GridBagConstraints.CENTER;
		centerConstraints.weightx = 6;
		centerConstraints.weighty = 16;
		centerConstraints.gridx = 1;
		centerConstraints.gridy = 1;
		centerConstraints.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(theCenterPanel, centerConstraints);

		GridBagConstraints rightConstraints = new GridBagConstraints();
		rightConstraints.weightx = 1;
		rightConstraints.weighty = 1;
		rightConstraints.gridx = 2;
		rightConstraints.gridy = 1;
		rightConstraints.fill = GridBagConstraints.BOTH;
		gridbag.setConstraints(theRightPanel, rightConstraints);

		GridBagConstraints bottomConstraints = new GridBagConstraints();
		bottomConstraints.weightx = 1;
		bottomConstraints.weighty = 1;
		bottomConstraints.gridx = 0;
		bottomConstraints.gridy = 2;
		bottomConstraints.fill = GridBagConstraints.BOTH;
		bottomConstraints.gridwidth = 3;
		gridbag.setConstraints(theBottomPanel, bottomConstraints);
	}

	private void addComponentsToMainFrame() {
		mainFrame.add(theTopPanel);
		mainFrame.add(theCenterPanel);
		mainFrame.add(theBottomPanel);
		mainFrame.add(theLeftPanel);
		mainFrame.add(theRightPanel);
	}

	void setOriginalImage() {
		theCenterPanel.showImage(businessLogic.getOriginalImage());
	}

	void takeImage() {
		theCenterPanel.showImage(businessLogic.takeSnapShot());
	}

	void openFileChooser() {
		theRightPanel.openFileChooser();
	}

	void importPictureFile() {
		try {
			theCenterPanel.showImage(businessLogic.takeFileImage(theRightPanel.getPicturePath()));
		} catch (RuntimeException ex) {
			theBottomPanel.changeLabel(ex.getMessage());
		}
	}

	void setGraph() {
		findBasket();
		theCenterPanel.showGraph(businessLogic.getData(theLeftPanel.getRow()), Integer.parseInt(theLeftPanel.getBasketPosition()),
				businessLogic.getAverage(theLeftPanel.getRow(), theLeftPanel.getLeftBorder(), theLeftPanel.getRigthBorder()),
				theLeftPanel.getLeftBorder(), theLeftPanel.getRigthBorder());
	}

	void shoot() {
		long startTime = System.currentTimeMillis();
		takeImage();
		findBasket();
		long stopTime = System.currentTimeMillis();
		String message = (getPositionMessage() + " Gefunden in " + (stopTime - startTime) + " ms.");
		theBottomPanel.changeLabel(message);
	}

	void showRow() {
		theCenterPanel.showImage(businessLogic.getRowImage(theLeftPanel.getRow()));
	}

	void showBorders() {
		theCenterPanel.showImage(businessLogic.getTwoColumnImage(theLeftPanel.getLeftBorder(), theLeftPanel.getRigthBorder()));
	}

	void findBasket() {
		theLeftPanel.setBasketPosition(businessLogic.findBasket(theLeftPanel.getRow(), theLeftPanel.getLeftBorder(), theLeftPanel.getRigthBorder()));
		theCenterPanel.showImage(businessLogic.getOneColumnImage(Integer.parseInt(theLeftPanel.getBasketPosition())));
		theBottomPanel.changeLabel(getPositionMessage());
	}

	private String getPositionMessage() {
		int basketPosition = Integer.parseInt(theLeftPanel.getBasketPosition());
		String message = "Position: " + basketPosition + " bei Reihe: " + theLeftPanel.getRow() + ". " + "Distanz zum linken Rand: "
				+ (basketPosition - theLeftPanel.getLeftBorder()) + " Pixel, zum rechten Rand: " + (theLeftPanel.getRigthBorder() - basketPosition)
				+ " Pixel.";
		return message;
	}

	void changeCamera() {
		businessLogic.changeCamera(theRightPanel.getCameraNumber());
	}
}
