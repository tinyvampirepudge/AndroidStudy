package com.tinytongtong.androidstudy.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.retrofit.SimpleService.Contributor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @Description: retrofit源码学习
 *
 * @Author tinytongtong
 * @Date 2020/12/8 5:13 PM
 * @Version
 */
class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)

        val repos: Call<List<Repo>> = service.listRepos("octocat")

        // Fetch and print a list of the contributors to the library.
        val repoList: List<Repo>? = repos.execute().body()
        repoList?.also {
            for (repo in it) {
                Log.e("RetrofitActivity", "repo:" + repo);
            }
        }

    }
}