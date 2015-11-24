package org.exschool.pocketworld.building.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserCity {
    @Id
    @GeneratedValue
    private Long id;


    private Long playerId;

    private String name;

    private Long buildingsId;


    private Long resourceBuildingsId;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPlayerId() {
        return playerId;
    }
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
    public Long getBuildingsId() {
        return buildingsId;
    }
    public void setBuildingsId(Long buildingsId) {
        this.buildingsId = buildingsId;
    }
    public Long getResourceBuildingsId() {
        return resourceBuildingsId;
    }
    public void setResourceBuildingsId(Long resourceBuildingsId) {
        this.resourceBuildingsId = resourceBuildingsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCity usercity = (UserCity) o;

        if (playerId != null ? !playerId.equals(usercity.playerId) : usercity.playerId != null) return false;
        if (name != usercity.name) return false;
        if (buildingsId != null ? !buildingsId.equals(usercity.buildingsId) : usercity.buildingsId != null) return false;
        if (resourceBuildingsId != null ?
                !resourceBuildingsId.equals(usercity.resourceBuildingsId) :
                usercity.resourceBuildingsId != null) return false;
        if (id != null ? !id.equals(usercity.id) : usercity.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (playerId!= null ? playerId.hashCode() : 0);
        result = 31 * result + (buildingsId!= null ? buildingsId.hashCode() : 0);
        result = 31 * result + (resourceBuildingsId != null ? resourceBuildingsId.hashCode() : 0);
        return result;
    }





}