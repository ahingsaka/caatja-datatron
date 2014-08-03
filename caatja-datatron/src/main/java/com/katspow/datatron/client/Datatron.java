package com.katspow.datatron.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.datatron.client.view.HomeView;

public class Datatron implements EntryPoint {

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new HomeView());
    }

}
