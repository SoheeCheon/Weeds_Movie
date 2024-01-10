package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.Flow

interface DBRepository {
    fun checkBookmark(movieId: Int): Flow<Boolean>
    fun changeBookmark(bookmark: Boolean, movieId: Int): Flow<Boolean>
    fun getBookmark(): Flow<Array<Int>>
}