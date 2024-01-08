package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.response.SearchMovieResponse
import javax.inject.Inject

class SearchMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(token: String, word: String) : SearchMovieResponse? {
        return repository.searchMovie(token, word).single().getOrNull()
    }
}