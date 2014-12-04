/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.dataAccess;

import java.awt.Image;

import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import ch.hslu.pren30.dataAccess.camera.Webcam;
import ch.hslu.pren30.dataAccess.filereader.FileReader;

/** @author Christian Roth */
public class DataAccess {

	private final Webcam webcam;
	private final FileReader fileReader;

	public DataAccess() {
		webcam = new Webcam();
		fileReader = new FileReader();
	}

	public Image getCameraImage() {
		return webcam.takeImage();
	}

	public Image getFileImage(String path) {
		try {
			return fileReader.getImage(path);
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	public Mat getCameraMat() {
		return webcam.takeMat();
	}

	public VideoCapture startCameraVideoCapture() {
		return webcam.takeVideoCapture();
	}

	public void stopCameraVideoCapture() {
		webcam.releaseCapture();
	}

	public void changeCamera(int cameraNumber) {
		webcam.changeCamera(cameraNumber);
	}
}
