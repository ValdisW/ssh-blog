package com.valdis.blog.bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Userstar {
    private String rId;
    private String username;
    private String essayId;

    @Id
    @Column(name = "rID", nullable = false, length = 45)
    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "essayID", nullable = false, length = 45)
    public String getEssayId() {
        return essayId;
    }

    public void setEssayId(String essayId) {
        this.essayId = essayId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userstar userstar = (Userstar) o;
        return Objects.equals(rId, userstar.rId) &&
                Objects.equals(username, userstar.username) &&
                Objects.equals(essayId, userstar.essayId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rId, username, essayId);
    }
}
