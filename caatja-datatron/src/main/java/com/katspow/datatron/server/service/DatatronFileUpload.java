package com.katspow.datatron.server.service;

import gwtupload.server.exceptions.UploadActionException;
import gwtupload.server.gae.AppEngineUploadAction;

import java.util.Collections;
import java.util.List;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.FileItem;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.katspow.datatron.server.entity.DatatronApplication;
import com.katspow.datatron.server.entity.DatatronImage;

public class DatatronFileUpload extends AppEngineUploadAction {

    private static final long serialVersionUID = 4560838196174329470L;

    @Override
    public String executeAction(HttpServletRequest request, List<FileItem> sessionFiles) throws UploadActionException {
        String appValue = request.getParameter("app");

        if (appValue == null || appValue.isEmpty()) {
            throw new UploadActionException("No app value !");
        }

        Objectify ofy = ObjectifyService.ofy();
        Key<DatatronApplication> gruiApplication = ofy.load().type(DatatronApplication.class)
                .ancestor(KeyFactory.createKey("RootApp", "app")).keys().first().now();

        if (gruiApplication == null) {
            throw new UploadActionException("No app found !");
        }

        String response = "";
        for (FileItem item : sessionFiles) {
            if (false == item.isFormField()) {
                try {

                    byte[] bs = item.get();

                    // Conversion en base 64 des fichiers
                    // ISSUE with GWTUPLOAD
                    // THIS IS A HACK !
                    Cache cache = CacheManager.getInstance().getCacheFactory().createCache(Collections.emptyMap());
                    byte[] object = (byte[]) cache.get(item.getFieldName().substring(0,
                            item.getFieldName().length() - 2));

                    Image makeImage = ImagesServiceFactory.makeImage(object);
                    int height = makeImage.getHeight();
                    int width = makeImage.getWidth();

                    String base64Value = DatatypeConverter.printBase64Binary(object);

                    // / Create a new file based on the remote file name in the
                    // client
                    String saveName = item.getName().replaceAll("[\\\\/><\\|\\s\"'{}()\\[\\]]+", "_");

                    DatatronImage datatronImg = new DatatronImage(saveName, width, height, base64Value, gruiApplication);
                    ofy.save().entity(datatronImg);

                } catch (Exception e) {
                    throw new UploadActionException(e);
                }
            }
        }

        // / Remove files from session because we have a copy of them
        removeSessionFileItems(request);

        // / Send your customized message to the client.
        return response;
    }

}
