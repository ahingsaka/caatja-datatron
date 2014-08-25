package com.katspow.datatron.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.api.SimpleLoadingCallback;
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
    Anchor forgot;
    
    @UiField
    Label msg;
    
    @UiField
    TextBox question;
    
    @UiField
    TextBox answer;
    
    @UiField
    SubmitButton forgetPwd;
    
    @UiField
    Anchor back;
    

    public LoginView() {
        initWidget(uiBinder.createAndBindUi(this));
        
        question.setVisible(false);
        question.setEnabled(false);
        answer.setVisible(false);
        back.setVisible(false);
        forgetPwd.setVisible(false);

        user.getElement().setPropertyString("placeholder", "Username");
        pass.getElement().setPropertyString("placeholder", "Password");

        login.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                callAuthService();
            }
        });
        
        forgot.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                pass.setText(null);
                getQuestion();
            }
        });
        
        back.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                question.setVisible(false);
                answer.setVisible(false);
                back.setVisible(false);
                forgetPwd.setVisible(false);
                
                user.setVisible(true);
                pass.setVisible(true);
                login.setVisible(true);
                forgot.setVisible(true);
                
                answer.setText(null);
            }
        });
        
        forgetPwd.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (answer.getText().isEmpty()) {
                    msg.setText("Please give an answer");
                } else {
                    callAuthService();
                }
            }
        });
    }

    protected void getQuestion() {
        
        Datatron.loading();
        
        dataService.getQuestion(new SimpleLoadingCallback<String>() {
            public void onOk(String result) {
                question.setVisible(true);
                
                user.setVisible(false);
                pass.setVisible(false);
                login.setVisible(false);
                forgot.setVisible(false);
                
                back.setVisible(true);
                
                if (result == null) {
                    question.getElement().setPropertyString("placeholder", "No question defined");
                    forgetPwd.setVisible(false);
                } else {
                    question.getElement().setPropertyString("placeholder", result);
                    answer.setVisible(true);
                    forgetPwd.setVisible(true);
                }
            }
        });
        
    }

    protected void callAuthService() {
        
        Datatron.loading();
        dataService.login(user.getText(), pass.getText(), answer.getText(),new SimpleLoadingCallback<AuthenticationDto>() {
            public void onOk(AuthenticationDto result) {

                msg.setText("");

                if (!result.isFirstTime() && result.isOk()) {
                    Datatron.showHomeView();

                } else if (result.isFirstTime() && result.isOk()) {
                    Datatron.showHomeView();
                    Datatron.showPasswordView();
                    Datatron.getPasswordView().showFirstTimeMsg();
                    Datatron.getHomeView().displayMenuLinks(false);
                    
                } else {
                    msg.setText("Authentication error");
                }

            }
        });

    }

}
