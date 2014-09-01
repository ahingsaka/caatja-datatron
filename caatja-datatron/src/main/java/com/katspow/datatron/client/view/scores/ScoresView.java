package com.katspow.datatron.client.view.scores;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ScoresView extends Composite {

    private static ScoresViewUiBinder uiBinder = GWT.create(ScoresViewUiBinder.class);

    interface ScoresViewUiBinder extends UiBinder<Widget, ScoresView> {
    }
    
    @UiField
    HTML message;
    
    @UiField(provided = true)
    SimplePager pager;
    
    @UiField
    DockLayoutPanel dock;

    public ScoresView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
