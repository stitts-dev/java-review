package com.stitts.oop_review.model.pos;


import com.stitts.oop_review.enums.ServiceProvider;

import java.util.List;
import java.util.Map;

public class Pos {
    private String name;
    private List<ServiceProvider> supportedServiceProviderList;
    private Map<Integer, Double> installmentCommissionMap;

    public Pos(String name, Map<Integer, Double> installmentCommissionMap, List<ServiceProvider> supportedServiceProviderList) {
        this.name = name;
        this.installmentCommissionMap = installmentCommissionMap;
        this.supportedServiceProviderList = supportedServiceProviderList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Double> getInstallmentCommissionMap() {
        return installmentCommissionMap;
    }

    public void setInstallmentCommissionMap(Map<Integer, Double> installmentCommissionMap) {
        this.installmentCommissionMap = installmentCommissionMap;
    }

    public List<ServiceProvider> getSupportedServiceProviderList() {
        return supportedServiceProviderList;
    }

    public void setSupportedServiceProviderList(List<ServiceProvider> supportedServiceProviderList) {
        this.supportedServiceProviderList = supportedServiceProviderList;
    }
}
