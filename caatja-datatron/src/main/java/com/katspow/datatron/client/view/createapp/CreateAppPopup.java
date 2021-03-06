package com.katspow.datatron.client.view.createapp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.utils.Msg;

public class CreateAppPopup extends Composite {

    interface CreateAppPopupUiBinder extends UiBinder<Widget, CreateAppPopup> {
    }

    private static CreateAppPopupUiBinder uiBinder = GWT.create(CreateAppPopupUiBinder.class);
    
    private static final DatatronServiceAsync dataService = GWT.create(DatatronService.class);

    @UiField
    HTMLPanel container;

    @UiField
    DialogBox dialogBox;

    @UiField
    TextBox nameFld;
    
    @UiField
    TextBox pwdFld;
    
    @UiField
    TextBox maxNbScoresFld;

    @UiField
    HTML errorMsg;

    @UiField
    SubmitButton createBtn;

    @UiField
    SubmitButton cancelBtn;

    public CreateAppPopup() {

        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);

        cancelBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        createBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                callService();
            }
        });
    }

    protected void callService() {

        String text = nameFld.getText();
        String password = pwdFld.getText();
        String score = maxNbScoresFld.getText();

        if (text.isEmpty()) {
            Msg.setErrorMsg(errorMsg, "Please enter a name");
            return;
        }
        
        if (password.isEmpty()) {
            Msg.setErrorMsg(errorMsg, "Please enter a password");
            return;
        }
        
        if (score.isEmpty()) {
            Msg.setErrorMsg(errorMsg, "Please enter the maximum number of scores");
            return;
        }

        dataService.createApplication(text, password, score, new AsyncCallback<Boolean>() {
            public void onSuccess(Boolean result) {
                if (result) {
                    nameFld.setText("");
                    Datatron.showApps();
                    dialogBox.hide();
                } else {
                    Msg.setErrorMsg(errorMsg, "App name already exists !");
                }
            }

            public void onFailure(Throwable caught) {
                Msg.setErrorMsg(errorMsg, "Exception !");
            }
        });

    }

    public void center() {
        dialogBox.center();
    }

}
