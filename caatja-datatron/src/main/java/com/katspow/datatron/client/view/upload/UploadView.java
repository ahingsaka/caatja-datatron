package com.katspow.datatron.client.view.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.Widget;

public class UploadView extends Composite {

    private static UploadViewUiBinder uiBinder = GWT.create(UploadViewUiBinder.class);

    interface UploadViewUiBinder extends UiBinder<Widget, UploadView> {
    }

    @UiField
    DialogBox dialogBox;

    @UiField
    HTML errorMsg;

    @UiField
    SubmitButton uploadBtn;

    @UiField
    SubmitButton cancelBtn;

    public UploadView() {
        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);

        cancelBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        uploadBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            }
        });
    }

    public void center() {
        dialogBox.center();
    }

}
