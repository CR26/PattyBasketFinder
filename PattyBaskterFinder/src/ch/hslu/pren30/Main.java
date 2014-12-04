/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30;

import ch.hslu.pren30.dataAccess.DataAccess;
import ch.hslu.pren30.finder.BusinessLogic;
import ch.hslu.pren30.gui.MainWindow;

public class Main {

	/** @param args */
	public static void main(String[] args) {
		new MainWindow(new BusinessLogic(new DataAccess()));
	}
}
