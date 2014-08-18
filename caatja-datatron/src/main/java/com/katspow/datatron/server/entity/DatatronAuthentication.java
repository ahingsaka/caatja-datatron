package com.katspow.datatron.server.entity;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache
public class DatatronAuthentication {

    @Id
    String id;

    @Parent
    Key parent;

    private String login;

    private String password;

    private String question;

    private String answer;

    public DatatronAuthentication() {
    }

    public DatatronAuthentication(String id, String login, String password, String question, String answer) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.parent = KeyFactory.createKey("RootAuth", "auth");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String getAnswer() {
        return answer;
    }

}
