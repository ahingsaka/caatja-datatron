package com.katspow.datatron.shared;

import java.io.Serializable;

public class AuthenticationDto implements Serializable {
    
    private static final long serialVersionUID = 1917908608387330401L;

    private boolean ok;
    
    private boolean firstTime;
    
    private String login;
    
    private String password;
    
    private String question;
    
    private String answer;
    
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
