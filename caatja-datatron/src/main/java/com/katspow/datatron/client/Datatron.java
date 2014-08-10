package com.katspow.datatron.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.datatron.client.view.HomeView;
import com.katspow.datatron.client.view.applist.AppLstView;
import com.katspow.datatron.client.view.imgList.ImgLstView;
import com.katspow.datatron.client.view.popup.DatatronPopup;
import com.katspow.datatron.shared.ApplicationDto;

public class Datatron implements EntryPoint {

    private static HomeView homeView = new HomeView();

    private static AppLstView appLstView;
    
    private static ImgLstView imgLstView;
    
    private static ApplicationDto selectedApplication;

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(getHomeView());
    }

    public static void showApps() {
        appLstView = new AppLstView();
        getHomeView().setViewInMain(appLstView);
    }
    
    public static void setSelectedApplication(ApplicationDto object) {
        selectedApplication = object;
        appLstView.setSelectedApplication(object);
        homeView.setSelectedApplication(object);
    }
    
    public static void showImgs() {
        if (selectedApplication == null) {
            DatatronPopup datatronPopup = new DatatronPopup("Please select an application first");
            datatronPopup.center();
            
        } else {
            imgLstView = new ImgLstView();
            getHomeView().setViewInMain(imgLstView);
        }
    }
    
    public static ApplicationDto getSelectedApplication() {
        return selectedApplication;
    }

    public static HomeView getHomeView() {
        return homeView;
    }

    public static AppLstView getAppLstView() {
        return appLstView;
    }
    
    public static ImgLstView getImgLstView() {
        return imgLstView;
    }

}
