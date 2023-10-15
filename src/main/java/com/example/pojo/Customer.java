package com.example.pojo;

import java.sql.Date;
import java.time.LocalDate;

public class Customer {
    private Long id;
    private String name;
    private String phone;
    private Boolean admin;
    private java.time.LocalDate birthday;
    private Date registerDate;
    private Address address;
    private int age;

    public Customer(Long id, String name, String phone, Boolean admin, LocalDate birthday, Date registerDate,
            Address address, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.admin = admin;
        this.birthday = birthday;
        this.registerDate = registerDate;
        this.address = address;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public java.time.LocalDate getBirthday() {
        return birthday;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
