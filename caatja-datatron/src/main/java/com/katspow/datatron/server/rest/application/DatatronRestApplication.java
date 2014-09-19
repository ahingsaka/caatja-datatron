package com.katspow.datatron.server.rest.application;

import java.util.List;

import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.katspow.datatron.server.entity.DatatronApplication;
import com.katspow.datatron.server.entity.DatatronRoot;
import com.katspow.datatron.server.rest.resource.DatatronImageResource;
import com.katspow.datatron.server.rest.resource.DatatronScoreResource;

/**
 * Cross site request
 * http://restlet-discuss.1400322.n2.nabble.com/Cross-Domain-JQUERY
 * -POST-Attempt-td6222104.html
 * 
 * @author Ahingsaka
 *
 */
public class DatatronRestApplication extends Application {

    static {
        ObjectifyService.register(DatatronApplication.class);
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        
        // router.attachDefault(DatatronImageResource.class);
        router.attach("/img", DatatronImageResource.class);
        router.attach("/score", DatatronScoreResource.class);

        ChallengeAuthenticator challengeAuthenticator = new ChallengeAuthenticator(getContext(),
                ChallengeScheme.HTTP_BASIC, "myRealm") {
            @Override
            protected boolean authenticate(Request request, Response response) {
                if (request.getChallengeResponse() == null) {
                    return false;
                } else {
                    return super.authenticate(request, response);
                }
            }
        };

        OriginFilter originFilter = new OriginFilter(getContext());
        // originFilter.setNext(challengeAuthenticator);

        List<DatatronApplication> listFound = ObjectifyService.ofy().load().type(DatatronApplication.class)
                .ancestor(Key.create(DatatronRoot.class, "app")).list();

        if (!listFound.isEmpty()) {

            MapVerifier verifier = new MapVerifier();

            for (DatatronApplication datatronApplication : listFound) {
                String appName = datatronApplication.getName();
                String password = datatronApplication.getPassword();

                verifier.getLocalSecrets().put(appName, password.toCharArray());
                challengeAuthenticator.setVerifier(verifier);
            }

            // challengeAuthenticator.setNext(router);

            originFilter.setNext(challengeAuthenticator);
            challengeAuthenticator.setNext(router);

        }

        return originFilter;
        // return challengeAuthenticator;
    }

}
