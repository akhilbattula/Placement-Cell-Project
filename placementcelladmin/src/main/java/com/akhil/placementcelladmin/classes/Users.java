package com.akhil.placementcelladmin.classes;

/**
 * Created by Battula on 3/8/2017.
 */

public class Users {

    private String Email,Phno,Name,Group,Year,College,Rollno,City;


    public Users(){

    }
    public Users(String Email,String Phno, String Name, String Group, String Year, String College, String Rollno,String City){

        this.Email=Email;
        this.Rollno=Rollno;
        this.Phno=Phno;
        this.Name=Name;
        this.Group=Group;
        this.Year=Year;
        this.College=College;
        this.City=City;

    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhno() {
        return Phno;
    }

    public void setPhno(String phno) {
        this.Phno = phno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        this.Group = group;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        this.College = college;
    }

    public String getRollno() {
        return Rollno;
    }

    public void setRollno(String rollno) {
        this.Rollno = rollno;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        this.City = city;
    }
}
