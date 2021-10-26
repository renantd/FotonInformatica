package com.framework.db.ui

import android.webkit.WebViewClient
import com.framework.db.R
import com.framework.db.model.Article
import com.framework.db.model.data.NewsDataSource
import com.framework.db.presenter.ViewHome
import com.framework.db.presenter.favorite.FavoritePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AbstractActivity(), ViewHome.Favorite {

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {
        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this,dataSource)

        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }

        fab.setOnClickListener{
            presenter.saveArticle(article)
            Snackbar.make(it , "Artigo salvo com sucesso !!",Snackbar.LENGTH_LONG).show()
        }

    }

    private fun getArticle() {
        intent.extras?.let { articleSend ->
            article = articleSend.get("article") as Article
        }
    }

    override fun showArticle(articles: List<Article>) {}

}