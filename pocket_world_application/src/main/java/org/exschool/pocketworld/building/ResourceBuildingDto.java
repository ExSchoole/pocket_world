package org.exschool.pocketworld.building;

public final class ResourceBuildingDto {

    public static Builder builder() {
        return new Builder();
    }

    private String type;
    private int level;
    private int position;

    private ResourceBuildingDto(String type, int level, int position) {
        this.type = type;
        this.level = level;
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public static final class Builder {
        private String type;
        private int level;
        private int position;

        private Builder() {}

        public Builder type(final String type) {
            this.type = type;
            return this;
        }

        public Builder level(final int level) {
            this.level = level;
            return this;
        }

        public Builder position(int position) {
            this.position = position;
            return this;
        }

        public ResourceBuildingDto build() {
            return new ResourceBuildingDto(type, level, position);
        }
    }
}
