package com.katspow.datatron.shared;

import java.io.Serializable;

public class ScoreDto implements Serializable {

    private static final long serialVersionUID = 7544541956419900190L;

    private Long id;

    private int numOrder;

    private String name;

    private int score;
    
    public ScoreDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
}
