package com.katspow.datatron.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class HomeView extends Composite {
    
    interface MyUiBinder extends UiBinder<Widget, HomeView> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    public HomeView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
