package com.katspow.datatron.client.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.view.popup.DatatronPopup;

public abstract class SimpleLoadingCallback<T> implements AsyncCallback<T> {
    
    public abstract void onOk(T result);

    @Override
    public void onSuccess(T result) {
        onOk(result);
        Datatron.stopLoading();
    }

    @Override
    public void onFailure(Throwable caught) {
        Datatron.stopLoading();
        DatatronPopup datatronPopup = new DatatronPopup("Error : " + caught.getMessage());
        datatronPopup.center();
    }

}
