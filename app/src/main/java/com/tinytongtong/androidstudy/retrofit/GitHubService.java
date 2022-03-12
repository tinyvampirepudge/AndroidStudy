package com.tinytongtong.androidstudy.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Description: Retrofit
 * https://square.github.io/retrofit/
 * @Author wangjianzhou
 * @Date 2022/3/12 11:15 PM
 */
public interface GitHubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
