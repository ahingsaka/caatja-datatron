package com.katspow.datatron.client.view.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateAppPopup extends Composite {
    
    interface CreateAppPopupUiBinder extends UiBinder<Widget, CreateAppPopup> {
    }
    
    private static CreateAppPopupUiBinder uiBinder = GWT.create(CreateAppPopupUiBinder.class);
    
    @UiField
    HTMLPanel container;
    
    @UiField
    DialogBox dialogBox;
    
    public CreateAppPopup() {
        
        initWidget(uiBinder.createAndBindUi(this));
        
        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);
        
//        setWidget(new Label("Click outside to close"));
//        
//        errorLabel = new Label();
//        
//        final Button submitBtn = new Button("Submit");
//        
//        nameField = new TextBox();
//        
//        submitBtn.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
////                callService();
//            }
//        });
    }

    public void center() {
        dialogBox.center();
    }

}
