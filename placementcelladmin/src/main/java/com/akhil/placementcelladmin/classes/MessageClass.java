package com.akhil.placementcelladmin.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Battula's on 10/3/2016.
 */
public class MessageClass {

    String name,college,group,email,query;
    String phno;

    public MessageClass(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> drives = new HashMap<>();
        drives.put("name", name);
        drives.put("college", college);
        drives.put("group", group);
        drives.put("phno", phno);
        drives.put("email", email);
        drives.put("query", query);
        return drives;
    }
}
