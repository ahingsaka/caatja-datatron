package com.katspow.datatron.server.rest.resource;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.katspow.datatron.server.entity.DatatronApplication;
import com.katspow.datatron.server.entity.DatatronImage;
import com.katspow.datatron.server.entity.DatatronRoot;

public class DatatronImageResource extends ServerResource {

    @Get
    public Representation represent() {

        String appName = getQuery().getValues("appName");

        Map<String, String> valuesMap = getQuery().getValuesMap();
        Map<String, String> responseMap = new HashMap<String, String>();
        JSONArray jsonData = new JSONArray();

        Objectify ofy = ObjectifyService.ofy();
        DatatronApplication app = ofy.load().type(DatatronApplication.class).ancestor(Key.create(DatatronRoot.class, "app"))
                .filter("name", appName).first().now();

        for (Map.Entry<String, String> entry : valuesMap.entrySet()) {
            String key = entry.getKey();
            if (!key.contains("appName")) {
                String value = entry.getValue();
                DatatronImage resource = ofy.load().type(DatatronImage.class).ancestor(app).filter("name", value).first()
                        .now();

                if (resource != null) {
                    responseMap.put(key, resource.getImageData());
                }
            }
        }

        // String pictureName = getQuery().getValues("pictureName");
        // GruiResource resource =
        // ofy.load().type(GruiResource.class).ancestor(app).filter("name",
        // pictureName).first().now();
        // return resource.getImageData();

        jsonData.put(responseMap);

        return new JsonRepresentation(jsonData);
    }

}
