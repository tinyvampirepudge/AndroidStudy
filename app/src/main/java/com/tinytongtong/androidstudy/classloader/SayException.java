package com.tinytongtong.androidstudy.classloader;

/**
 * @Description: TODO
 * @Author wangjianzhou
 * @Date 2022/3/21 3:18 PM
 */
public class SayException implements ISay {
    @Override
    public String saySth() {
        return "something wrong here!";
    }
}
