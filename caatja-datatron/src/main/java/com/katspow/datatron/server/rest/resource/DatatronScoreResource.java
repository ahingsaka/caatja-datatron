package com.katspow.datatron.server.rest.resource;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.katspow.datatron.server.entity.DatatronApplication;
import com.katspow.datatron.server.entity.DatatronRoot;
import com.katspow.datatron.server.entity.DatatronScore;

public class DatatronScoreResource extends ServerResource {

    @Get
    public Representation represent() {

        String appName = getQuery().getValues("appName");

        LinkedHashMap<String, Integer> responseMap = new LinkedHashMap<String, Integer>();
        JSONArray jsonData = new JSONArray();

        Objectify ofy = ObjectifyService.ofy();
        DatatronApplication app = ofy.load().type(DatatronApplication.class)
                .ancestor(Key.create(DatatronRoot.class, "app")).filter("name", appName).first().now();

        List<DatatronScore> listFound = ObjectifyService.ofy().load().type(DatatronScore.class).ancestor(app)
                .order("numOrder").list();

        for (DatatronScore datatronScore : listFound) {
            responseMap.put(datatronScore.getName(), datatronScore.getScore());
        }

        jsonData.put(responseMap);

        return new JsonRepresentation(jsonData);

    }

}
