package com.tinytongtong.androidstudy.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinytongtong.androidstudy.retrofit.GithubRepo;

import java.util.List;

/**
 * @Description: TODO
 * @Author wangjianzhou
 * @Date 2022/3/13 5:32 PM
 */
public class GsonTest {

    public static void main(String[] args) {
        // 解析对象
        GithubRepo repo = new Gson().fromJson(GsonSource.SIMPLE_REPO, GithubRepo.class);
        System.out.println("repo:" + repo.getArchiveUrl());

        // 解析list
        List<GithubRepo> repos = new Gson().fromJson(GsonSource.GITHUB_REPOS, new TypeToken<List<GithubRepo>>() {
        }.getType());
        System.out.println("repos size:" + repos.size());
    }
}
