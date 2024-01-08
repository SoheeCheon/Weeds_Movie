package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.Flow
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.DetailMovieTrailer
import sohee.cheon.moviedb.data.response.MovieCreditInfo
import sohee.cheon.moviedb.data.response.MovieListResponse
import sohee.cheon.moviedb.data.response.SearchMovieResponse
import sohee.cheon.moviedb.data.response.SimilarMovieInfo


interface MovieRepository {
    fun getPopularMovie(token: String): Flow<Result<MovieListResponse>>
    fun getTopRated(token: String): Flow<Result<MovieListResponse>>
    fun getUpcoming(token: String): Flow<Result<MovieListResponse>>
    fun getDetailMovie(token: String, movieId: Int): Flow<Result<DetailMovieInfo>>
    fun getMovieTrailer(token: String, movieId: Int): Flow<Result<DetailMovieTrailer>>
    fun getSimilarMovie(token: String, movieId: Int): Flow<Result<SimilarMovieInfo>>
    fun getCredit(token: String, movieId: Int): Flow<Result<MovieCreditInfo>>
    fun searchMovie(token: String, word: String): Flow<Result<SearchMovieResponse>>
}
