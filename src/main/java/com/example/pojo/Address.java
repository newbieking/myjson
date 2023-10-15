package com.example.pojo;

public class Address {
    private Long no;
    private String name;
    private String[] alias;
    public Address(Long no, String name, String[] alias) {
        this.no = no;
        this.name = name;
        this.alias = alias;
    }
    public String[] getAlias() {
        return alias;
    }
    public String getName() {
        return name;
    }
    public Long getNo() {
        return no;
    }
    
    public Address() {
    }
    public Address(Long no, String name) {
        this.name = name;
        this.no = no;
    }

    
}
