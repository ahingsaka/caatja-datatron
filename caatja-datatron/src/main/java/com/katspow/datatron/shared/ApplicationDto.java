package com.katspow.datatron.shared;

import java.io.Serializable;

public class ApplicationDto implements Serializable {
    
    private static final long serialVersionUID = -8029867959224229420L;
    
    private Long id;
    private String name;
    private String password;
    private Integer maxNbOfScores;
    
    public ApplicationDto() {
    }
    
    public ApplicationDto(Long id, String name, String password, Integer maxNbOfScores) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.maxNbOfScores = maxNbOfScores;
    }

    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getMaxNbOfScores() {
        return maxNbOfScores;
    }

}
