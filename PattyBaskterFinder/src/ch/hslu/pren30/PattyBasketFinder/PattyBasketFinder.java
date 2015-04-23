package ch.hslu.pren30.PattyBasketFinder;

import ch.hslu.pren30.PattyBasketFinder.dataAccess.DataAccess;
import ch.hslu.pren30.PattyBasketFinder.finder.BusinessLogic;

public class PattyBasketFinder {

	/** @param args */
	public static void main(String[] args) {
		findBasket();

	}

	public static int findBasket() {
		int relativePosition, position;

		BusinessLogic bl = new BusinessLogic(new DataAccess());
		position = bl.findBasket(410, 0, 640);
		relativePosition = (position * 100) / 640;
		System.out.println("Position: " + position);
		System.out.println("Relative Position: " + relativePosition);

		return relativePosition;
	}
}
