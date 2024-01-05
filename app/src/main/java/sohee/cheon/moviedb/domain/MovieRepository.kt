package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.Flow
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.MovieListResponse


interface MovieRepository {
    fun getPopularMovie(token: String): Flow<Result<MovieListResponse>>
    fun getTopRated(token: String): Flow<Result<MovieListResponse>>
    fun getUpcoming(token: String): Flow<Result<MovieListResponse>>
    fun getDetailMovie(token: String, movieId: Int): Flow<Result<DetailMovieInfo>>
}
