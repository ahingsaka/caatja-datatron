package com.katspow.datatron.shared;

import java.io.Serializable;

public class AuthenticationDto implements Serializable {
    
    private static final long serialVersionUID = 1917908608387330401L;

    private boolean ok;
    
    private boolean firstTime;
    
    public AuthenticationDto() {
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

}
