package com.katspow.datatron.client.utils;

import com.google.gwt.user.cellview.client.CellTable;

public interface TableResources extends CellTable.Resources {

    /**
       * The styles applied to the table.
       */
    interface TableStyle extends CellTable.Style {
    }

    @Source({ "TableResources.css" })
    TableStyle cellTableStyle();

}
