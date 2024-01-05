package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.Flow
import sohee.cheon.moviedb.data.response.PopularMovieResponse


interface MovieRepository {
    fun getPopularMovie(token: String): Flow<Result<PopularMovieResponse>>
    fun getTopRated(token: String): Flow<Result<PopularMovieResponse>>
    fun getUpcoming(token: String): Flow<Result<PopularMovieResponse>>
}
