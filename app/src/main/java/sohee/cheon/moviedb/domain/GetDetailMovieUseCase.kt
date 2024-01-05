package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.MovieListResponse
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(token: String, movieId: Int) : DetailMovieInfo? {
        val response = repository.getDetailMovie(token, movieId).single()

        return response.getOrNull()
    }
}