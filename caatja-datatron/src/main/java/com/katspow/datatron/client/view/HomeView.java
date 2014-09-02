package com.katspow.datatron.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.utils.Msg;
import com.katspow.datatron.client.view.createapp.CreateAppPopup;
import com.katspow.datatron.shared.ApplicationDto;

public class HomeView extends Composite {

    private static final String NO_APPLICATION_SELECTED = "[ No application selected ]";

    interface MyUiBinder extends UiBinder<Widget, HomeView> {
    }

    private static final String WELCOME = "Welcome to the DATATRON ! Please select an action on the left menu.";

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
    @UiField
    HTML selectedApplication;

    @UiField
    HTML menuTitle;

    @UiField
    HTML applications;

    @UiField
    HTML images;

    @UiField
    HTML scores;

    @UiField
    HTML data;

    @UiField
    HTML other;

    @UiField
    Anchor listApp;

    @UiField
    Anchor newApp;

    @UiField
    Anchor listImg;

    @UiField
    Anchor uploadImg;

    @UiField
    Anchor listScores;
    
    @UiField
    Anchor newScore;

    @UiField
    Anchor listData;

    @UiField
    Anchor changeDatatronPwd;

    @UiField
    static HTMLPanel mainPanel;

    @UiField
    HTML infoMsg;

    public HomeView() {
        initWidget(uiBinder.createAndBindUi(this));
        
        setMenuLinkMsg(applications, "Applications");
        setMenuLinkMsg(images, "Images");
        setMenuLinkMsg(scores, "Scores");
        setMenuLinkMsg(data, "Data");
        setMenuLinkMsg(other, "Other");

        Msg.setInfoMsg(infoMsg, WELCOME);

        listApp.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showApps();
            }
        });

        newApp.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                showCreateAppPopup();
            }
        });

        listImg.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showImgs();
            }
        });

        uploadImg.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showUpload();
            }
        });

        listScores.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showScores();
            }
        });
        
        newScore.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showCreateScore();
            }
        });

        listData.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showData();
            }
        });

        changeDatatronPwd.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Datatron.showPasswordView();
            }
        });

        setHomeTitle("[ No application selected ]");
        setMenuTitle("[ No application selected ]");
    }

    public void setViewInMain(Widget w) {
        mainPanel.clear();
        mainPanel.add(w);
    }

    protected void showCreateAppPopup() {
        CreateAppPopup createAppPopup = new CreateAppPopup();
        createAppPopup.center();
    }

    public void setInfoMsg(String msg) {
        infoMsg.setHTML("<h4 class='alert_info'>" + msg + "</h4>");
    }

    public void setMenuLinkMsg(HTML html, String msg) {
        html.setHTML("<h3>" + msg + "</h3>");
    }

    private void setHomeTitle(String title) {
        selectedApplication.setHTML("<h2 class='section_title'>" + title + "</h2>");
    }

    private void setMenuTitle(String title) {
        menuTitle.setHTML("<p>" + title + "</p>");
    }

    public void setSelectedApplication(ApplicationDto object) {
        if (object == null) {
            setHomeTitle(NO_APPLICATION_SELECTED);
            setMenuTitle(NO_APPLICATION_SELECTED);
        } else {
            setHomeTitle("Application selected : " + object.getName());
            setMenuTitle(object.getName());
        }
    }

    public void displayMenuLinks(boolean visible) {
        applications.setVisible(visible);
        images.setVisible(visible);
        scores.setVisible(visible);
        data.setVisible(visible);
        other.setVisible(visible);
        listApp.setVisible(visible);
        newApp.setVisible(visible);
        listImg.setVisible(visible);
        uploadImg.setVisible(visible);
        listScores.setVisible(visible);
        listData.setVisible(visible);
        changeDatatronPwd.setVisible(visible);
    }
    
}
