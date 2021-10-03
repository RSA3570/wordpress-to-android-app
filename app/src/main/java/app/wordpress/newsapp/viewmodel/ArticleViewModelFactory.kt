package app.wordpress.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.wordpress.newsapp.repository.PostRepository

class ArticleViewModelFactory(private val repository: PostRepository, private val page: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArticleViewModel(repository, page) as T
    }
}