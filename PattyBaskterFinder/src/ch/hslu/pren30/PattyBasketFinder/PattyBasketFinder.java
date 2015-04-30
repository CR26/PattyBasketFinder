package ch.hslu.pren30.PattyBasketFinder;

import org.opencv.core.Core;

import ch.hslu.pren30.PattyBasketFinder.config.Config;
import ch.hslu.pren30.PattyBasketFinder.finder.BusinessLogic;

public class PattyBasketFinder {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	/** @param args */
	public static void main(String[] args) {
		findBasket();
	}

	public static int findBasket() {
		int relativePosition, position;

		BusinessLogic bl = new BusinessLogic();
		Config _config = Config.getInstance();
		position = bl.findBasket(_config.getMiddleRow(), _config.getLeftBorder(), 640 - _config.getRightBorder());
		relativePosition = (position * 100) / 640;
		System.out.println("Position: " + position);
		System.out.println("Relative Position: " + relativePosition);

		return relativePosition;
	}
}
