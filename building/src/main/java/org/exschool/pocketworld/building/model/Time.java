package org.exschool.pocketworld.building.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class Time {

    @EmbeddedId
    private TimeId timeId;

    private int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public TimeId getTimeId() {
        return timeId;
    }

    public void setTimeId(TimeId timeId) {
        this.timeId = timeId;
    }
}
