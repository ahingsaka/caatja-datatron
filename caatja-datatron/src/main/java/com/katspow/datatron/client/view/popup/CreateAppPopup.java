package com.katspow.datatron.client.view.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class CreateAppPopup extends Composite {
    
    interface CreateAppPopupUiBinder extends UiBinder<Widget, CreateAppPopup> {
    }
    
    private static CreateAppPopupUiBinder uiBinder = GWT.create(CreateAppPopupUiBinder.class);
    
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
        // TODO Auto-generated method stub
        
    }

    public void center() {
        dialogBox.center();
    }

}
