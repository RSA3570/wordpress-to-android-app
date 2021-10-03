package app.wordpress.newsapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.wordpress.newsapp.R
import app.wordpress.newsapp.adapters.HomeRecentArticleAdapter
import app.wordpress.newsapp.appconstants.AppConstant
import app.wordpress.newsapp.myapplication.PostApplication
import app.wordpress.newsapp.response.Response
import app.wordpress.newsapp.viewmodel.ArticleViewModel
import app.wordpress.newsapp.viewmodel.ArticleViewModelFactory
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var articleViewModel: ArticleViewModel

    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    private lateinit var noDataView: LinearLayout
    private lateinit var noInternetView: LinearLayout

    private lateinit var progressBar: ProgressBar

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeRecentArticleAdapter: HomeRecentArticleAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initDrawer()
        initView()

        progressBar = findViewById(R.id.spin_kit)
        val doubleBounce: Sprite = DoubleBounce()
        progressBar.indeterminateDrawable = doubleBounce

        //progressBar.visibility = View.VISIBLE
//        val postApi = ApiHelper.getInstance().create(ApiService::class.java)
//        GlobalScope.launch {
//            val postResponse = postApi.getLatestArticles(1)
//
//            if (postResponse != null){
//                Log.d("RR: ", postResponse.body().toString())
//            }
//        }

        val postRepository = (application as PostApplication).postRepository
        val page = 1
        articleViewModel = ViewModelProvider(this, ArticleViewModelFactory(postRepository, page)).get(ArticleViewModel::class.java)
        articleViewModel.articles.observe(this, { article ->
            when(article){
                is Response.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    article.data?.let {
                        Toast.makeText(this@MainActivity, it[0].excerpt.toString(), Toast.LENGTH_LONG).show()
                        homeRecentArticleAdapter = HomeRecentArticleAdapter(this@MainActivity, it)
                        recyclerView.adapter = homeRecentArticleAdapter
                        progressBar.visibility = View.GONE
                    }
                }
                is Response.Error -> {
                    if (article.errorMsg.toString() == "No Internet"){
                        noInternetView.visibility = View.VISIBLE
                        swipeRefreshLayout.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, article.errorMsg.toString(), Toast.LENGTH_LONG).show()
                    }
                    else{
                        noDataView.visibility = View.VISIBLE
                        swipeRefreshLayout.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, article.errorMsg.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

    }

    private fun initToolbar(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar!!.title = AppConstant.APP_NAME
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    private fun getNavigationView(): NavigationView {
        return navigationView
    }

    private fun initDrawer(){
        drawerLayout = findViewById(R.id.drawer_layout)
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openDrawer,
            R.string.closeDrawer)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        actionBarDrawerToggle.syncState()

        navigationView = findViewById(R.id.navigationView)
        navigationView.itemIconTintList = null

        getNavigationView().setNavigationItemSelectedListener(this)

    }

    private fun initView(){
        swipeRefreshLayout = findViewById(R.id.swipe_refresh)
        recyclerView = findViewById(R.id.rvRecent)
        noDataView = findViewById(R.id.noDataView)
        noInternetView = findViewById(R.id.noInternetView)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_categories -> {
                Toast.makeText(this@MainActivity, "Categories", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, CatActivity::class.java)
                startActivity(intent)
            }

            R.id.action_youtube -> {
                Toast.makeText(this@MainActivity,"youtube", Toast.LENGTH_SHORT).show()
            }
            R.id.action_facebook -> {
                Toast.makeText(this@MainActivity,"facebook", Toast.LENGTH_SHORT).show()
            }
            R.id.action_twitter -> {
                Toast.makeText(this@MainActivity,"twitter", Toast.LENGTH_SHORT).show()
            }
            R.id.action_google_plus -> {
                Toast.makeText(this@MainActivity,"gplus", Toast.LENGTH_SHORT).show()
            }

            R.id.privacy_policy -> {
                Toast.makeText(this@MainActivity,"policy", Toast.LENGTH_SHORT).show()
            }
            R.id.about_us -> {
                Toast.makeText(this@MainActivity,"about", Toast.LENGTH_SHORT).show()
            }
            R.id.action_share -> {
                Toast.makeText(this@MainActivity,"share", Toast.LENGTH_SHORT).show()
            }
            R.id.action_rate_app -> {
                Toast.makeText(this@MainActivity,"Rate", Toast.LENGTH_SHORT).show()
            }
        }

        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        return true
    }
}