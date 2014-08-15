package com.katspow.datatron.client.view.scores;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ScoresView extends Composite {

    private static ScoresViewUiBinder uiBinder = GWT.create(ScoresViewUiBinder.class);

    interface ScoresViewUiBinder extends UiBinder<Widget, ScoresView> {
    }

    public ScoresView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

}
