package com.katspow.datatron.client.view.createscore;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.Widget;

public class CreateScorePopup extends Composite {

    private static CreateScorePopupUiBinder uiBinder = GWT.create(CreateScorePopupUiBinder.class);

    interface CreateScorePopupUiBinder extends UiBinder<Widget, CreateScorePopup> {
    }

    @UiField
    HTMLPanel container;

    @UiField
    DialogBox dialogBox;

    @UiField
    SubmitButton okBtn;

    @UiField
    SubmitButton cancelBtn;

    public CreateScorePopup() {
        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);

        cancelBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
    }

    public void center() {
        dialogBox.center();
    }

}
