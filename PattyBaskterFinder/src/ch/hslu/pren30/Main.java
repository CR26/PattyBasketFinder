package ch.hslu.pren30;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ch.hslu.pren30.dataAccess.DataAccess;
import ch.hslu.pren30.dataAccess.config.Config;
import ch.hslu.pren30.finder.BusinessLogic;

public class Main {

	/** @param args */
	public static void main(String[] args) {
		int relativePosition, position;

		BusinessLogic bl = new BusinessLogic(new DataAccess());
		Config config = Config.getInstance();
		position = bl.findBasket(config.getMiddleRow(), config.getLeftBorder(), 640 - config.getRightBorder());
		relativePosition = (position * 100) / 640;
		System.out.println("Position: " + position);
		System.out.println("Relative Position: " + relativePosition);

		JFrame frame = new JFrame("Test GUI");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(800, 800);
		BufferedImage bufIm = (BufferedImage) bl.getOriginalImage();
		Color marker = new Color(255, 255, 255);
		for (int x = 0; x < bufIm.getHeight(); x++) {
			bufIm.setRGB(position, x, marker.getRGB());
		}
		ImageIcon image = new ImageIcon(bufIm);

		frame.add(new JLabel(image));
		frame.setVisible(true);

	}
}
