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
import com.katspow.datatron.shared.ApplicationDto;
import com.katspow.datatron.shared.AuthenticationDto;
import com.katspow.datatron.shared.ImageDto;

@SuppressWarnings("serial")
public class DatatronServiceImpl extends RemoteServiceServlet implements DatatronService {

    public static final String DEFAULT_LOGIN = "datatron";
    public static final String DEFAULT_PWD = "datatron";

    static {
        ObjectifyService.register(DatatronAuthentication.class);
        ObjectifyService.register(DatatronApplication.class);
        ObjectifyService.register(DatatronImage.class);
    }

    @Override
    public boolean createApplication(String name, String password) {

        Objectify ofy = ObjectifyService.ofy();

        List<DatatronApplication> appNames = ofy.load().type(DatatronApplication.class).filter("name", name)
                .ancestor(KeyFactory.createKey("RootApp", "app")).list();
        if (appNames.isEmpty()) {
            DatatronApplication datatronApp = new DatatronApplication(name, password);
            ofy.save().entity(datatronApp);
            return true;

        } else {
            return false;
        }

    }

    @Override
    public List<ApplicationDto> findAllApps() {
        List<ApplicationDto> result = new ArrayList<ApplicationDto>();
        List<DatatronApplication> listFound = ObjectifyService.ofy().load().type(DatatronApplication.class)
                .ancestor(KeyFactory.createKey("RootApp", "app")).list();

        for (DatatronApplication datatronApplication : listFound) {
            result.add(new ApplicationDto(datatronApplication.getId(), datatronApplication.getName(),
                    datatronApplication.getPassword()));
        }

        return result;
    }

    @Override
    public List<ImageDto> findAllResources(Long appId) {
        List<ImageDto> result = new ArrayList<ImageDto>();

        Objectify ofy = ObjectifyService.ofy();

        DatatronApplication application = ofy.load().type(DatatronApplication.class)
                .parent(KeyFactory.createKey("RootApp", "app")).id(appId).now();

        // com.google.appengine.api.datastore.Key key =
        // KeyFactory.createKey(GruiApplication.class.getSimpleName(), appId);
        // GruiApplication gruiApplication =
        // ofy.load().type(GruiApplication.class).ancestor(KeyFactory.createKey("RootApp",
        // "app")).filterKey(key).first().now();

        List<DatatronImage> listFound = ObjectifyService.ofy().load().type(DatatronImage.class).ancestor(application)
                .list();

        for (DatatronImage gruiResource : listFound) {
            result.add(new ImageDto(gruiResource.getId(), gruiResource.getParentId(), gruiResource.getName(),
                    gruiResource.getImageData(), gruiResource.getWidth(), gruiResource.getHeight()));
        }

        return result;
    }

    @Override
    public void deleteImage(Long id, Long parentId) {
        Objectify ofy = ObjectifyService.ofy();

        Key<DatatronApplication> application = ofy.load().type(DatatronApplication.class)
                .ancestor(KeyFactory.createKey("RootApp", "app")).keys().first().now();
        Key<DatatronImage> key = Key.create(application, DatatronImage.class, id);

        DatatronImage res = ofy.load().key(key).now();
        ofy.delete().entity(res).now();

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
