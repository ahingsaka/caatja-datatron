package com.katspow.datatron.client.view.applist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.utils.TableResources;
import com.katspow.datatron.client.view.popup.DatatronPopup;
import com.katspow.datatron.shared.ApplicationDto;

public class AppLstView extends Composite {

    interface MyUiBinder extends UiBinder<Widget, AppLstView> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private static final DatatronServiceAsync dataService = GWT.create(DatatronService.class);

    @UiField(provided = true)
    CellTable<ApplicationDto> appLst;

    public AppLstView() {
        appLst = new CellTable<ApplicationDto>(10, (Resources) GWT.create(TableResources.class));
        initWidget(uiBinder.createAndBindUi(this));

        TextColumn<ApplicationDto> nameColumn = new TextColumn<ApplicationDto>() {
            public String getValue(ApplicationDto object) {
                return object.name;
            }

        };

        appLst.addColumn(nameColumn, "Name");

        final SafeHtmlCell selectCell = new SafeHtmlCell() {
            @Override
            public Set<String> getConsumedEvents() {
                HashSet<String> events = new HashSet<String>();
                events.add("click");
                return events;
            }
        };

        Column<ApplicationDto, SafeHtml> selectCol = new Column<ApplicationDto, SafeHtml>(selectCell) {
            public SafeHtml getValue(ApplicationDto value) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<input type='image' src='images/icn_search.png' title='Search' />");
                return sb.toSafeHtml();
            }

            @Override
            public void onBrowserEvent(Context context, Element elem, ApplicationDto object, NativeEvent event) {
                super.onBrowserEvent(context, elem, object, event);
                if ("click".equals(event.getType())) {
                    DatatronPopup datatronPopup = new DatatronPopup("Confirm action", "Application <b>"
                            + object.getName() + "</b> will be selected");
                    datatronPopup.center();
                }
            }

        };

        appLst.addColumn(selectCol, "Select");

        final SafeHtmlCell progressCell = new SafeHtmlCell() {
            @Override
            public Set<String> getConsumedEvents() {
                HashSet<String> events = new HashSet<String>();
                events.add("click");
                return events;
            }
        };

        Column<ApplicationDto, SafeHtml> progressCol = new Column<ApplicationDto, SafeHtml>(progressCell) {
            public SafeHtml getValue(ApplicationDto value) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<input type='image' src='images/icn_trash.png' title='Trash' />");
                return sb.toSafeHtml();
            }

            @Override
            public void onBrowserEvent(Context context, Element elem, ApplicationDto object, NativeEvent event) {
                super.onBrowserEvent(context, elem, object, event);
                if ("click".equals(event.getType())) {
                    DatatronPopup datatronPopup = new DatatronPopup("Confirm action", "Application <b>"
                            + object.getName() + "</b> and all associated resources will be <b>DELETED</b> !");
                    datatronPopup.center();
                }
            }

        };

        appLst.addColumn(progressCol, "Delete");

        dataService.findAllApps(new AsyncCallback<List<ApplicationDto>>() {
            public void onSuccess(List<ApplicationDto> result) {
                appLst.setRowData(result);
            }

            @Override
            public void onFailure(Throwable caught) {
            }
        });

    }

}
