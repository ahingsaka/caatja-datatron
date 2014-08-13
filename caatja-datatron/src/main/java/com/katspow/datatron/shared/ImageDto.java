package com.katspow.datatron.shared;

import java.io.Serializable;

public class ImageDto implements Serializable {

    private static final long serialVersionUID = -4001369304426534002L;

    private Long id;

    private Long parentId;

    private String name;

    private int height;

    private int width;

    private String imageData;

    public ImageDto() {
    }

    public ImageDto(Long id, Long parentId, String name, String imageData, int width, int height) {
        super();
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.imageData = imageData;
        this.width = width;
        this.height = height;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
