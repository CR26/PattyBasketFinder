/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.gui.mainelements;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import ch.hslu.pren30.gui.ButtonListener;

/** @author Christian Roth */
public class RightPanel extends JPanel {

	private JPanel itemPanel;

	private JButton snapShotButton;

	private JTextField picturePath;
	private JButton openFileChooser;
	private JFileChooser fileChooser;
	private JButton importPicture;

	private JComboBox<String> cameraComboBox;
	private JButton changeCameraButton;

	private JButton shotButton;

	private final ButtonListener buttonListener;

	public RightPanel(ButtonListener bl) {
		super();
		super.setPreferredSize(new Dimension(160, 640));
		this.buttonListener = bl;
		initializeComponents();
	}

	private void initializeComponents() {
		itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(0, 1));

		snapShotButton = new JButton("snapshot");
		snapShotButton.addActionListener(buttonListener);
		snapShotButton.setActionCommand("SnapShot");
		itemPanel.add(snapShotButton);

		itemPanel.add(new JLabel());

		picturePath = new JTextField("D:\\Dropbox\\Schule\\PREN1\\sample_pics\\colors.png");
		itemPanel.add(picturePath);
		openFileChooser = new JButton("find picture");
		openFileChooser.addActionListener(buttonListener);
		openFileChooser.setActionCommand("OpenPicture");
		itemPanel.add(openFileChooser);
		importPicture = new JButton("import picture");
		importPicture.addActionListener(buttonListener);
		importPicture.setActionCommand("ImportPicture");
		itemPanel.add(importPicture);

		itemPanel.add(new JLabel());

		cameraComboBox = new JComboBox<String>();
		cameraComboBox.addItem("Webcam");
		cameraComboBox.addItem("USB Cam");
		itemPanel.add(cameraComboBox);
		changeCameraButton = new JButton("change camera");
		changeCameraButton.addActionListener(buttonListener);
		changeCameraButton.setActionCommand("ChangeCamera");
		itemPanel.add(changeCameraButton);

		itemPanel.add(new JLabel());

		shotButton = new JButton("shoot");
		shotButton.addActionListener(buttonListener);
		shotButton.setActionCommand("Shoot");
		itemPanel.add(shotButton);

		super.add(itemPanel);
	}

	public String getPicturePath() {
		return picturePath.getText();
	}

	public void openFileChooser() {
		fileChooser = new JFileChooser("D:\\Dropbox\\Schule\\PREN1\\sample_pics\\");
		fileChooser.setFileFilter(new FileNameExtensionFilter("JPG File", "jpg"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("PNG File", "png"));
		int rueckgabeWert = fileChooser.showOpenDialog(picturePath);

		if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
			picturePath.setText(fileChooser.getSelectedFile().getPath());
		}
	}

	public int getCameraNumber() {
		switch (cameraComboBox.getSelectedItem().toString()) {
		case "Webcam":
			return 0;
		case "USB Cam":
			return 1;
		default:
			return 0;
		}
	}
}
