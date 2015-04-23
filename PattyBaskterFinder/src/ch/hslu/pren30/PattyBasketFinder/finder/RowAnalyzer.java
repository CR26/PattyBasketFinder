/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.PattyBasketFinder.finder;

/**
 *
 * @author Patrik
 */
public class RowAnalyzer {
    private static RowAnalyzer instance = null;
        
    private RowAnalyzer() {
        
    }
         
    public static RowAnalyzer getInstance() {
        if (instance == null) {
            instance = new RowAnalyzer();
        }
        return instance;
    }
    
    public int findBasket(int[] row, int left, int right) {
        int basketPosition = 0;
        int countBasketPosition = 0;
        int countGoodPixels = 0;
        int countTolerance = 0;
        
        if(right < left) {
            int tmp = right;
            right = left;
            left = tmp;
        } 
        if (left < 0) {
            left = 0;
        }
        if (right > row.length) {
            right = row.length;
        }
        
        // cut the Array;
        int[] cuttedData = cutArray(row, left, right);
        
        int average = getAverageOfArray(cuttedData);
        
        for(int i = 0; i < cuttedData.length; i++) {
            if(cuttedData[i] < average) {
                countGoodPixels++;
                countTolerance = 10;
            } else {
                countGoodPixels++;
                countTolerance--;
            }
            if(countTolerance < 0) {
                if(countBasketPosition < countGoodPixels) {
                    basketPosition = i - ((countGoodPixels - 10) / 2) - 10; 
                    countBasketPosition = countGoodPixels;
                }
                countGoodPixels = 0;
            }
        }    
        
        if(countBasketPosition < countGoodPixels) {
            basketPosition = cuttedData.length - ((countGoodPixels - 10 + countTolerance) / 2) - 10 + countTolerance;
        }
        
        if(basketPosition < 0) {
            basketPosition = 0;
        }
        
        return basketPosition + left;
    }
    
    public int[] cutArray(int[] array, int left, int right) {
        if(array.length > right - left) {
            int[] cuttedData = new int[right - left];
            for(int i = left; i < right; i++) {
                cuttedData[i-left] = array[i];
            }
            return cuttedData;
        } else {
            return array;
        }        
    }
    
//    public int getAverageOfArray(int[] array) {
//        int total = 0;
//        for (double element : array) {
//            total += element;
//        }
//
//        return total / array.length;
//    }
    
    public int getAverageOfArray(int [] array) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        
        for(int i = 0; i < array.length; i++) {
            if(array[i] < min) {
                min = array[i];
            } else {
                if(array[i] > max) {
                    max = array[i];
                }
            }
        }
        return max - ((max - min) / 2);
    }
    
}
