package com.example.covidassist.Model;

public class feed {
    private String item_name;
    private String item_quantity;
    private String item_desc;
    private String user_id;

    public feed() {
    }

    public feed(String item_name, String item_quantity, String item_desc, String user_id) {
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_desc = item_desc;
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(String item_quantity) {
        this.item_quantity = item_quantity;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }
}
