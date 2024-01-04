package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.Flow
import sohee.cheon.moviedb.data.response.PopularMovieResponse


interface MovieRepository {
    fun getPopularMovie(): Flow<Result<PopularMovieResponse>>
}
