package org.exschool.pocketworld.util.builder;

import org.exschool.pocketworld.building.model.UserCity;
import java.util.List;
/**
 * Created by manoylo with baditsa on 18.11.15.
 */
public final class UserCityBuilder {

    //Is it need the field "private Long id;" ?
	private Long playerId;
	private String name;
    private List<Long> buildingsId;
    private List<Long> resourceBuildingsId;

    public static UserCityBuilder builder() {
        return new UserCityBuilder();
    }
    private UserCityBuilder(){};

    public UserCityBuilder playerId(Long playerId) {
        this.playerId = playerId;
        return this;

    }
    public UserCityBuilder name(String name) {
        this.name = name;
        return this;

    }
    public UserCityBuilder buildingsId(List<Long> buildingsId) {
        this.buildingsId = buildingsId;
        return this;

    }
    public UserCityBuilder resourceBuildingsId(List<Long> resourceBuildingsId) {
        this.resourceBuildingsId = resourceBuildingsId;
        return this;

    }
    public UserCity build() {
        UserCity city = new UserCity();
        
        city.setPlayerId(playerId);
        city.setBuildingsId(buildingsId);
        city.setName(name);
        city.setResourceBuildingsId(resourceBuildingsId);
        return city;
    }

}
