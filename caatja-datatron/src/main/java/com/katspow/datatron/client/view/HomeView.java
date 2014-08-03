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
    HTMLPanel mainPanel;

    @UiField
    HTML infoMsg;

    public HomeView() {
        initWidget(uiBinder.createAndBindUi(this));

        setInfoMsg(WELCOME);

        listApp.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                mainPanel.clear();
            }
        });
    }

    public void setInfoMsg(String msg) {
        infoMsg.setHTML("<h4 class='alert_info'>" + msg + "</h4>");
    }

}
