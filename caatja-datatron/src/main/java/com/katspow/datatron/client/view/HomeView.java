package com.katspow.datatron.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.utils.Msg;
import com.katspow.datatron.client.view.applist.AppLstView;
import com.katspow.datatron.client.view.popup.CreateAppPopup;
import com.katspow.datatron.shared.ApplicationDto;

public class HomeView extends Composite {

    interface MyUiBinder extends UiBinder<Widget, HomeView> {
    }

    private static final String WELCOME = "Welcome to the DATATRON ! Please select an action on the left menu.";

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    Anchor listApp;

    @UiField
    Anchor newApp;

    @UiField
    Anchor changePwd;

    @UiField
    static HTMLPanel mainPanel;

    @UiField
    HTML infoMsg;

    private static AppLstView appLstView;

    public HomeView() {
        initWidget(uiBinder.createAndBindUi(this));

        Msg.setInfoMsg(infoMsg, WELCOME);

        listApp.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
               showApps();
            }
        });

        newApp.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                showCreateAppPopup();
            }
        });
    }

    public static void showApps() {
        mainPanel.clear();
        mainPanel.add(getAppListView());
    }
    
    protected static AppLstView getAppListView() {
        //if (appLstView == null) {
            appLstView = new AppLstView();
        //}
        return appLstView;
    }

    protected void showCreateAppPopup() {
        CreateAppPopup createAppPopup = new CreateAppPopup();
        createAppPopup.center();
    }

    public void setInfoMsg(String msg) {
        infoMsg.setHTML("<h4 class='alert_info'>" + msg + "</h4>");
    }

}
