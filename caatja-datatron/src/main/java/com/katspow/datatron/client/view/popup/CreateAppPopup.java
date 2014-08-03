package com.katspow.datatron.client.view.popup;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;

public class CreateAppPopup extends PopupPanel {
    
    public CreateAppPopup() {
        super(true);
        setGlassEnabled(true);
        setWidget(new Label("Click outside to close"));
    }

}
