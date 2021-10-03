package app.wordpress.newsapp.myapplication

import android.app.Application
import app.wordpress.newsapp.api.ApiHelper
import app.wordpress.newsapp.api.ApiService
import app.wordpress.newsapp.repository.PostRepository

class PostApplication: Application() {

    lateinit var postRepository: PostRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiService = ApiHelper.getInstance().create(ApiService::class.java)
        postRepository = PostRepository(apiService, applicationContext)
    }
}