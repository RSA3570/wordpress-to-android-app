package app.wordpress.newsapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.wordpress.newsapp.api.ApiService
import app.wordpress.newsapp.appconstants.AppConstant
import app.wordpress.newsapp.models.category.CatList
import app.wordpress.newsapp.models.post.Post
import app.wordpress.newsapp.response.Response
import app.wordpress.newsapp.utils.NetworkUtils
import java.lang.Exception


class PostRepository(private val apiService: ApiService, private val applicationContext: Context) {

    private val articlesLiveData = MutableLiveData<Response<Post>>()
    val article: LiveData<Response<Post>>
        get() = articlesLiveData

    private val catLiveData = MutableLiveData<Response<CatList>>()
    val cat: LiveData<Response<CatList>>
        get() = catLiveData

    //GET LATEST ARTICLES
    suspend fun getLatestArticles(page: Int){
        if (NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                val postResponse = apiService.getLatestArticles(page)
                if (postResponse != null){
                    articlesLiveData.postValue(Response.Success(postResponse.body()))
                }else{
                    articlesLiveData.postValue(Response.Error(AppConstant.API_ERROR))
                }
            }catch (e: Exception){
                articlesLiveData.postValue(Response.Error(e.message.toString()))
            }
        }else{
            articlesLiveData.postValue(Response.Error(AppConstant.NO_INTERNET))
        }
    }

    //GET ALL CATEGORIES
    suspend fun getCatList(){
        if (NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                val catResponse = apiService.getCatList()
                if (catResponse != null){
                    catLiveData.postValue(Response.Success(catResponse.body()))
                }else{
                    catLiveData.postValue(Response.Error(AppConstant.API_ERROR))
                }
            }catch (e: Exception){
                catLiveData.postValue(Response.Error(e.message.toString()))
            }
        }else{
            catLiveData.postValue(Response.Error(AppConstant.NO_INTERNET))
        }
    }
}