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
    
    public DatatronApplication() {
    }

    public DatatronApplication(String name) {
        this.name = name;
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

}
