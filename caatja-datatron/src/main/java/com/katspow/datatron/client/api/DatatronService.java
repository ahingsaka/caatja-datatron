package com.katspow.datatron.client.api;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.katspow.datatron.shared.ApplicationDto;

@RemoteServiceRelativePath("service")
public interface DatatronService extends RemoteService {

    boolean createApplication(String name);

    List<ApplicationDto> findAllApps();

}
