package com.katspow.datatron.client.api;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.katspow.datatron.shared.ApplicationDto;
import com.katspow.datatron.shared.AuthenticationDto;
import com.katspow.datatron.shared.ImageDto;

public interface DatatronServiceAsync {
    
    void createApplication(String name, AsyncCallback<Boolean> callback);

    void findAllApps(AsyncCallback<List<ApplicationDto>> callback);
    
    void findAllResources(Long appId, AsyncCallback<List<ImageDto>> callback);

    void deleteImage(Long id, Long parentId, AsyncCallback<Void> callback);

    void login(String name, String pwd, String answer, AsyncCallback<AuthenticationDto> callback);

    void changeLogin(String login, String password, String question, String answer, AsyncCallback<Void> callback);

    void getInfo(AsyncCallback<AuthenticationDto> callback);

    void getQuestion(AsyncCallback<String> callback);

}
