package com.katspow.datatron.client.view.imgList;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.shared.ImageDto;

public class ImgLstView extends Composite {

    private static ImgLstViewUiBinder uiBinder = GWT.create(ImgLstViewUiBinder.class);

    interface ImgLstViewUiBinder extends UiBinder<Widget, ImgLstView> {
    }

    private static final DatatronServiceAsync dataService = GWT.create(DatatronService.class);

    @UiField(provided = true)
    CellList<ImageDto> imgLst;

    static class ImageCell extends AbstractCell<ImageDto> {

        @Override
        public void render(com.google.gwt.cell.client.Cell.Context context, ImageDto value, SafeHtmlBuilder sb) {
            if (value == null) {
                return;
            }

            sb.appendHtmlConstant("<table>");

            // Add the contact image.
            sb.appendHtmlConstant("<tr><td rowspan='3'>");
            // sb.appendHtmlConstant(imageHtml);
            sb.appendHtmlConstant("</td>");

            // Add the name and address.
            sb.appendHtmlConstant("<td style='font-size:95%;'>");
            sb.appendEscaped("Test");
            // sb.appendHtmlConstant("</td></tr><tr><td>");
            // sb.appendEscaped(value.getAddress());
            // sb.appendHtmlConstant("</td></tr></table>");
        }
    }

    public ImgLstView() {
        ImageCell imageCell = new ImageCell();
        imgLst = new CellList<ImageDto>(imageCell);
        initWidget(uiBinder.createAndBindUi(this));

        dataService.findAllResources(Datatron.getSelectedApplication().getId(), new AsyncCallback<List<ImageDto>>() {
            @Override
            public void onSuccess(List<ImageDto> result) {
                imgLst.setRowData(result);
            }

            @Override
            public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
            }
        });

    }

}
