package com.tinytongtong.androidstudy.retrofit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Description: https://github.com/square/retrofit/blob/20a2afd773427f550961638e2dee0264e22a774d/samples/src/main/java/com/example/retrofit/SimpleService.java#L26
 * @Author wangjianzhou
 * @Date 2022/3/12 11:43 PM
 */
public class SimpleService {
    private static final String TAG = SimpleService.class.getSimpleName();

    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);

        @GET("/users/{user}/repos")
        Call<List<GithubRepo>> listRepos(@Path("user") String user);
    }

    public static void main(String... args) throws IOException {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Contributor>> call = github.contributors("square", "retrofit");

        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = call.execute().body();
        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }

        Call<List<GithubRepo>> callRepos = github.listRepos("tinyvampirepudge");
        callRepos.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                System.out.println("onResponse");
                List<GithubRepo> list = response.body();
                if (list != null) {
                    System.out.println("onResponse list.size():" + list.size());
                } else {
                    System.out.println("onResponse list is null");
                }
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                System.out.println("onFailure t:" + t.toString());
            }
        });
    }


}
