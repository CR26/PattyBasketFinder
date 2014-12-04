/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.hslu.pren30.dataAccess.filereader;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/** @author Christian Roth */
public class FileReader {

	public FileReader() {

	}

	public Image getImage(String path) {
		try {
			return ImageIO.read(new File(path)).getScaledInstance(640, 480, 0);
		} catch (IOException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
}
