package com.framework.db.presenter.favorite

import com.framework.db.model.Article
import com.framework.db.model.data.NewsDataSource
import com.framework.db.presenter.ViewHome
import com.framework.db.ui.ArticleActivity

class FavoritePresenter(
    val view: ViewHome.Favorite,
    private val dataSource: NewsDataSource): FavoriteHome.Presenter {

    fun getAll(){
        this.dataSource.getAllArticle(this)
    }

    fun saveArticle(article: Article){
        this.dataSource.saveArticle(article)
    }

    override fun onSuccess(articles: List<Article>) {
        this.view.showArticle(articles)
    }

}