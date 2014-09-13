package com.katspow.datatron.client.view.createscore;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.SimpleLoadingCallback;
import com.katspow.datatron.client.utils.Msg;

public class CreateScorePopup extends Composite {

    private static CreateScorePopupUiBinder uiBinder = GWT.create(CreateScorePopupUiBinder.class);

    interface CreateScorePopupUiBinder extends UiBinder<Widget, CreateScorePopup> {
    }

    @UiField
    HTMLPanel container;

    @UiField
    DialogBox dialogBox;

    @UiField
    TextBox nameFld;

    @UiField
    TextBox scoreFld;

    @UiField
    HTML errorMsg;

    @UiField
    SubmitButton okBtn;

    @UiField
    SubmitButton cancelBtn;

    public CreateScorePopup() {
        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);

        cancelBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        okBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                callService();
            }
        });
    }

    protected void callService() {
        String text = nameFld.getText();

        if (text.isEmpty()) {
            Msg.setErrorMsg(errorMsg, "Please enter a name");
            return;
        }

        String score = scoreFld.getText();

        if (score.isEmpty()) {
            Msg.setErrorMsg(errorMsg, "Please enter a score");
            return;
        }

        Datatron.dataService.createScore(Datatron.getSelectedApplication().getId(), text, Integer.parseInt(score),
                new SimpleLoadingCallback<Boolean>() {
                    public void onOk(Boolean result) {
                        if (result) {
                            Datatron.showScores();
                            dialogBox.hide();
                        } else {
                            Msg.setErrorMsg(errorMsg, "Max nb of scores reached");
                        }
                    }
                });

    }

    public void center() {
        dialogBox.center();
    }

}
