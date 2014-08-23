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
import com.katspow.datatron.client.utils.Msg;

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
    SubmitButton saveBtn;

    public PasswordView() {
        initWidget(uiBinder.createAndBindUi(this));
        
        saveBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showHomeView();
            }
        });
    }
    
    public void showFirstTimeMsg() {
        Msg.setInfoMsg(infoMsg, "First time accessing DATATRON. Please change login and password !");
    }

}
