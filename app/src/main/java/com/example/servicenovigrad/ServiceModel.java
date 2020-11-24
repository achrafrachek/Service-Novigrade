package com.example.servicenovigrad;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;

public class ServiceModel {
    private String serviceName;
    private String servicePrice;
    private ArrayList<String> infoList;
    private ArrayList<String> docList;

    public ServiceModel() {
    }

    public ServiceModel(String serviceName, String servicePrice, ArrayList<String> infoList, ArrayList<String> docList) {
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.infoList = infoList;
        this.docList = docList;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceModel that = (ServiceModel) o;
        return Objects.equals(serviceName, that.serviceName) &&
                Objects.equals(servicePrice, that.servicePrice) &&
                Objects.equals(infoList, that.infoList) &&
                Objects.equals(docList, that.docList);
    }



    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public ArrayList<String> getInfoList() {
        return infoList;
    }

    public void setInfoList(ArrayList<String> infoList) {
        this.infoList = infoList;
    }

    public ArrayList<String> getDocList() {
        return docList;
    }

    public void setDocList(ArrayList<String> docList) {
        this.docList = docList;
    }
}
