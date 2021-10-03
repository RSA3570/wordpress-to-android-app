package app.wordpress.newsapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import app.wordpress.newsapp.R
import app.wordpress.newsapp.adapters.CatAdapter
import app.wordpress.newsapp.adapters.HomeRecentArticleAdapter
import app.wordpress.newsapp.appconstants.AppConstant
import app.wordpress.newsapp.myapplication.PostApplication
import app.wordpress.newsapp.response.Response
import app.wordpress.newsapp.viewmodel.ArticleViewModel
import app.wordpress.newsapp.viewmodel.ArticleViewModelFactory
import app.wordpress.newsapp.viewmodel.CatViewModel
import app.wordpress.newsapp.viewmodel.CatViewModelFactory
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce

class CatActivity : AppCompatActivity() {

    private lateinit var catViewModel: CatViewModel

    private lateinit var toolbar: Toolbar

    private lateinit var recyclerView: RecyclerView
    private lateinit var catAdapter: CatAdapter

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cat)

        initToolbar()
        initView()

        val doubleBounce: Sprite = DoubleBounce()
        progressBar.indeterminateDrawable = doubleBounce

        val postRepository = (application as PostApplication).postRepository

        catViewModel = ViewModelProvider(this, CatViewModelFactory(postRepository)).get(
            CatViewModel::class.java)
        catViewModel.cat.observe(this, { cat ->
            when(cat){
                is Response.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    cat.data?.let {
                        Toast.makeText(this@CatActivity, it.toString(), Toast.LENGTH_LONG).show()
                        catAdapter = CatAdapter(this@CatActivity, it)
                        recyclerView.adapter = catAdapter
                        progressBar.visibility = View.GONE
                    }
                }
                is Response.Error -> {
                    if (cat.errorMsg.toString() == "No Internet"){
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@CatActivity, cat.errorMsg.toString(), Toast.LENGTH_LONG).show()
                    }
                    else{
                        progressBar.visibility = View.GONE
                        Toast.makeText(this@CatActivity, cat.errorMsg.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    private fun initToolbar(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "All Categories"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    private fun initView(){
        recyclerView = findViewById(R.id.catRecycler)
        progressBar = findViewById(R.id.spin_kit)

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