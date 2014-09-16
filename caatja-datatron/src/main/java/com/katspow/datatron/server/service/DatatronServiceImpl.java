package com.katspow.datatron.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.server.entity.DatatronApplication;
import com.katspow.datatron.server.entity.DatatronAuthentication;
import com.katspow.datatron.server.entity.DatatronImage;
import com.katspow.datatron.server.entity.DatatronRoot;
import com.katspow.datatron.server.entity.DatatronScore;
import com.katspow.datatron.shared.ApplicationDto;
import com.katspow.datatron.shared.AuthenticationDto;
import com.katspow.datatron.shared.ImageDto;
import com.katspow.datatron.shared.ScoreDto;

@SuppressWarnings("serial")
public class DatatronServiceImpl extends RemoteServiceServlet implements DatatronService {

    public static final String DEFAULT_LOGIN = "datatron";
    public static final String DEFAULT_PWD = "datatron";

    static {
        ObjectifyService.register(DatatronAuthentication.class);
        ObjectifyService.register(DatatronApplication.class);
        ObjectifyService.register(DatatronImage.class);
        ObjectifyService.register(DatatronScore.class);
    }

    @Override
    public boolean createApplication(String name, String password, String maxNbScores) {

        Objectify ofy = ObjectifyService.ofy();

        List<DatatronApplication> appNames = ofy.load().type(DatatronApplication.class).filter("name", name)
                .ancestor(Key.create(DatatronRoot.class, "app")).list();
        if (appNames.isEmpty()) {
            DatatronApplication datatronApp = new DatatronApplication(name, password, maxNbScores);
            ofy.save().entity(datatronApp);
            return true;

        } else {
            return false;
        }

    }

    @Override
    public boolean createScore(Long appId, String name, int score) {
        
        boolean result = true;
        
        Objectify ofy = ObjectifyService.ofy();

        DatatronApplication application = ofy.load().type(DatatronApplication.class)
               .parent(Key.create(DatatronRoot.class, "app")).id(appId).now();
        
        List<DatatronScore> listFound = ofy.load().type(DatatronScore.class).ancestor(application)
                .list();
        
        // Verify if max nb of scores was reached
        if (application.getMaxNbScores() == listFound.size()) {
            result = false;
            
        } else {
            Key<DatatronApplication> appKey = Key.create(Key.create(DatatronRoot.class, "app"), DatatronApplication.class,
                    appId);
            DatatronScore datatronScore = new DatatronScore(listFound.size() + 1, name, score, appKey);
            ofy.save().entity(datatronScore).now();
        }
        
        return result;
        
    }
    
    @Override
    public void updateScore(Long appId, Long scoreId, String name, int score) {
        
        Objectify ofy = ObjectifyService.ofy();
        
        DatatronApplication application = ofy.load().type(DatatronApplication.class)
                .parent(Key.create(DatatronRoot.class, "app")).id(appId).now();
        
        DatatronScore datatronScore = ofy.load().type(DatatronScore.class).parent(application).id(scoreId).now();
        datatronScore.setName(name);
        datatronScore.setScore(score);
        
        ofy.save().entity(datatronScore).now();
    }

    @Override
    public void deleteApplication(Long appId) {

        Objectify ofy = ObjectifyService.ofy();

        DatatronApplication application = ofy.load().type(DatatronApplication.class)
                .parent(Key.create(DatatronRoot.class, "app")).id(appId).now();

        Key<DatatronApplication> keyApp = ofy.load().type(DatatronApplication.class)
                .ancestor(Key.create(DatatronRoot.class, "app")).keys().first().now();

        // Delete all images first
        List<DatatronImage> listFound = ObjectifyService.ofy().load().type(DatatronImage.class).ancestor(application)
                .list();

        for (DatatronImage datatronImage : listFound) {
            Long imgId = datatronImage.getId();
            Key<DatatronImage> key = Key.create(keyApp, DatatronImage.class, imgId);
            DatatronImage res = ofy.load().key(key).now();
            ofy.delete().entity(res).now();
        }

        // Delete the app
        ofy.delete().entity(application).now();

    }

    @Override
    public void updatePasswordApp(Long appId, String password) {

        Objectify ofy = ObjectifyService.ofy();

        DatatronApplication application = ofy.load().type(DatatronApplication.class)
                .parent(Key.create(DatatronRoot.class, "app")).id(appId).now();

        application.setPassword(password);
        ofy.save().entity(application).now();
    }

    @Override
    public List<ApplicationDto> findAllApps() {
        List<ApplicationDto> result = new ArrayList<ApplicationDto>();
        List<DatatronApplication> listFound = ObjectifyService.ofy().load().type(DatatronApplication.class)
                .ancestor(Key.create(DatatronRoot.class, "app")).list();

        for (DatatronApplication datatronApplication : listFound) {
            result.add(new ApplicationDto(datatronApplication.getId(), datatronApplication.getName(),
                    datatronApplication.getPassword(), datatronApplication.getMaxNbScores()));
        }

        return result;
    }

    @Override
    public List<ScoreDto> findAllScores(Long appId) {
        List<ScoreDto> scores = new ArrayList<ScoreDto>();

        Objectify ofy = ObjectifyService.ofy();

        DatatronApplication application = ofy.load().type(DatatronApplication.class)
                .parent(Key.create(DatatronRoot.class, "app")).id(appId).now();

        List<DatatronScore> listFound = ObjectifyService.ofy().load().type(DatatronScore.class).ancestor(application).order("numOrder")
                .list();

        for (DatatronScore datatronScore : listFound) {
            scores.add(new ScoreDto(datatronScore.getId(), datatronScore.getNumOrder(), datatronScore.getName(),
                    datatronScore.getScore()));
        }

        return scores;
    }

    @Override
    public List<ImageDto> findAllResources(Long appId) {
        List<ImageDto> result = new ArrayList<ImageDto>();

        Objectify ofy = ObjectifyService.ofy();

        DatatronApplication application = ofy.load().type(DatatronApplication.class)
                .parent(Key.create(DatatronRoot.class, "app")).id(appId).now();

        // com.google.appengine.api.datastore.Key key =
        // KeyFactory.createKey(GruiApplication.class.getSimpleName(), appId);
        // GruiApplication gruiApplication =
        // ofy.load().type(GruiApplication.class).ancestor(KeyFactory.createKey("RootApp",
        // "app")).filterKey(key).first().now();

        List<DatatronImage> listFound = ofy.load().type(DatatronImage.class).ancestor(application)
                .list();

        for (DatatronImage datatronImg : listFound) {
            result.add(new ImageDto(datatronImg.getId(), datatronImg.getParentId(), datatronImg.getName(), datatronImg
                    .getImageData(), datatronImg.getWidth(), datatronImg.getHeight()));
        }

        return result;
    }

    @Override
    public void deleteImage(Long id, Long parentId) {
        Objectify ofy = ObjectifyService.ofy();

        // Key<DatatronApplication> application =
        // ofy.load().type(DatatronApplication.class)
        // .ancestor(KeyFactory.createKey("RootApp",
        // "app")).keys().first().now();
        // Key<DatatronImage> key = Key.create(application, DatatronImage.class,
        // id);
        //
        // DatatronImage res = ofy.load().key(key).now();

        DatatronApplication application = ofy.load().type(DatatronApplication.class)
                .parent(Key.create(DatatronRoot.class, "app")).id(parentId).now();
        
        DatatronImage datatronImage = ofy.load().type(DatatronImage.class).parent(application).id(id).now();

        ofy.delete().entity(datatronImage).now();

        // ofy.delete().entity(res).now();

    }

    @Override
    public AuthenticationDto getInfo() {
        Objectify ofy = ObjectifyService.ofy();
        List<DatatronAuthentication> authentication = ofy.load().type(DatatronAuthentication.class)
                .ancestor(KeyFactory.createKey("RootAuth", "auth")).list();

        if (authentication.isEmpty()) {
            return null;
        } else {
            DatatronAuthentication datatronAuthentication = authentication.get(0);

            AuthenticationDto auth = new AuthenticationDto();
            auth.setLogin(datatronAuthentication.getLogin());
            auth.setPassword(datatronAuthentication.getPassword());
            auth.setQuestion(datatronAuthentication.getQuestion());
            auth.setAnswer(datatronAuthentication.getAnswer());

            return auth;
        }
    }

    @Override
    public AuthenticationDto login(String login, String pwd, String answer) throws IllegalArgumentException {

        AuthenticationDto auth = new AuthenticationDto();

        login = escapeHtml(login);
        pwd = escapeHtml(pwd);
        answer = escapeHtml(answer);

        Objectify ofy = ObjectifyService.ofy();
        List<DatatronAuthentication> authentication = ofy.load().type(DatatronAuthentication.class)
                .ancestor(KeyFactory.createKey("RootAuth", "auth")).list();

        if (authentication.isEmpty()) {

            auth.setFirstTime(true);

            boolean loginOk = DEFAULT_LOGIN.equals(login) && DEFAULT_PWD.equals(pwd);
            auth.setOk(loginOk);

        } else {

            auth.setFirstTime(false);

            DatatronAuthentication datatronAuthentication = authentication.get(0);

            if (!answer.isEmpty()) {

                boolean answerOk = answer.equals(datatronAuthentication.getAnswer());
                auth.setOk(answerOk);

            } else {

                boolean loginOk = datatronAuthentication.getLogin().equals(login)
                        && datatronAuthentication.getPassword().equals(pwd);
                auth.setOk(loginOk);

            }

        }

        return auth;
    }

    /**
     * Escape an html string. Escaping data received from the client helps to
     * prevent cross-site script vulnerabilities.
     * 
     * @param html
     *            the html string to escape
     * @return the escaped string
     */
    private String escapeHtml(String html) {
        if (html == null) {
            return null;
        }
        return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    @Override
    public void changeLogin(String login, String password, String question, String answer) {
        Objectify ofy = ObjectifyService.ofy();
        DatatronAuthentication gruiAuthentication = new DatatronAuthentication("1", login, password, question, answer);
        ofy.save().entity(gruiAuthentication);
    }

    @Override
    public String getQuestion() {
        Objectify ofy = ObjectifyService.ofy();
        List<DatatronAuthentication> authentication = ofy.load().type(DatatronAuthentication.class)
                .ancestor(KeyFactory.createKey("RootAuth", "auth")).list();

        if (authentication.isEmpty()) {
            return null;

        } else {
            DatatronAuthentication datatronAuthentication = authentication.get(0);
            String question = datatronAuthentication.getQuestion();
            return question;
        }

    }

    

}
