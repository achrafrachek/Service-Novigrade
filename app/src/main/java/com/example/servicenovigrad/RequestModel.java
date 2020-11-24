package com.example.servicenovigrad;

public class RequestModel {
    private String name;
    private String Service;
    private String statue;

    public RequestModel() {
    }

    public RequestModel(String name, String service, String statue) {
        this.name = name;
        Service = service;
        this.statue = statue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return Service;
    }

    public void setService(String service) {
        Service = service;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue;
    }
}
