package com.framework.db.presenter.favorite

import com.framework.db.model.Article

interface FavoriteHome {

    interface Presenter{
        fun onSuccess(article: List<Article>)
    }

}