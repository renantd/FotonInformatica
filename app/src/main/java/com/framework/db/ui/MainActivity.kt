 package com.framework.db.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.framework.db.R
import com.framework.db.adapter.RecyclerAdapter
import com.framework.db.model.Article
import com.framework.db.model.data.NewsDataSource
import com.framework.db.presenter.ViewHome
import com.framework.db.presenter.news.NewsPresenter
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AbstractActivity(),ViewHome.View {

     private val mainAdapter by lazy {
         RecyclerAdapter()
     }

     private lateinit var presenter: NewsPresenter

     override fun getLayout(): Int =  R.layout.activity_main

     override fun onInject() {
       val dataSource = NewsDataSource(this)
       presenter = NewsPresenter(this , dataSource)
       presenter.requestAll()
       configRecycle()
         clickAdapter()
     }

     private fun configRecycle(){
         with(rvNews){
             adapter = mainAdapter
             layoutManager = LinearLayoutManager(this@MainActivity)
             addItemDecoration(
                 DividerItemDecoration(
                     this@MainActivity , DividerItemDecoration.VERTICAL
                 )
             )
         }
     }

     private fun clickAdapter(){
         mainAdapter.setOnClickListener { article ->
             val intent = Intent(this, ArticleActivity::class.java)
             intent.putExtra("article" , article)
             startActivity(intent)
         }
     }

     override fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
     }

     override fun showFailure(message: String) {
         Toast.makeText(this,message,Toast.LENGTH_LONG).show()
     }

     override fun hideProgressBar() {
        rvProgressBar.visibility = View.INVISIBLE
     }

     override fun shoArticles(articles: List<Article>) {
         mainAdapter.differ.submitList(articles.toList())
     }

     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_item , menu)
         return super.onCreateOptionsMenu(menu)
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when(item.itemId){
             R.id.search_menu -> {
                 Intent(applicationContext , SearchActivity::class.java).apply {
                     startActivity(this)
                 }
             }
             R.id.favorite -> {
                 Intent(this , FavoriteActivity::class.java).apply {
                     startActivity(this)
                 }
             }
         }
         return super.onOptionsItemSelected(item)
     }

 }