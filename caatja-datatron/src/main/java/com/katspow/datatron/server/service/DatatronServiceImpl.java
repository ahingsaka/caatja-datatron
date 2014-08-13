package com.katspow.datatron.server.service;

import java.util.ArrayList;
import java.util.List;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.server.entity.DatatronApplication;
import com.katspow.datatron.server.entity.DatatronImage;
import com.katspow.datatron.shared.ApplicationDto;
import com.katspow.datatron.shared.ImageDto;

@SuppressWarnings("serial")
public class DatatronServiceImpl extends RemoteServiceServlet implements DatatronService {

    static {
        ObjectifyService.register(DatatronApplication.class);
        ObjectifyService.register(DatatronImage.class);
    }

    @Override
    public boolean createApplication(String name) {

        Objectify ofy = ObjectifyService.ofy();

        List<DatatronApplication> appNames = ofy.load().type(DatatronApplication.class).filter("name", name)
                .ancestor(KeyFactory.createKey("RootApp", "app")).list();
        if (appNames.isEmpty()) {
            DatatronApplication gruiApp = new DatatronApplication(name);
            ofy.save().entity(gruiApp);
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
            result.add(new ApplicationDto(datatronApplication.getId(), datatronApplication.getName()));
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

}
