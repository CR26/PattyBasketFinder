/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** @author Christian Roth */
public class ButtonListener implements ActionListener {

	private final MainWindow mainWindow;

	public ButtonListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "PictureView":
			mainWindow.setOriginalImage();
			break;
		case "GraphView":
			mainWindow.setGraph();
			break;
		case "SnapShot":
			mainWindow.takeImage();
			break;
		case "OpenPicture":
			mainWindow.openFileChooser();
			break;
		case "ImportPicture":
			mainWindow.importPictureFile();
			break;
		case "Shoot":
			mainWindow.shoot();
			break;
		case "ShowRow":
			mainWindow.showRow();
			break;
		case "ShowBorders":
			mainWindow.showBorders();
			break;
		case "FindBasket":
			mainWindow.findBasket();
			break;
		case "ChangeCamera":
			mainWindow.changeCamera();
			break;
		default:
			break;
		}
	}

}
