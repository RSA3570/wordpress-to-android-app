package app.wordpress.newsapp.models.post

data class Author(
    val id: Int,
    val name: String,
    val description: String,
    val link: String,
    val avatar_urls: AvatarUrls,
)