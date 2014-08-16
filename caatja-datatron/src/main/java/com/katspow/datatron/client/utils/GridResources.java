package com.katspow.datatron.client.utils;

import com.google.gwt.user.cellview.client.DataGrid;

public interface GridResources extends DataGrid.Resources {
    
    interface GridStyle extends DataGrid.Style {
        
    }
    
    @Source({ "GridResources.css" })
    GridStyle dataGridStyle();

}
