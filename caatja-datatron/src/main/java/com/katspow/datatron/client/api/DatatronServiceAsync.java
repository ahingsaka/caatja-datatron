package com.katspow.datatron.client.api;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.katspow.datatron.shared.ApplicationDto;

public interface DatatronServiceAsync {
    
    void createApplication(String name, AsyncCallback<Boolean> callback);

    void findAllApps(AsyncCallback<List<ApplicationDto>> callback);

}
