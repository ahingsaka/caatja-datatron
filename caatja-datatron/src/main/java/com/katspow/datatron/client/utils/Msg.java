package com.katspow.datatron.client.utils;

import com.google.gwt.user.client.ui.HTML;

public class Msg {
    
    public static void setInfoMsg(HTML html, String msg) {
        html.setHTML("<h4 class='alert_info'>" + msg + "</h4>");
    }
    
    public static void setErrorMsg(HTML html, String msg) {
        html.setHTML("<h4 class='alert_error'>" + msg + "</h4>");
    }
    
}
