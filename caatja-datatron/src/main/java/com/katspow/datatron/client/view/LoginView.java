package com.katspow.datatron.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.api.SimpleCallback;
import com.katspow.datatron.client.view.popup.DatatronPopup;
import com.katspow.datatron.shared.AuthenticationDto;

public class LoginView extends Composite {

    private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

    interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {
    }

    private static final DatatronServiceAsync dataService = GWT.create(DatatronService.class);

    @UiField
    SubmitButton login;

    @UiField
    TextBox user;

    @UiField
    PasswordTextBox pass;

    @UiField
    Label msg;

    public LoginView() {
        initWidget(uiBinder.createAndBindUi(this));

        user.getElement().setPropertyString("placeholder", "Username");
        pass.getElement().setPropertyString("placeholder", "Password");

        login.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                callAuthService();
            }
        });
    }

    protected void callAuthService() {

        dataService.login(user.getText(), pass.getText(), new SimpleCallback<AuthenticationDto>() {
            public void onSuccess(AuthenticationDto result) {

                msg.setText("");

                if (!result.isFirstTime() && result.isOk()) {
                    Datatron.showHomeView();

                } else if (result.isFirstTime() && result.isOk()) {
                    Datatron.showHomeView();
                    Datatron.showPasswordView();
                    Datatron.getPasswordView().showFirstTimeMsg();
                    Datatron.getHomeView().displayMenuLinks(false);
                    
                } else {
                    msg.setText("Wrong login or/and password");
                }

            }
        });

    }

}
