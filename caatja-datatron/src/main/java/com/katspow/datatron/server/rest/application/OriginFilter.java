package com.katspow.datatron.server.rest.application;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.Method;
import org.restlet.engine.header.Header;
import org.restlet.routing.Filter;
import org.restlet.util.Series;

public class OriginFilter extends Filter {

    public OriginFilter(Context context) {
        super(context);
    }

    @Override
    protected int beforeHandle(Request request, Response response) {
        if (Method.OPTIONS.equals(request.getMethod())) {
            // Form requestHeaders = (Form)
            // request.getAttributes().get("org.restlet.http.headers");

            Series requestHeaders = (Series) request.getAttributes().get("org.restlet.http.headers");

            request.getChallengeResponse();

            String origin = requestHeaders.getFirstValue("Origin", true);

            // if(MyConfig.getAllowedOrigins().contains(origin)) {
            Series responseHeaders = (Series) response.getAttributes().get("org.restlet.http.headers");
            if (responseHeaders == null) {
                responseHeaders = new Series(Header.class);
                response.getAttributes().put("org.restlet.http.headers", responseHeaders);
            }
            responseHeaders.add("Access-Control-Allow-Origin", origin);
            responseHeaders.add("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
            responseHeaders.add("Access-Control-Allow-Headers", "Authorization");
            responseHeaders.add("Access-Control-Allow-Credentials", "true");
            responseHeaders.add("Access-Control-Max-Age", "60");
            responseHeaders.add("WWW-Authenticate", "'Secure Area'");

            // response.setEntity(new EmptyRepresentation());

            // response.setChallengeRequests(challengeRequests);

            return CONTINUE;
            // }
        }

        return super.beforeHandle(request, response);
    }

    @Override
    protected void afterHandle(Request request, Response response) {
        if (!Method.OPTIONS.equals(request.getMethod())) {
            Series requestHeaders = (Series) request.getAttributes().get("org.restlet.http.headers");
            String origin = requestHeaders.getFirstValue("Origin", true);

            // if(MyConfig.getAllowedOrigins().contains(origin)) {
            Series responseHeaders = (Series) response.getAttributes().get("org.restlet.http.headers");
            if (responseHeaders == null) {
                responseHeaders = new Series(Header.class);
                response.getAttributes().put("org.restlet.http.headers", responseHeaders);
            }
            responseHeaders.add("Access-Control-Allow-Origin", origin);
            responseHeaders.add("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
            responseHeaders.add("Access-Control-Allow-Headers", "Authorization");
            responseHeaders.add("Access-Control-Allow-Credentials", "true");
            responseHeaders.add("Access-Control-Max-Age", "60");
            responseHeaders.add("WWW-Authenticate", "'Secure Area'");
            // }
        }
        super.afterHandle(request, response);
    }

}
