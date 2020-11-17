package com.tinytongtong.androidstudy.eventbus;

/**
 * @Description:
 * @Author tinytongtong
 * @Date 2020/11/16 3:47 PM
 * @Version
 */
public class EventData {
    private String message;

    public EventData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
