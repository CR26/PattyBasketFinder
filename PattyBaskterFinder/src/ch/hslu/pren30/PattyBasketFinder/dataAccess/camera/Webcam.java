/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.PattyBasketFinder.dataAccess.camera;

import java.awt.Image;
import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import ch.hslu.pren30.PattyBasketFinder.finder.MatrixFilter;

/** @author Christian Roth */
public class Webcam {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private final Mat webcam_image;
	private BufferedImage temp;
	private VideoCapture capture;
	private int cameraNumber;

	public Webcam() {
		cameraNumber = 0;
		webcam_image = new Mat();
		capture = new VideoCapture(cameraNumber);
		capture.release();
	}

	public Mat takeMat() {
		capture = new VideoCapture(cameraNumber);
		capture.read(webcam_image);
		if (!webcam_image.empty()) {
			capture.release();
			return webcam_image;
		} else {
			System.out.println(" --(!) No captured frame -- Break!");
			return null;
		}
	}

	public Image takeImage() {
		if (!capture.isOpened()) {
			capture = new VideoCapture(cameraNumber);
		}
		capture.read(webcam_image);
		if (!webcam_image.empty()) {
			temp = MatrixFilter.matToBufferedImage(webcam_image);
			capture.release();
			return temp.getScaledInstance(640, 480, 0);
		} else {
			System.out.println(" --(!) No captured frame -- Break!");
			return null;
		}
	}

	public VideoCapture takeVideoCapture() {
		if (!capture.isOpened()) {
			capture = new VideoCapture(cameraNumber);
		}
		return capture;
	}

	public void releaseCapture() {
		capture.release();
	}

	public void changeCamera(int cameraNumber) {
		this.cameraNumber = cameraNumber;
	}
}
