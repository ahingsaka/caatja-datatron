package com.katspow.datatron.server.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;


@Entity
@Cache
public class DatatronScore {
    
    @Id
    private Long id;

    @Parent
    Key<DatatronApplication> application;
    
    private int numOrder;
    
    private String name;
    
    private int score;
    
    public DatatronScore() {
    }

    public int getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(int numOrder) {
        this.numOrder = numOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public Long getParentId() {
        return application.getId();
    }
    
    public Long getId() {
        return id;
    }
    
}
