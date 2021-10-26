package com.framework.db.network

import com.framework.db.model.NewsResponse
import com.framework.db.util.Constants.Companion.API_KEY
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("/v2/top-headlines")
    suspend fun getBreakingNews(

        @Query("Country")
        countryCode: String = "br",

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY

    ): retrofit2.Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun searchNews(

        @Query("q")
        searchQuery: String,

        @Query("page")
        pageNumber: Int = 1,

        @Query("apiKey")
        apiKey: String = API_KEY

    ): retrofit2.Response<NewsResponse>

}