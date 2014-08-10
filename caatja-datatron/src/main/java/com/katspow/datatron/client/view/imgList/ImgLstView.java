package com.katspow.datatron.client.view.imgList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ImgLstView extends Composite {

    private static ImgLstViewUiBinder uiBinder = GWT.create(ImgLstViewUiBinder.class);

    interface ImgLstViewUiBinder extends UiBinder<Widget, ImgLstView> {
    }

    public ImgLstView() {
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    public ImgLstView(String firstName) {
        initWidget(uiBinder.createAndBindUi(this));
    }

   

}
