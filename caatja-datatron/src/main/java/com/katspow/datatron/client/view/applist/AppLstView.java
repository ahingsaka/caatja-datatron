package com.katspow.datatron.client.view.applist;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTable.Resources;
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
        
        List<ApplicationDto> values = new ArrayList<ApplicationDto>();
        ApplicationDto value1 = new ApplicationDto("lorem ipsum");
        values.add(value1);
        appLst.setRowData(values);
    }

}
