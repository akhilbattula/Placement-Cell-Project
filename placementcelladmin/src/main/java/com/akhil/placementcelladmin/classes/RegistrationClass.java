package com.akhil.placementcelladmin.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Battula's on 9/11/2016.
 */
public class RegistrationClass {

    int rollno,yoj,Dmarks,backlogs,Tmarks,Imarks,Tyop,Iyop,IGroup,phno;
    String name,address,email,dob,program,group;


    public RegistrationClass(){

    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public int getRollno() {
        return rollno;
    }

    public void setYoj(int yoj) {
        this.yoj = yoj;
    }

    public int getYoj() {
        return yoj;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDob() {
        return dob;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getDmarks() {
        return Dmarks;
    }

    public void setDmarks(int dmarks) {
        Dmarks = dmarks;
    }

    public int getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(int backlogs) {
        this.backlogs = backlogs;
    }

    public int getTmarks() {
        return Tmarks;
    }

    public void setTmarks(int tmarks) {
        Tmarks = tmarks;
    }

    public int getIGroup() {
        return IGroup;
    }

    public void setIGroup(int IGroup) {
        this.IGroup = IGroup;
    }

    public int getImarks() {
        return Imarks;
    }

    public void setImarks(int imarks) {
        Imarks = imarks;
    }

    public int getIyop() {
        return Iyop;
    }

    public void setIyop(int iyop) {
        Iyop = iyop;
    }

    public int getPhno() {
        return phno;
    }

    public void setPhno(int phno) {
        this.phno = phno;
    }

    public int getTyop() {
        return Tyop;
    }

    public void setTyop(int tyop) {
        Tyop = tyop;
    }



    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Rollno", rollno);
        result.put("Name", name);
        result.put("DOB", dob);
        result.put("Email", email);
        result.put("Ph no", phno);
        result.put("Address", address);
        result.put("Program", program);
        result.put("Group", group);
        result.put("YOJ", yoj);
        result.put("Degree Marks", Dmarks);
        result.put("Backlogs", backlogs);
        result.put("Tenth Marks", Tmarks);
        result.put("Tenth YOP", Tyop);
        result.put("Inter Marks", Imarks);
        result.put("Inter YOP", Iyop);
        result.put("Inter Group", IGroup);

        return result;
    }

}
