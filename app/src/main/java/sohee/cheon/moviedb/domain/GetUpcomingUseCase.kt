package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.response.MovieListResponse
import javax.inject.Inject

class GetUpcomingUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(token: String) : MovieListResponse? {
        val response = repository.getUpcoming(token).single()

        return response.getOrNull()
    }
}