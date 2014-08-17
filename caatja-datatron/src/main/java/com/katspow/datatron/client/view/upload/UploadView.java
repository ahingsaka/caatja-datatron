package com.katspow.datatron.client.view.upload;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.MultiUploader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.katspow.datatron.client.Datatron;

public class UploadView extends Composite {

    private static UploadViewUiBinder uiBinder = GWT.create(UploadViewUiBinder.class);

    interface UploadViewUiBinder extends UiBinder<Widget, UploadView> {
    }

    @UiField
    DialogBox dialogBox;

    @UiField
    HTML errorMsg;

    @UiField
    SubmitButton uploadBtn;

    @UiField
    SubmitButton cancelBtn;
    
    @UiField(provided = true)
    MultiUploader uploader;
    
    @UiField
    FormPanel form;

    public UploadView() {
        
        uploader = new MultiUploader();
        uploader.setAutoSubmit(false);
        uploader.setValidExtensions(".jpg", ".png", ".gif");
        uploader.addOnFinishUploadHandler(onFinishUploaderHandler);
        uploader.reset();
        
        initWidget(uiBinder.createAndBindUi(this));
        
        form.setAction("/imgupload");
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_POST);

        dialogBox.setGlassEnabled(true);
        dialogBox.setModal(true);
        dialogBox.setAnimationEnabled(true);

        cancelBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });

        uploadBtn.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                uploader.setServletPath(uploader.getServletPath() + "?app=" + Datatron.getSelectedApplication().getId());
                uploader.submit();
            }
        });
        
        // Add an event handler to the form.
        form.addSubmitHandler(new FormPanel.SubmitHandler() {
            public void onSubmit(SubmitEvent event) {
                
            }
        });

        form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            public void onSubmitComplete(SubmitCompleteEvent event) {
                // When the form submission is successfully completed, this
                // event is
                // fired. Assuming the service returned a response of type
                // text/html,
                // we can get the result text here (see the FormPanel
                // documentation for
                // further explanation).
                Window.alert(event.getResults());
            }
        });
        
    }
    
    private OnFinishUploaderHandler onFinishUploaderHandler = new OnFinishUploaderHandler() {
        @Override
        public void onFinish(IUploader uploader) {
            
            if (uploader.getStatus() == Status.SUCCESS) {

//                new PreloadedImage(uploader.fileUrl(), showImage);
                
                // The server sends useful information to the client by default
                UploadedInfo info = uploader.getServerInfo();
//                System.out.println("File name " + info.name);
//                System.out.println("File content-type " + info.ctype);
//                System.out.println("File size " + info.size);
                
                Datatron.showImgs();
                
//                Long appId = Long.valueOf(applstBox.getValue(applstBox.getSelectedIndex()));
//
//                gruiService.findAllResources( appId, new AsyncCallback<List<ResourceDto>>() {
//                    @Override
//                    public void onSuccess(List<ResourceDto> result) {
//                        resources = result;
//                        imgListBox.clear();
//                        for (ResourceDto resourceDto : result) {
//                            imgListBox.addItem(resourceDto.getName(), String.valueOf(resourceDto.getId()));
//                        }
//                    }
//                    
//                    @Override
//                    public void onFailure(Throwable caught) {
//                        // TODO Auto-generated method stub
//                    }
//                });

             }
            
        }
    };

    public void center() {
        dialogBox.center();
    }

}
