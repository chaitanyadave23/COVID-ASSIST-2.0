package com.example.covidassist.Model;

public class User {
    private String Handle;
    private String Name;
    private String Phno;
    private String Userid;
    private String Lati;
    private String Longi;

    public String getLati() {
        return Lati;
    }

    public void setLati(String lati) {
        Lati = lati;
    }

    public String getLongi() {
        return Longi;
    }

    public void setLongi(String longi) {
        Longi = longi;
    }

    public String getHandle() {
        return Handle;
    }

    public void setHandle(String handle) {
        Handle = handle;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhno() {
        return Phno;
    }

    public void setPhno(String phno) {
        Phno = phno;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }


}
