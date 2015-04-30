package ch.hslu.pren30.PattyBasketFinder.finder;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import ch.hslu.pren30.PattyBasketFinder.config.Config;

/** @author Christian Roth */
public class BusinessLogic {
	private final Mat imageMatrix;

	public BusinessLogic() {
		imageMatrix = Highgui.imread(Config.getInstance().getPicturePath());
	}

	public int findBasket(final int row, final int leftBorder, final int rightBorder) {
		RowAnalyzer analyzer = new RowAnalyzer();
		List<Integer> filteredRow = new MatrixFilter().getRow(imageMatrix, row);
		return analyzer.findBasket(filteredRow, leftBorder, rightBorder);
	}
}
