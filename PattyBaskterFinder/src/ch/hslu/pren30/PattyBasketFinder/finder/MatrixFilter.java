package ch.hslu.pren30.PattyBasketFinder.finder;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Mat;

/** @author Christian Roth */
public class MatrixFilter {

	public List<Integer> getRow(Mat imageMatrix, int row) {
		List<Integer> result = new ArrayList<Integer>();

		for (int j = 0; j < imageMatrix.size().width; j++) {
			double[] data = imageMatrix.get(row, j);
			data[0] = data[0] / 2;
			data[1] = data[1] / 2;
			data[2] = data[2] / 2;
			result.add((int) (data[0] + data[1] + data[2]));
		}
		return result;
	}

}