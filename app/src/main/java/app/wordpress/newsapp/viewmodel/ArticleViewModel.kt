package app.wordpress.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordpress.newsapp.models.post.Post
import app.wordpress.newsapp.repository.PostRepository
import app.wordpress.newsapp.response.Response
import kotlinx.coroutines.launch


class ArticleViewModel(private val repository: PostRepository, private val page: Int): ViewModel() {
    init {
        viewModelScope.launch {
            repository.getLatestArticles(page)
        }
    }

    val articles: LiveData<Response<Post>>
    get() = repository.article
}