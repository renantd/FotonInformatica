package com.framework.db.presenter.news

import com.framework.db.model.NewsResponse

interface NewsHome {

    interface  Presenter{
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }

}