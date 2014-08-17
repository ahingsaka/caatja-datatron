package com.katspow.datatron.client.view.imgList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.shared.ImageDto;

public class ImgPopup extends Composite {

    private static ImgPopupUiBinder uiBinder = GWT.create(ImgPopupUiBinder.class);

    interface ImgPopupUiBinder extends UiBinder<Widget, ImgPopup> {
    }
    
    @UiField
    DialogBox dialogBox;

    @UiField
    Image image;
    
    public ImgPopup(ImageDto imageDto) {
        initWidget(uiBinder.createAndBindUi(this));
        
        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);
        
        String base64Data = "data:image/png;base64," + imageDto.getImageData();
        image.setVisibleRect(0, 0, image.getWidth(), image.getHeight());
        image.setUrl(base64Data);
        image.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
    }

    public void center() {
        dialogBox.center();
    }

}
