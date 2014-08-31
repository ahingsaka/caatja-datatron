package com.katspow.datatron.server.rest.resource;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.restlet.ext.json.JsonRepresentation;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class DatatronImageResource extends ServerResource {

    @Get
    public Representation represent() {

        String appName = getQuery().getValues("appName");

        Map<String, String> valuesMap = getQuery().getValuesMap();
        Map<String, String> responseMap = new HashMap<String, String>();
        JSONArray jsonData = new JSONArray();

        Objectify ofy = ObjectifyService.ofy();

        // String pictureName = getQuery().getValues("pictureName");
        // GruiResource resource =
        // ofy.load().type(GruiResource.class).ancestor(app).filter("name",
        // pictureName).first().now();
        // return resource.getImageData();

        jsonData.put(responseMap);

        return new JsonRepresentation(jsonData);
    }

}
