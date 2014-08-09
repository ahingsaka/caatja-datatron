package com.katspow.datatron.client.view.popup;

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
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.utils.Msg;
import com.katspow.datatron.client.view.HomeView;

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

        if (text.isEmpty()) {
            Msg.setErrorMsg(errorMsg, "Please enter a name");
            return;
        }

        dataService.createApplication(text, new AsyncCallback<Boolean>() {
            public void onSuccess(Boolean result) {
                if (result) {
                    nameFld.setText("");
                    HomeView.showApps();
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
