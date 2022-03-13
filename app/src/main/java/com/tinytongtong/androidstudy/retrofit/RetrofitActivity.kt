package com.tinytongtong.androidstudy.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tinytongtong.androidstudy.R
import com.tinytongtong.androidstudy.retrofit.SimpleService.Contributor
import kotlinx.android.synthetic.main.activity_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)

        btn_github_repos.setOnClickListener {
            val repos: Call<List<GithubRepo>> = service.listRepos("tinyvampirepudge")

            // Fetch and print a list of the contributors to the library.
            repos.enqueue(object : Callback<List<GithubRepo>> {
                override fun onResponse(
                    call: Call<List<GithubRepo>>,
                    response: Response<List<GithubRepo>>
                ) {
                    Log.e("RetrofitActivity", "onResponse")
                    val list: List<GithubRepo>? = response.body()
                    list?.also {
                        for (repo in it) {
                            Log.e("RetrofitActivity", "repo:" + repo)
                        }
                    }
                }

                override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                    Log.e("RetrofitActivity", "onFailure t:" + t.toString())
                }

            })
        }
    }
}