package com.katspow.datatron.server.rest.test;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

public class DatatronRestClient {

    public static void main(String[] args) {

        ClientResource resource = new ClientResource("http://localhost:8888/caatjaServlet");
        resource.addQueryParameter("appName", "arkaanz");
        resource.addQueryParameter("pictureName", "lvl.png");

        ChallengeResponse authentication = new ChallengeResponse(ChallengeScheme.HTTP_BASIC, "arkaanz", "arkaanz123");
        resource.setChallengeResponse(authentication);

        try {
            // Send the HTTP GET request
            resource.get();
            // Output the response entity on the JVM console
            resource.getResponseEntity().write(System.out);
        } catch (Exception e) {
            if (resource.getStatus().equals(Status.CLIENT_ERROR_UNAUTHORIZED)) {
                // Unauthorized access
                System.out.println("Access unauthorized by the server, check your credentials");
            } else {
                // Unexpected status
                System.out.println("An unexpected status was returned: " + resource.getStatus());
            }
        }

    }

}
