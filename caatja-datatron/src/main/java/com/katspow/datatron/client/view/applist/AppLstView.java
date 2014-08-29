package com.katspow.datatron.client.view.applist;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.DataGrid.Resources;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.katspow.datatron.client.Datatron;
import com.katspow.datatron.client.api.DatatronService;
import com.katspow.datatron.client.api.DatatronServiceAsync;
import com.katspow.datatron.client.utils.GridResources;
import com.katspow.datatron.client.utils.Msg;
import com.katspow.datatron.client.view.popup.DatatronPopup;
import com.katspow.datatron.client.view.popup.PopupCallback;
import com.katspow.datatron.shared.ApplicationDto;

public class AppLstView extends Composite {

    interface MyUiBinder extends UiBinder<Widget, AppLstView> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    private static final DatatronServiceAsync dataService = GWT.create(DatatronService.class);
    
    @UiField
    HTML message;

    @UiField(provided = true)
    DataGrid<ApplicationDto> appLst;
    
    @UiField(provided = true)
    SimplePager pager;
    
    @UiField
    DockLayoutPanel dock;
    
    private ListDataProvider<ApplicationDto> dataProvider = new ListDataProvider<ApplicationDto>();

    public AppLstView() {
        appLst = new DataGrid<ApplicationDto>(10, (Resources) GWT.create(GridResources.class));
        appLst.setAutoHeaderRefreshDisabled(true);
        appLst.setEmptyTableWidget(new Label("No item"));
        
        // Attach a column sort handler to the ListDataProvider to sort
        // the
        // list.
        ListHandler<ApplicationDto> sortHandler = new ListHandler<ApplicationDto>(dataProvider.getList());
        appLst.addColumnSortHandler(sortHandler);
        
     // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(appLst);
        
        initTableColumns(sortHandler);
        
        dataProvider.addDataDisplay(appLst);
        
        initWidget(uiBinder.createAndBindUi(this));
        
        dock.setHeight("500px");
        dock.setWidth("100%");


        dataService.findAllApps(new AsyncCallback<List<ApplicationDto>>() {
            public void onSuccess(List<ApplicationDto> result) {
//                appLst.setRowData(result);
                dataProvider.setList(result);
            }

            @Override
            public void onFailure(Throwable caught) {
            }
        });

    }
    
    private void initTableColumns(ListHandler<ApplicationDto> sortHandler) {
        
        TextColumn<ApplicationDto> nameColumn = new TextColumn<ApplicationDto>() {
            public String getValue(ApplicationDto object) {
                return object.getName();
            }

        };

        appLst.addColumn(nameColumn, "Name");
        
        Column<ApplicationDto, String> pwdColumn = new Column<ApplicationDto, String>(new EditTextCell()) {
            @Override
            public String getValue(ApplicationDto object) {
                String pwd = object.getPassword();
                return pwd == null ? "" : pwd;
            }
        };
        
        appLst.addColumn(pwdColumn, "Password");

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
            public void onBrowserEvent(Context context, Element elem, final ApplicationDto object, NativeEvent event) {
                super.onBrowserEvent(context, elem, object, event);
                if ("click".equals(event.getType())) {
                    final DatatronPopup datatronPopup = new DatatronPopup("Confirm action", "Application <b>"
                            + object.getName() + "</b> will be selected");
                    
                    datatronPopup.setCallback(new PopupCallback() {
                        public void onOk() {
                            Datatron.setSelectedApplication(object);
                            datatronPopup.hide();
                        }

                    });
                    
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
                    
                    datatronPopup.setCallback(new PopupCallback() {
                        public void onOk() {
                            // TODO Auto-generated method stub
                        }
                    });
                    
                    datatronPopup.center();
                }
            }

        };

        appLst.addColumn(progressCol, "Delete");
        
    }

    public void setSelectedApplication(ApplicationDto object) {
        Msg.setInfoMsg(message, "Application <b>" + object.getName() + "</b> is now selected");
    }

}
