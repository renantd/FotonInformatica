package com.framework.db.presenter.news

import com.framework.db.model.NewsResponse
import com.framework.db.model.data.NewsDataSource
import com.framework.db.presenter.ViewHome

class NewsPresenter(
    val view: ViewHome.View ,
    private val dataSource: NewsDataSource): NewsHome.Presenter {

    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
       this.view.shoArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
       this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

}