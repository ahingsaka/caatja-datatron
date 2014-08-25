package com.katspow.datatron.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.view.HomeView;
import com.katspow.datatron.client.view.LoginView;
import com.katspow.datatron.client.view.applist.AppLstView;
import com.katspow.datatron.client.view.data.DataView;
import com.katspow.datatron.client.view.imgList.ImgLstView;
import com.katspow.datatron.client.view.popup.DatatronPopup;
import com.katspow.datatron.client.view.popup.Spinner;
import com.katspow.datatron.client.view.pwd.PasswordView;
import com.katspow.datatron.client.view.scores.ScoresView;
import com.katspow.datatron.client.view.upload.UploadView;
import com.katspow.datatron.shared.ApplicationDto;

public class Datatron implements EntryPoint {

    private static HomeView homeView;

    private static AppLstView appLstView;

    private static ImgLstView imgLstView;

    private static ApplicationDto selectedApplication;

    private static UploadView uploadView;

    private static ScoresView scoresView;

    private static DataView dataView;

    private static PasswordView passwordView;

    private static Spinner spinner;

    public static final DatatronServiceAsync dataService = GWT.create(DatatronService.class);

    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new LoginView());
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

    public static void showUpload() {
        if (selectedApplication == null) {
            DatatronPopup datatronPopup = new DatatronPopup("Please select an application first");
            datatronPopup.center();

        } else {
            uploadView = new UploadView();
            uploadView.center();
        }
    }

    public static void showScores() {
        if (selectedApplication == null) {
            DatatronPopup datatronPopup = new DatatronPopup("Please select an application first");
            datatronPopup.center();
        } else {
            scoresView = new ScoresView();
            getHomeView().setViewInMain(scoresView);
        }
    }

    public static void showData() {
        if (!checkSelectedApp()) {
            dataView = new DataView();
            getHomeView().setViewInMain(dataView);
        }
    }

    public static void showPasswordView() {
        passwordView = new PasswordView();
        getHomeView().setViewInMain(passwordView);
    }

    private static boolean checkSelectedApp() {
        if (selectedApplication == null) {
            DatatronPopup datatronPopup = new DatatronPopup("Please select an application first");
            datatronPopup.center();
            return true;
        } else {
            return false;
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

    public static ScoresView getScoresView() {
        return scoresView;
    }

    public static DataView getDataView() {
        return dataView;
    }

    public static PasswordView getPasswordView() {
        return passwordView;
    }

    public static void loading() {
        getSpinner().show();
    }
    
    public static void stopLoading() {
        getSpinner().hide();
    }

    public static Spinner getSpinner() {
        if (spinner == null) {
            spinner = new Spinner();
        }

        return spinner;
    }

    public static void showHomeView() {
        RootPanel.get().clear();
        homeView = new HomeView();
        RootPanel.get().add(homeView);
    }

}
