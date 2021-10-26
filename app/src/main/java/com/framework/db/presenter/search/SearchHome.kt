package com.framework.db.presenter.search

import com.framework.db.model.NewsResponse

interface SearchHome {

    interface  Presenter{
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }

}