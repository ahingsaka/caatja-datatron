package com.katspow.datatron.client.view.applist;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.katspow.datatron.client.utils.TableResources;
import com.katspow.datatron.shared.ApplicationDto;

public class AppLstView extends Composite {
    
    interface MyUiBinder extends UiBinder<Widget, AppLstView> {
    }
    
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
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
        
        final SafeHtmlCell selectCell = new SafeHtmlCell();

        Column<ApplicationDto, SafeHtml> selectCol = new Column<ApplicationDto, SafeHtml>(
                selectCell) {
            public SafeHtml getValue(ApplicationDto value) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<input type='image' src='images/icn_search.png' title='Search' />");
                return sb.toSafeHtml();
            }
        };
        
        appLst.addColumn(selectCol, "Select");
        
        final SafeHtmlCell progressCell = new SafeHtmlCell();

        Column<ApplicationDto, SafeHtml> progressCol = new Column<ApplicationDto, SafeHtml>(
                progressCell) {
            public SafeHtml getValue(ApplicationDto value) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<input type='image' src='images/icn_trash.png' title='Trash' />");
                return sb.toSafeHtml();
            }
        };
        
        appLst.addColumn(progressCol, "Delete");
        
        List<ApplicationDto> values = new ArrayList<ApplicationDto>();
        ApplicationDto value1 = new ApplicationDto("lorem ipsum");
        ApplicationDto value2 = new ApplicationDto("lorem ipsum again ceded");
        values.add(value1);
        values.add(value2);
        appLst.setRowData(values);
    }

}
