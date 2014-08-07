package com.katspow.datatron.client.view.applist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AppLstView extends Composite {
    
    interface MyUiBinder extends UiBinder<Widget, AppLstView> {
    }
    
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
    public AppLstView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
