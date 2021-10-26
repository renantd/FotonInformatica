package com.framework.db.presenter

import com.framework.db.model.Article

interface ViewHome {
    interface  View{
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun shoArticles(articles: List<Article>)
    }

    interface  Favorite{
        fun showArticle(articles: List<Article>)
    }
}