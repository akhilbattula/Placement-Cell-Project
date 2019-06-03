package com.akhil.placementcelladmin.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Battula's on 9/26/2016.
 */
public class Recent {

    private String title;
    private String desc;
    private String dod;
    private String reglink;
    private String fulldetais;

    public Recent(){

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

    public Map<String, Object> toMap() {
        HashMap<String, Object> recdrives = new HashMap<>();
        recdrives.put("title", title);
        recdrives.put("desc", desc);
        recdrives.put("dod", dod);
        recdrives.put("reglink", reglink);
        recdrives.put("fulldetails", fulldetais);
        return recdrives;
    }
}

