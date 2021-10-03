package app.wordpress.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.wordpress.newsapp.models.category.CatList
import app.wordpress.newsapp.repository.PostRepository
import app.wordpress.newsapp.response.Response
import kotlinx.coroutines.launch

class CatViewModel(private val repository: PostRepository): ViewModel() {
    init {
        viewModelScope.launch {
            repository.getCatList()
        }
    }

    val cat: LiveData<Response<CatList>>
        get() = repository.cat
}