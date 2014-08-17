package com.katspow.datatron.client.view.popup;

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

public class DatatronPopup extends Composite {

    private static DatatronPopupUiBinder uiBinder = GWT.create(DatatronPopupUiBinder.class);

    interface DatatronPopupUiBinder extends UiBinder<Widget, DatatronPopup> {
    }

    @UiField
    DialogBox dialogBox;

    @UiField
    HTML title;

    @UiField
    HTML message;

    @UiField
    SubmitButton okBtn;

    @UiField
    SubmitButton cancelBtn;

    private PopupCallback callback;

    public DatatronPopup(String infoMsg) {
        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);

        cancelBtn.setVisible(false);

        okBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        setPopupTitle("Warning");
        setMessage(infoMsg);
    }

    public DatatronPopup(String title, String msg) {
        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);

        cancelBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        okBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                callback.onOk();
            }
        });

        setPopupTitle(title);
        setMessage(msg);

    }

    protected void setPopupTitle(String msg) {
        title.setHTML("<h3>" + msg + "</h3>");
    }

    public void setMessage(String message) {
        this.message.setHTML(message);
    }

    public void center() {
        dialogBox.center();
    }

    public void setCallback(PopupCallback callback) {
        this.callback = callback;
    }

    public void hide() {
        this.dialogBox.hide();
    }

}
