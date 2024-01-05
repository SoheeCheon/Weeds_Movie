package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.response.MovieListResponse
import javax.inject.Inject

class GetTopRatedUseCase  @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(token: String) : MovieListResponse? {
        val response = repository.getTopRated(token).single()

        return response.getOrNull()
    }
}