package com.framework.db.presenter.search

import com.framework.db.model.NewsResponse
import com.framework.db.model.data.NewsDataSource
import com.framework.db.presenter.ViewHome

class SearchPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource): SearchHome.Presenter {

    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term , this)
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