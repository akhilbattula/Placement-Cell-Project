package com.akhil.placementcell.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Battula's on 10/26/2016.
 */
public class Activities {

    private String title,content,image,date,date_post;
    private int sno;
    public Activities() {

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

    public String getDate_post() {
        return date_post;
    }

    public void setDate_post(String date_post) {
        this.date_post = date_post;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
