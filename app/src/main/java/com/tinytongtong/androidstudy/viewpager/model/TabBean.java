package com.tinytongtong.androidstudy.viewpager.model;

/**
 * @Description: tab对应的数据
 * @Author tinytongtong
 * @Date 2021/1/22 10:21 AM
 * @Version
 */
public class TabBean {
    private int type;
    private String title;

    public TabBean(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
