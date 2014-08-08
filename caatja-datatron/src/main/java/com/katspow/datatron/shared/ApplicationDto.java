package com.katspow.datatron.shared;

import java.io.Serializable;

public class ApplicationDto implements Serializable {
    
    private static final long serialVersionUID = -8029867959224229420L;
    
    private Long id;
    
    public ApplicationDto(String name) {
        this.name = name;
    }

    public ApplicationDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String name;
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

}
