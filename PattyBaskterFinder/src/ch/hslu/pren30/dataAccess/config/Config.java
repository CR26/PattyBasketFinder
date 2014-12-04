package ch.hslu.pren30.dataAccess.config;

public class Config {
	private static Config instance;
	private String middleRow;
	private String leftBorder;
	private String rightBorder;
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

	public final String getMiddleRow() {
		return middleRow;
	}

	final void setMiddleRow(String middleRow) {
		this.middleRow = middleRow;
	}

	public final String getLeftBorder() {
		return leftBorder;
	}

	final void setLeftBorder(String leftBorder) {
		this.leftBorder = leftBorder;
	}

	public final String getRightBorder() {
		return rightBorder;
	}

	final void setRightBorder(String rightBorder) {
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
