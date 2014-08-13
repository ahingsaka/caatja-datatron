package com.katspow.datatron.server.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

@Entity
@Cache
public class DatatronImage {

    @Id
    private Long id;

    @Parent
    Key<DatatronApplication> application;

    @Index
    private String name;

    private String imageData;

    private int width;

    private int height;

    public DatatronImage() {
    }

    public DatatronImage(String name, int width, int height, String imageData, Key<DatatronApplication> application) {
        this.name = name;
        this.imageData = imageData;
        this.application = application;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getImageData() {
        return imageData;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Long getParentId() {
        return application.getId();
    }

}
