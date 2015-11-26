package org.exschool.pocketworld.player.model;

import javax.persistence.*;

/**
 * Created by skandy on 26.11.15.
 */
@Embeddable
public class PlayerResources {
    private Integer gold;
    private Integer timber;
    private Integer clay;
    private Integer corn;

    public PlayerResources() {
    }

    public PlayerResources(int gold, int timber, int clay, int corn) {
        this.gold = gold;
        this.timber = timber;
        this.clay = clay;
        this.corn = corn;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getTimber() {
        return timber;
    }

    public void setTimber(int timber) {
        this.timber = timber;
    }

    public int getClay() {
        return clay;
    }

    public void setClay(int clay) {
        this.clay = clay;
    }

    public int getCorn() {
        return corn;
    }

    public void setCorn(int corn) {
        this.corn = corn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerResources that = (PlayerResources) o;

        if (clay != null ? !clay.equals(that.clay) : that.clay != null) return false;
        if (corn != null ? !corn.equals(that.corn) : that.corn != null) return false;
        if (gold != null ? !gold.equals(that.gold) : that.gold != null) return false;
        if (timber != null ? !timber.equals(that.timber) : that.timber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gold != null ? gold.hashCode() : 0;
        result = 31 * result + (timber != null ? timber.hashCode() : 0);
        result = 31 * result + (clay != null ? clay.hashCode() : 0);
        result = 31 * result + (corn != null ? corn.hashCode() : 0);
        return result;
    }
}
