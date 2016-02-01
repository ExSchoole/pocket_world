package org.exschool.pocketworld.dto;

public class PositionOfBuilding {
    private Integer position;
    private String type;
    private String playerName;

    public PositionOfBuilding() {}

    public PositionOfBuilding(Integer position, String type, String playerName) {
        this.position = position;
        this.type = type;
        this.setPlayerName(playerName);
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PositionOfBuilding{" +
                "position=" + position +
                ", type='" + type + '\'' +
                '}';
    }

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
