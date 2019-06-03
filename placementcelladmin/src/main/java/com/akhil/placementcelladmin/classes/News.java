package com.akhil.placementcelladmin.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Battula's on 10/26/2016.
 */
public class News {

    private String title, content,image,date;

    public News() {

    }

    public News(String image, String title, String content, String date) {

         this.image = image;
        this.title = title;
        this.content = content;
        this.date = date;

    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

     public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> drives = new HashMap<>();
        drives.put("title", title);
        drives.put("content", content);
        drives.put("image", content);

        return drives;
    }
}
