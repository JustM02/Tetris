package game;

import javafx.scene.paint.Color;

public enum Tile {

	EMPTY(Color.LIGHTGRAY, "E", false), LIGHTBLUE(Color.TEAL, "I", false), BLUE(Color.BLUE, "J", false),
	YELLOW(Color.YELLOW, "O", false), PURPLE(Color.PURPLE, "T", false), GREEN(Color.GREEN, "S", false),
	RED(Color.RED, "Z", false), ORANGE(Color.ORANGE, "O", false), S_LIGHTBLUE(Color.TEAL, "SI", true),
	S_BLUE(Color.BLUE, "SJ", true), S_YELLOW(Color.YELLOW, "SO", true), S_PURPLE(Color.PURPLE, "ST", true),
	S_GREEN(Color.GREEN, "SS", true), S_RED(Color.RED, "SZ", true), S_ORANGE(Color.ORANGE, "SO", true);

	private Color color;
	private String name;
	private boolean stopped;

	Tile(Color color, String name, boolean stopped) {
		this.color = color;
		this.name = name;
		this.stopped = stopped;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public boolean getStopped() {
		return stopped;
	}
}
