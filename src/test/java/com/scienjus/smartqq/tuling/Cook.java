package com.scienjus.smartqq.tuling;


public class Cook {
    private String name;
    private String icon;
    private String info;
    private String detailUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Cook() {
    }

    public Cook(String name, String icon, String info, String detailUrl) {
        this.name = name;
        this.icon = icon;
        this.info = info;
        this.detailUrl = detailUrl;
    }
}
