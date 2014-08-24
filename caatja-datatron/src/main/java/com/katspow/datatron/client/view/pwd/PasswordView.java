package com.katspow.datatron.client.view.pwd;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.SimpleCallback;
import com.katspow.datatron.client.utils.Msg;
import com.katspow.datatron.shared.AuthenticationDto;

public class PasswordView extends Composite {

    private static PasswordViewUiBinder uiBinder = GWT.create(PasswordViewUiBinder.class);

    interface PasswordViewUiBinder extends UiBinder<Widget, PasswordView> {
    }

    @UiField
    HTML infoMsg;

    @UiField
    TextBox login;

    @UiField
    TextBox newPwd;

    @UiField
    TextBox question;

    @UiField
    TextBox answer;

    @UiField
    SubmitButton saveBtn;

    public PasswordView() {
        initWidget(uiBinder.createAndBindUi(this));

        saveBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                changeAuth();
            }
        });

        loadAuth();
    }

    protected void changeAuth() {

        String sLogin = login.getText();
        String pwd = newPwd.getText();
        String sQuestion = question.getText();
        String sAnswer = answer.getText();

        if (sLogin.isEmpty() || pwd.isEmpty()) {
            Msg.setErrorMsg(infoMsg, "Login and password are mandatory");
            return;
        }
        
        if (!sQuestion.isEmpty() || !sAnswer.isEmpty()) {
            if (sQuestion.isEmpty() || sAnswer.isEmpty()) {
                Msg.setErrorMsg(infoMsg, "Fill both question and answer when you fill one of them");
                return;
            }
        }

        Datatron.dataService.changeLogin(sLogin, pwd, sQuestion, sAnswer, new SimpleCallback<Void>() {
            public void onSuccess(Void result) {
                Msg.setInfoMsg(infoMsg, "Your credentials were succesfully updated");
            }
        });
    }

    private void loadAuth() {
        Datatron.dataService.getInfo(new SimpleCallback<AuthenticationDto>() {
            public void onSuccess(AuthenticationDto authenticationDto) {
                if (authenticationDto != null) {
                    login.setText(authenticationDto.getLogin());
                    newPwd.setText(authenticationDto.getPassword());
                    question.setText(authenticationDto.getQuestion());
                    answer.setText(authenticationDto.getAnswer());
                }
            }
        });
    }

    public void showFirstTimeMsg() {
        Msg.setInfoMsg(infoMsg, "First time accessing DATATRON. Please change login and password !");
    }

}
