package app.wordpress.newsapp.adapters

import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import app.wordpress.newsapp.R
import app.wordpress.newsapp.activity.ArticleDetailActivity
import app.wordpress.newsapp.models.post.PostItem
import app.wordpress.newsapp.utils.DateTimeUtils
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

class HomeRecentArticleAdapter(private val context: Context, private val homeRecentArticleList: ArrayList<PostItem>):
    RecyclerView.Adapter<HomeRecentArticleAdapter.HomeRecentArticleViewHolder>() {

    class HomeRecentArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgPost: ImageView = itemView.findViewById(R.id.post_img)
        val tvCategoryName: TextView = itemView.findViewById(R.id.category_name)
        val tvPostTitle: TextView = itemView.findViewById(R.id.title_text)
        val tvPostDate: TextView = itemView.findViewById(R.id.date_text)
        val cardView: CardView = itemView.findViewById(R.id.card_view_top)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecentArticleViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_recent_article_home, parent, false)
        return HomeRecentArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeRecentArticleViewHolder, position: Int) {
        val model = homeRecentArticleList[position]
        val imgUrl: String = model.jetpack_featured_media_url
        if (imgUrl != null) {
            Glide.with(context)
                .load(imgUrl)
                .into(holder.imgPost)
        } else {
            Glide.with(context)
                .load(R.drawable.ic_launcher_foreground)
                .into(holder.imgPost)
        }

        holder.tvCategoryName.text = model._embedded.wp_term[0][0].name
        val contentTitle = Html.fromHtml(model.title.rendered)
        holder.tvPostTitle.text = contentTitle
        holder.tvPostDate.text = DateTimeUtils.getFormattedDate(model.date)

        val rand = Random()

        when (rand.nextInt(5) + 1) {
            1 -> holder.tvCategoryName.background = ContextCompat.getDrawable(
                context,
                R.drawable.rectangle_orange
            )
            2 -> holder.tvCategoryName.background = ContextCompat.getDrawable(
                context,
                R.drawable.rectangle_purple
            )
            3 -> holder.tvCategoryName.background = ContextCompat.getDrawable(
                context,
                R.drawable.rectangle_green
            )
            4 -> holder.tvCategoryName.background = ContextCompat.getDrawable(
                context,
                R.drawable.rectangle_red
            )
            5 -> holder.tvCategoryName.background = ContextCompat.getDrawable(
                context,
                R.drawable.rectangle_pink
            )

        }

        holder.cardView.setOnClickListener {
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("imgUrl", model.jetpack_featured_media_url)
            intent.putExtra("title", contentTitle.toString())
            intent.putExtra("date", DateTimeUtils.getFormattedDate(model.date))
            intent.putExtra("cat_name", model._embedded.wp_term[0][0].name)
            intent.putExtra("content", model.content.rendered)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return homeRecentArticleList.size
    }
}