package com.framework.db.model.db

import androidx.room.*
import com.framework.db.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article): Long

    @Query("Select * from articles")
    fun getAll(): List<Article>

    @Delete
    suspend fun delete(article: Article)
}