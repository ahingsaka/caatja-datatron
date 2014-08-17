package com.katspow.datatron.client.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.katspow.datatron.client.view.popup.DatatronPopup;

public abstract class SimpleCallback<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(Throwable caught) {
        DatatronPopup datatronPopup = new DatatronPopup("Error : " + caught.getMessage());
        datatronPopup.center();
    }

}
