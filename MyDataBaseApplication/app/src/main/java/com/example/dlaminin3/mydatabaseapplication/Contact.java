package com.example.dlaminin3.mydatabaseapplication;

public class Contact
{

    int id;
    String name;
    String phone;
    public Contact()
    {

    }
    public Contact(int id,String name, String phoneNumber)
    {

        this.name =name;
        this.id= id;
        this.phone= phoneNumber;
    }
    public Contact(String name, String phone_number){
        this.name = name;
        this.phone = phone_number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
