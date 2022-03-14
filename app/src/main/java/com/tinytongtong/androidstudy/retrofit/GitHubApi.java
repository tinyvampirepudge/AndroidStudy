package com.tinytongtong.androidstudy.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Description: TODO
 * @Author wangjianzhou
 * @Date 2022/3/13 5:35 PM
 */
public interface GitHubApi {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<SimpleService.Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);

    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> listRepos(@Path("user") String user);
}
