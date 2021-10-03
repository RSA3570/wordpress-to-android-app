package app.wordpress.newsapp.models.post

import com.google.gson.annotations.SerializedName

data class Embedded(
    val author: List<Author>,
    @SerializedName("wp:term")
    val wp_term: List<PostCatList>
)