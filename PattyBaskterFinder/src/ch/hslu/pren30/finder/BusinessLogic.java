/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.finder;

import java.awt.Image;

import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import ch.hslu.pren30.dataAccess.DataAccess;

/** @author Christian Roth */
public class BusinessLogic {

	private final DataAccess dataAccess;

	private Mat snapshotMatrix;

	public BusinessLogic(DataAccess dal) {
		dataAccess = dal;
	}

	public Image takeSnapShot() {
		do {
			snapshotMatrix = dataAccess.getCameraMat();
		} while (snapshotMatrix == null);
		return MatrixFilter.matToBufferedImage(snapshotMatrix);
	}

	public Image getOriginalImage() {
		return MatrixFilter.matToBufferedImage(snapshotMatrix);
	}

	public Image takeFileImage(String path) {
		try {
			snapshotMatrix = MatrixFilter.imageToMat(dataAccess.getFileImage(path));
			return MatrixFilter.matToBufferedImage(snapshotMatrix);
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	public VideoCapture startVideoCapture() {
		return dataAccess.startCameraVideoCapture();
	}

	public void stopVideoCapture() {
		dataAccess.stopCameraVideoCapture();
	}

	public Image getRowImage(int row) {
		return MatrixFilter.getInstance().exchangeRow(MatrixFilter.matToBufferedImage(snapshotMatrix), row);
	}

	public int findBasket(int row, int left, int right) {
		return RowAnalyzer.getInstance().findBasket(MatrixFilter.getInstance().getRow(snapshotMatrix, row), left, right);
	}

	public Image getOneColumnImage(int column) {
		return MatrixFilter.getInstance().exchangeColumn(MatrixFilter.matToBufferedImage(snapshotMatrix), column);
	}

	public Image getTwoColumnImage(int columnA, int columnB) {
		return MatrixFilter.getInstance().exchangeColumn(getOneColumnImage(columnA), columnB);
	}

	public int[] getData(int row) {
		return MatrixFilter.getInstance().getRow(snapshotMatrix, row);
	}

	public int getAverage(int row, int left, int right) {
		return RowAnalyzer.getInstance().getAverageOfArray(
				RowAnalyzer.getInstance().cutArray(MatrixFilter.getInstance().getRow(snapshotMatrix, row), left, right));
	}

	public void changeCamera(int cameraNumber) {
		dataAccess.changeCamera(cameraNumber);
	}
}
