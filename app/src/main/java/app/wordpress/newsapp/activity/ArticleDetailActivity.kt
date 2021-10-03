package app.wordpress.newsapp.activity

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import app.wordpress.newsapp.R
import app.wordpress.newsapp.utils.WebUtils
import com.bumptech.glide.Glide
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var contentView: WebView
    private lateinit var contentTitle: TextView
    private lateinit var contentDate: TextView
    private lateinit var contentCatName: TextView
    private lateinit var contentFeaturedImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        // Get the Intent that started this activity and extract the string
        val imgUrl = intent.getStringExtra("imgUrl")
        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val catName = intent.getStringExtra("cat_name")
        val content = intent.getStringExtra("content")

        initToolbar(title.toString())
        initView()

        if (imgUrl != null) {
            Glide.with(this)
                .load(imgUrl)
                .into(contentFeaturedImg)
        } else {
            Glide.with(this)
                .load(R.drawable.ic_launcher_foreground)
                .into(contentFeaturedImg)
        }

        contentTitle.text = title
        contentDate.text = date
        contentCatName.text = catName
        val document = Jsoup.parse(content)
        val html = WebUtils.docToBetterHTML(document, this@ArticleDetailActivity)
        contentView.loadData(html, "text/html", "utf-8")
    }

    private fun initView(){
        contentView = findViewById(R.id.web_view)
        contentTitle = findViewById(R.id.title_text)
        contentDate = findViewById(R.id.date_text)
        contentCatName = findViewById(R.id.cat_name_text)
        contentFeaturedImg = findViewById(R.id.post_img)
    }

    private fun initToolbar(title: String){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

}