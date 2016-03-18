package org.exschool.pocketworld.chat.model;

import javax.persistence.*;

@Entity
@Table(name="relation")
public class UserRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="playername1")
    private String playername1;

    @Column(name="playername2")
    private String playername2;

    public UserRelation(){

    }

    public UserRelation(String playername1, String playername2){
        this.playername1 = playername1;
        this.playername2 = playername2;
    }

    public String getPlayername2() {
        return playername2;
    }

    public void setPlayername2(String playername2) {
        this.playername2 = playername2;
    }

    public String getPlayername1() {
        return playername1;
    }

    public void setPlayername1(String playername1) {
        this.playername1 = playername1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRelation that = (UserRelation) o;

        if (playername1 != null ? !playername1.equals(that.playername1) : that.playername1 != null) return false;
        return playername2 != null ? playername2.equals(that.playername2) : that.playername2 == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playername1 != null ? playername1.hashCode() : 0);
        result = 31 * result + (playername2 != null ? playername2.hashCode() : 0);
        return result;
    }
}
