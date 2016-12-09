package com.scienjus.smartqq.tuling;


public class News {
    private String article;
    private String source;
    private String icon;
    private String detailUrl;

    public News() {
    }

    public News(String article, String source, String icon, String detailUrl) {
        this.article = article;
        this.source = source;
        this.icon = icon;
        this.detailUrl = detailUrl;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
