/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.PattyBasketFinder.finder;

import java.util.ArrayList;
import java.util.List;

/** @author Christian Roth */
public class RowAnalyzer {
	public int findBasket(List<Integer> filteredRow, int left, int right) {
		int basketPosition = 0;
		int countBasketPosition = 0;
		int countGoodPixels = 0;
		int countTolerance = 0;

		if (right < left) {
			int tmp = right;
			right = left;
			left = tmp;
		}
		if (left < 0) {
			left = 0;
		}
		if (right > filteredRow.size()) {
			right = filteredRow.size();
		}

		// cut the Array;
		List<Integer> cuttedData = cutArray(filteredRow, left, right);

		int average = getAverageOfArray(cuttedData);
		int counter = 0;
		for (Integer currentInt : cuttedData) {
			if (currentInt < average) {
				countGoodPixels++;
				countTolerance = 10;
			} else {
				countGoodPixels++;
				countTolerance--;
			}
			if (countTolerance < 0) {
				if (countBasketPosition < countGoodPixels) {
					basketPosition = counter - ((countGoodPixels - 10) / 2) - 10;
					countBasketPosition = countGoodPixels;
				}
				countGoodPixels = 0;
			}
			counter++;
		}

		if (countBasketPosition < countGoodPixels) {
			basketPosition = cuttedData.size() - ((countGoodPixels - 10 + countTolerance) / 2) - 10 + countTolerance;
		}

		if (basketPosition < 0) {
			basketPosition = 0;
		}

		return basketPosition + left;
	}

	public List<Integer> cutArray(List<Integer> filteredRow, int left, int right) {
		if (filteredRow.size() > right - left) {
			List<Integer> cuttedData = new ArrayList<Integer>();
			for (int i = left; i < right; i++) {
				cuttedData.add(filteredRow.get(i));
			}
			return cuttedData;
		} else {
			return filteredRow;
		}
	}

	public int getAverageOfArray(List<Integer> cuttedData) {
		int min = Integer.MAX_VALUE;
		int max = 0;

		for (Integer currentInt : cuttedData) {
			if (currentInt < min) {
				min = currentInt.intValue();
			} else {
				if (currentInt > max) {
					max = currentInt.intValue();
				}
			}
		}
		return max - ((max - min) / 2);
	}

}
