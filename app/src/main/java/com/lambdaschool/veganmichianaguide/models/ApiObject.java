package com.lambdaschool.veganmichianaguide.models;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class ApiObject<T> implements Serializable {
    public static final String API_ITEM_INTENT_TAG = "api_item";
    protected String name, category, content;
    protected ArrayList<T> arrayList;
    protected long id;



    protected void parseUrlForId(String url) {
        // "https://localhost:2019/restaurants/"
        final String[] urlComponents = url.split("/");
        if(urlComponents.length >= 3) {
//            setImageId(Integer.parseInt(urlComponents[5]));
        }
        if(urlComponents.length >= 2) {
            this.category = urlComponents[3];
//            setDrawableResourceId(DrawableResolver.getDrawableId(this.category, this.imageId));
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//    public int getImageId() {
//        return imageId;
//    }
//
//    public void setImageId(int imageId) {
//        this.imageId = imageId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
