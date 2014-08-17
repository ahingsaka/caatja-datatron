package com.katspow.datatron.client.utils;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.cell.client.SafeHtmlCell;

public class ClickSafeHtmlCell extends SafeHtmlCell {

    @Override
    public Set<String> getConsumedEvents() {
        HashSet<String> events = new HashSet<String>();
        events.add("click");
        return events;
    }
    
}
