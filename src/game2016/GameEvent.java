package game2016;

import javafx.event.ActionEvent;

public class GameEvent extends ActionEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String name;
	private int xPos;
	private int yPos;
	private String direction;
	
	public GameEvent(String type, String name, int xPos, int yPos, String direction) {
		this.type = type;
		this.name = name;
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public String getDirection() {
		return direction;
	}
	
	@Override
	public String toString() {
		return "GameEvent";
	}
}
