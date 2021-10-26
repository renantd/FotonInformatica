package com.framework.db.model.data

import android.content.Context
import com.framework.db.model.Article
import com.framework.db.model.db.ArticleDatabase
import com.framework.db.network.RetrofitInstatnce
import com.framework.db.presenter.favorite.FavoriteHome
import com.framework.db.presenter.news.NewsHome
import com.framework.db.presenter.search.SearchHome
import kotlinx.coroutines.*
import retrofit2.Retrofit

class NewsDataSource(context: Context) {

    private var db: ArticleDatabase = ArticleDatabase(context)
    private var newsRepository: NewsRepository = NewsRepository(db)

    fun getBreakingNews(callback: NewsHome.Presenter){
        GlobalScope.launch(Dispatchers.Main){
          val response = RetrofitInstatnce.api.getBreakingNews("br")

          if(response.isSuccessful){
             response.body()?.let { newsResponse ->
                callback.onSuccess(newsResponse)
             }
              callback.onComplete()
          }else{
              callback.onError(response.message())
              callback.onComplete()
          }
        }

    }

    fun searchNews(term: String , callback: SearchHome.Presenter){
        GlobalScope.launch( Dispatchers.Main){
            val response = RetrofitInstatnce.api.searchNews(term)
            if(response.isSuccessful){
                response.body()?.let{ newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            }else{
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun saveArticle(article: Article){
        GlobalScope.launch(Dispatchers.Main){
            newsRepository.updateInsert(article)
        }
    }

    fun getAllArticle(callback: FavoriteHome.Presenter){
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = newsRepository.getAll()
            withContext(Dispatchers.Main){
                callback.onSuccess(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article?){
        GlobalScope.launch (Dispatchers.Main){
            article?.let { articlesafe ->
                newsRepository.delete(articlesafe)
            }
        }
    }

}