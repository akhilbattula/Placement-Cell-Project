package com.akhil.placementcell.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Battula's on 9/26/2016.
 */
public class Upcoming {

    private String title;
    private String desc;
    private String dod;
    private String reglink;
    private String fulldetais;
    private String place;

    public Upcoming(){

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getDod() {
        return dod;
    }

    public void setReglink(String reglink) {
        this.reglink = reglink;
    }

    public String getReglink() {
        return reglink;
    }

    public void setFulldetais(String fulldetais) {
        this.fulldetais = fulldetais;
    }

    public String getFulldetais() {
        return fulldetais;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> drives = new HashMap<>();
        drives.put("title", title);
        drives.put("desc", desc);
        drives.put("dod", dod);
        drives.put("place", place);
        drives.put("reglink", reglink);
        drives.put("fulldetails", fulldetais);
        return drives;
    }
}
