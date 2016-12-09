package com.scienjus.smartqq.tuling;

import java.util.List;

public class TulingResponse {
    private Type type;
    private String text;
    private String url;

    private List<News> newsList;

    private List<Cook> cookList;

    public List<Cook> getCookList() {
        return cookList;
    }

    public void setCookList(List<Cook> cookList) {
        this.cookList = cookList;
    }

    public TulingResponse() {
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public enum Type {
        Text, Link, News, Cook, UnKnown,Train;

        public static Type valueOf(Integer code) {
            switch (code) {
                case 100000:
                    return Text;
                case 200000:
                    return Link;
                case 302000:
                    return News;
                case 308000:
                    return Cook;
                default:
                    return UnKnown;
            }
        }
    }
}
