package com.katspow.datatron.client.view.data;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DataView extends Composite {

    private static DataViewUiBinder uiBinder = GWT.create(DataViewUiBinder.class);

    interface DataViewUiBinder extends UiBinder<Widget, DataView> {
    }

    public DataView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
