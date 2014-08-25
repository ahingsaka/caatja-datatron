package com.katspow.datatron.client.view.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;

public class Spinner extends Composite {

    private static SpinnerUiBinder uiBinder = GWT.create(SpinnerUiBinder.class);

    interface SpinnerUiBinder extends UiBinder<Widget, Spinner> {
    }
    
    @UiField
    DialogBox dialogBox;

    public Spinner() {
        initWidget(uiBinder.createAndBindUi(this));
        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
    }

    public void show() {
        dialogBox.center();
    }
    
    public void hide() {
        dialogBox.hide();
    }

}
