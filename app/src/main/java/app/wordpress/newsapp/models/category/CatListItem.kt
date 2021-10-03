package app.wordpress.newsapp.models.category

data class CatListItem(

    val id: Int,
    val count: Int,
    val name: String,
    val parent: Int,
)