package org.exschool.pocketworld.util.builder;

import org.exschool.pocketworld.city.model.City;

/**
 * Created by manoylo with baditsa on 18.11.15.
 */
public final class UserCityBuilder {

    private Long playerId;
    private String name;

    public static UserCityBuilder builder() {
        return new UserCityBuilder();
    }

    private UserCityBuilder() {
    }

    public UserCityBuilder playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public UserCityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public City build() {
        City city = new City();
        city.setPlayerId(playerId);
        city.setName(name);
        return city;
    }

}
