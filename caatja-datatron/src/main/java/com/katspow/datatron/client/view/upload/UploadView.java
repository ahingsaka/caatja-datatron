package com.katspow.datatron.client.view.upload;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class UploadView extends Composite {

    private static UploadViewUiBinder uiBinder = GWT.create(UploadViewUiBinder.class);

    interface UploadViewUiBinder extends UiBinder<Widget, UploadView> {
    }

    public UploadView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
