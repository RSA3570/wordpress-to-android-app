package app.wordpress.newsapp.models.post

data class PostItem(
    val id: Int,
    val date: String,
    val link: String,
    val title: Title,
    val content: Content,
    val excerpt: Excerpt,
    val categories: List<Int>,
    val author: Int,
    val jetpack_featured_media_url: String,
    val _embedded: Embedded
)