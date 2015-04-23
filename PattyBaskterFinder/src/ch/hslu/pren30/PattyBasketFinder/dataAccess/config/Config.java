package ch.hslu.pren30.PattyBasketFinder.dataAccess.config;

public class Config {
	private static Config instance;
	private int middleRow;
	private int leftBorder;
	private int rightBorder;
	private int spaceBetweenRows;
	private int rowsToCheck;

	private Config() {
	}

	public static Config getInstance() {
		if (instance == null) {
			instance = new Config();
			new ConfigReader().readConfig(instance);
		}
		return instance;
	}

	public final int getMiddleRow() {
		return middleRow;
	}

	final void setMiddleRow(int middleRow) {
		this.middleRow = middleRow;
	}

	public final int getLeftBorder() {
		return leftBorder;
	}

	final void setLeftBorder(int leftBorder) {
		this.leftBorder = leftBorder;
	}

	public final int getRightBorder() {
		return rightBorder;
	}

	final void setRightBorder(int rightBorder) {
		this.rightBorder = rightBorder;
	}

	public final int getSpaceBetweenRows() {
		return spaceBetweenRows;
	}

	final void setSpaceBetweenRows(int spaceBetweenRows) {
		this.spaceBetweenRows = spaceBetweenRows;
	}

	public final int getRowsToCheck() {
		return rowsToCheck;
	}

	final void setRowsToCheck(int rowsToCheck) {
		this.rowsToCheck = rowsToCheck;
	}

}
