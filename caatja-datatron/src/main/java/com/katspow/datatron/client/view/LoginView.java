package com.katspow.datatron.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;

public class LoginView extends Composite {

    private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

    interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
    }
    
    @UiField
    SubmitButton login;

    public LoginView() {
        initWidget(uiBinder.createAndBindUi(this));
        
        login.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showHomeView();
            }
        });
    }

}
