package com.example.inclass07;

import java.io.Serializable;

public class Contact implements Serializable {
        String id, name, email, phone, type;

    public Contact(String id, String name, String email, String phone, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
    }

}
