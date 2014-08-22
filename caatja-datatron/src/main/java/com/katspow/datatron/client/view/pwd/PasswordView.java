package com.katspow.datatron.client.view.pwd;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PasswordView extends Composite {

    private static PasswordViewUiBinder uiBinder = GWT.create(PasswordViewUiBinder.class);

    interface PasswordViewUiBinder extends UiBinder<Widget, PasswordView> {
    }

    public PasswordView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
