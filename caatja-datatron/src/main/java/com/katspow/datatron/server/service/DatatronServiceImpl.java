package com.katspow.datatron.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.server.entity.DatatronApplication;
import com.katspow.datatron.shared.ApplicationDto;

@SuppressWarnings("serial")
public class DatatronServiceImpl extends RemoteServiceServlet implements DatatronService {
    
    static {
        ObjectifyService.register(DatatronApplication.class);
    }
    
    @Override
    public boolean createApplication(String name) {
        
        Objectify ofy = ObjectifyService.ofy();
        
        List<DatatronApplication> appNames = ofy.load().type(DatatronApplication.class).filter("name", name).ancestor(KeyFactory.createKey("RootApp", "app")).list();
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
        List<DatatronApplication> listFound = ObjectifyService.ofy().load().type(DatatronApplication.class).ancestor(KeyFactory.createKey("RootApp", "app")).list();
        
        for (DatatronApplication datatronApplication : listFound) {
            result.add(new ApplicationDto(datatronApplication.getId(), datatronApplication.getName()));
        }
        
        return result;
    }

}
