package org.exschool.pocketworld.build_queue.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by skandy on 02.12.15.
 */
@Entity
@Table(name="BUILDQUEUE")
public class BuildQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int level;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Temporal(value=TemporalType.TIMESTAMP)
    private Date buildEnd;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long buildingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getBuildEnd() {
        return buildEnd;
    }

    public void setBuildEnd(Date buildEnd) {
        this.buildEnd = buildEnd;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public BuildQueue() {
    }

    public BuildQueue(
            Long id, String name, int level, Type type, Date buildEnd, Long userId, Status status, Long buildingId) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type = type;
        this.buildEnd = buildEnd;
        this.userId = userId;
        this.status = status;
        this.buildingId = buildingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuildQueue that = (BuildQueue) o;

        if (level != that.level) return false;
        if (buildEnd != null ? !buildEnd.equals(that.buildEnd) : that.buildEnd != null) return false;
        if (buildingId != null ? !buildingId.equals(that.buildingId) : that.buildingId != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != that.status) return false;
        if (type != that.type) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (buildEnd != null ? buildEnd.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (buildingId != null ? buildingId.hashCode() : 0);
        return result;
    }


}
