package org.exschool.pocketworld.city.common.dto;

/**
 * Created by tanya on 03.02.16.
 */
public class Timer {

    private final String type;
    private final long buildEndInSeconds;
    private final int position;

    private Timer(String type, long buildEndInSeconds, int position) {
        this.type = type;
        this.buildEndInSeconds = buildEndInSeconds;
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public long getBuildEndInSeconds() {
        return buildEndInSeconds;
    }

    public int getPosition() {
        return position;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private long buildEndInSeconds;
        private int position;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder buildEndInSeconds(long seconds) {
            this.buildEndInSeconds = seconds;
            return this;
        }

        public Builder position(int position) {
            this.position = position;
            return this;
        }

        public Timer get() {
            return new Timer(type, buildEndInSeconds, position);
        }
    }
}
