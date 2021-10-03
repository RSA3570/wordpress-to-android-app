package app.wordpress.newsapp.api

import app.wordpress.newsapp.models.category.CatList
import app.wordpress.newsapp.models.comment.CommentList
import app.wordpress.newsapp.models.post.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //GET LATEST POST
    @GET(HttpParams.API_POSTS_END_POINT)
    suspend fun getLatestArticles(@Query(HttpParams.PAGE)page:Int) : Response<Post>
    //https://your-site-name.com/wp-json/wp/v2/posts?page=1

    //GET ALL CATEGORY LIST
    @GET(HttpParams.API_CATEGORIES_END_POINT)
    suspend fun getCatList() : Response<CatList>
    //https://your-site-name.com/wp-json/wp/v2/categories?page=1

    //GET ALL CATEGORY LIST
    suspend fun getCommentList(): Response<CommentList>
    //https://your-site-name.com/wp-json/wp/v2/categories?page=1

}