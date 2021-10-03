package app.wordpress.newsapp.api

object HttpParams {
    const val PER_PAGE = "per_page"
    const val PAGE = "page"
    const val CATEGORIES = "categories"
    const val ID = "id"
    const val SEARCH = "search"
    const val POST = "post"

    const val API_CATEGORIES_END_POINT = "wp-json/wp/v2/categories?page=1&"

    const val API_POSTS_END_POINT = "wp-json/wp/v2/posts?per_page=50&_embed=true"
    const val API_FEATURED_POSTS_END_POINT = "wp-json/wp/v2/posts?per_page=10&_embed=true&sticky=true"

    const val API_COMMENT_END_POINT = "wp-json/wp/v2/comments?"

    const val TOTAL_PAGE = "x-wp-totalpages"

    const val COMMENT_AUTHOR_NAME = "author_name"
    const val COMMENT_AUTHOR_EMAIL = "author_email"
    const val COMMENT_CONTENT = "content"
}
