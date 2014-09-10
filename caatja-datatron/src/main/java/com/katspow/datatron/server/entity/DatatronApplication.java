package com.katspow.datatron.server.entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache
public class DatatronApplication {
    
    @Parent Key parent;

    @Id Long id;
    
    @Index
    private String name;
    
    private String password;
    
    private Integer maxNbScores;
    
    public DatatronApplication() {
    }

    public DatatronApplication(String name, String password, String maxNbScores) {
        this.name = name;
        this.password = password;
        this.maxNbScores = Integer.parseInt(maxNbScores);
        this.parent = KeyFactory.createKey("RootApp", "app");
    }
    
    public String getName() {
        return name;
    }
    
    public Long getId() {
        return id;
    }
    
    public Key getParent() {
        return parent;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Integer getMaxNbScores() {
        return maxNbScores;
    }

}
