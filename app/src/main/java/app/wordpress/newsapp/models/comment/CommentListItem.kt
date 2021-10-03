package app.wordpress.newsapp.models.comment

data class CommentListItem(
    val author_avatar_urls: AuthorAvatarUrls,
    val author_name: String,
    val content: Content,
    val date: String,
    val id: Int,
    val status: String,
)