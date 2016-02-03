package org.exschool.pocketworld.buildQueue.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * Created by skandy on 02.12.15.
 */
public enum Type implements Serializable {
    BUILDING,
    RESOURCE_BUILDING;
    
    public static List<Type> asList() {
        return Lists.transform(Arrays.asList(values()), TO_STRING_FUNCTION);
    }
    
    private static final Function<Type, Type> TO_STRING_FUNCTION = new Function<Type, Type>() {
        @Override
        public Type apply(Type type) {
            return type;
        }
    };
}
