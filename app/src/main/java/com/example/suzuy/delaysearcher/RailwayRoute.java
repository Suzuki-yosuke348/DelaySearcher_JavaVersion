package com.example.suzuy.delaysearcher;

import java.io.Serializable;

/**
 * Created by suzuy on 2017/01/18.
 */

public class RailwayRoute implements Serializable{
    private String name;
    private String company;
    public RailwayRoute(String n, String c)
    {
        setName(n);
        setCompany(c);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
}
