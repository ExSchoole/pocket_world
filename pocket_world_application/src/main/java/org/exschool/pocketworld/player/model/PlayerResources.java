package org.exschool.pocketworld.player.model;

import org.exschool.pocketworld.resource.model.Resource;

import javax.persistence.*;

/**
 * Created by skandy on 26.11.15.
 */
@Embeddable
public class PlayerResources {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "resourceType", column = @Column(name = "gold_resource_type")),
            @AttributeOverride(name = "amount", column = @Column(name = "gold_amount"))
    })
    private Resource gold;
    private Resource timber;
    private Resource clay;
    private Resource corn;

    public PlayerResources() {
    }

    public PlayerResources(Resource gold, Resource timber, Resource clay, Resource corn) {
        this.gold = gold;
        this.timber = timber;
        this.clay = clay;
        this.corn = corn;
    }

    public Resource getGold() {
        return gold;
    }

    public void setGold(Resource gold) {
        this.gold = gold;
    }

    public Resource getTimber() {
        return timber;
    }

    public void setTimber(Resource timber) {
        this.timber = timber;
    }

    public Resource getClay() {
        return clay;
    }

    public void setClay(Resource clay) {
        this.clay = clay;
    }

    public Resource getCorn() {
        return corn;
    }

    public void setCorn(Resource corn) {
        this.corn = corn;
    }
}
