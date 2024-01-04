package sohee.cheon.moviedb.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.response.PopularMovieResponse
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val IODispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke() : PopularMovieResponse? {
        val response = repository.getPopularMovie().single()

        return response.getOrNull()
    }
}