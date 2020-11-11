package com.tinytongtong.androidstudy.templates.adaptertemplate;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2020/11/10 1:48 PM
 * @Version
 */
public class PersonListBean {
    private String name;

    public PersonListBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
