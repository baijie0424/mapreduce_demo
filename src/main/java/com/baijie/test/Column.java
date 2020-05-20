package com.baijie.test;

/**
 * @ClassName Column
 * @Description
 * @Author bj
 * @Date 2020/4/22 15:23
 * @Version 1.0
 */
public class Column {
    private String key;
    private String header;
    private String width;
    private boolean allowSort;
    private boolean hidden;

    public Column() {
    }

    public Column(String key, String header, String width, boolean allowSort, boolean hidden) {
        this.key = key;
        this.header = header;
        this.width = width;
        this.allowSort = allowSort;
        this.hidden = hidden;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isAllowSort() {
        return allowSort;
    }

    public void setAllowSort(boolean allowSort) {
        this.allowSort = allowSort;
    }

    public boolean getHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "Column{" +
                "key='" + key + '\'' +
                ", header='" + header + '\'' +
                ", width='" + width + '\'' +
                ", allowSort=" + allowSort +
                ", hidden='" + hidden + '\'' +
                '}';
    }
}
