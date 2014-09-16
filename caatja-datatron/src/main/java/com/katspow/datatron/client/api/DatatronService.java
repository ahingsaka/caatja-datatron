package com.katspow.datatron.client.api;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.katspow.datatron.shared.ApplicationDto;
import com.katspow.datatron.shared.AuthenticationDto;
import com.katspow.datatron.shared.ImageDto;
import com.katspow.datatron.shared.ScoreDto;

@RemoteServiceRelativePath("service")
public interface DatatronService extends RemoteService {
    
    AuthenticationDto login(String name, String pwd, String answer);
    
    AuthenticationDto getInfo();
    
    String getQuestion();
    
    boolean createApplication(String name, String password, String maxNbScores);
    
    boolean createScore(Long appId, String name, int score);
    
    void updateScore(Long appId, Long scoreId, String name, int score);
    
    void deleteApplication(Long idApp);

    List<ApplicationDto> findAllApps();
    
    List<ScoreDto> findAllScores(Long appId);

    List<ImageDto> findAllResources(Long appId);
    
    void deleteImage(Long id, Long parentId);
    
    void changeLogin(String login, String password, String question, String answer);
    
    void updatePasswordApp(Long id, String password);

}
