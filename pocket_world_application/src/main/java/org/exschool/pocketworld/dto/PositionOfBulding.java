package org.exschool.pocketworld.dto;

public class PositionOfBulding {
    private Integer position;
    private String type;

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
        return "PositionOfBulding{" +
                "position=" + position +
                ", type='" + type + '\'' +
                '}';
    }
}
