package ch.hslu.pren30.PattyBasketFinder.config;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

class ConfigReader {
	private Element configElement;

	void readConfig(Config config) {
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("config.xml"));

			configElement = (Element) doc.getElementsByTagName("config").item(0);
			config.setMiddleRow(getMiddleRow());
			config.setLeftBorder(getLeftBorder());
			config.setRightBorder(getRightBorder());
			config.setRowsToCheck(getRowsToCheck());
			config.setSpaceBetweenRows(getSpaceBetweenRows());
			config.setPicturePath(getImagePath());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getMiddleRow() {
		return Integer.valueOf(configElement.getElementsByTagName("middleRow").item(0).getChildNodes().item(0).getNodeValue());
	}

	private int getLeftBorder() {
		return Integer.valueOf(configElement.getElementsByTagName("leftBorder").item(0).getChildNodes().item(0).getNodeValue());
	}

	private int getRightBorder() {
		return Integer.valueOf(configElement.getElementsByTagName("rightBorder").item(0).getChildNodes().item(0).getNodeValue());
	}

	private int getRowsToCheck() {
		String value = configElement.getElementsByTagName("rowsToCheck").item(0).getChildNodes().item(0).getNodeValue();
		return Integer.parseInt(value);
	}

	private int getSpaceBetweenRows() {
		String value = configElement.getElementsByTagName("spaceBetweenRows").item(0).getChildNodes().item(0).getNodeValue();
		return Integer.parseInt(value);
	}

	private String getImagePath() {
		return configElement.getElementsByTagName("imagePath").item(0).getChildNodes().item(0).getNodeValue();

	}

}
