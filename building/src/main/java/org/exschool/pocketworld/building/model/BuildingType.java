package org.exschool.pocketworld.building.model;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public enum BuildingType {
    STORAGE,
    GILOTHOME,
    TOWNHALL,
    MARKETPLACE,
    POOL,
    PLANT,
    FARM,
    BARN,
    SCHOOL,
    MALL;

    public static List<String> asList() {
        return Lists.transform(Arrays.asList(values()), TO_STRING_FUNCTION);
    }

    public static List<String> asListLowerCase() {
        return Lists.transform(Arrays.asList(values()), TO_LOWERCASE_STRING_FUNCTION);
    }

    private static final Function<BuildingType, String> TO_STRING_FUNCTION = new Function<BuildingType, String>() {
        @Override
        public String apply(BuildingType buildingType) {
            return buildingType.name();
        }
    };

    private static final Function<BuildingType, String> TO_LOWERCASE_STRING_FUNCTION =
            new Function<BuildingType, String>() {
                @Override
                public String apply(BuildingType buildingType) {
                    return buildingType.name().toLowerCase();
                }
            };

}
