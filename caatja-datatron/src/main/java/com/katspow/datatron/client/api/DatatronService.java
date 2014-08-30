package com.katspow.datatron.client.api;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.katspow.datatron.shared.ApplicationDto;
import com.katspow.datatron.shared.AuthenticationDto;
import com.katspow.datatron.shared.ImageDto;

@RemoteServiceRelativePath("service")
public interface DatatronService extends RemoteService {
    
    AuthenticationDto login(String name, String pwd, String answer);
    
    AuthenticationDto getInfo();
    
    String getQuestion();
    
    boolean createApplication(String name, String application);
    
    void deleteApplication(Long idApp);

    List<ApplicationDto> findAllApps();

    List<ImageDto> findAllResources(Long appId);
    
    void deleteImage(Long id, Long parentId);
    
    void changeLogin(String login, String password, String question, String answer);
    
    void updatePasswordApp(Long id, String password);

}
