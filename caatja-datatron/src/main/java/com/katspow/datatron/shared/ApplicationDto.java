package com.katspow.datatron.shared;

import java.io.Serializable;

public class ApplicationDto implements Serializable {
    
    private static final long serialVersionUID = -8029867959224229420L;
    
    private Long id;
    private String name;
    private String password;
    
    public ApplicationDto() {
    }
    
    public ApplicationDto(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
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

}
