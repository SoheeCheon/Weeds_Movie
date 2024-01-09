package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.response.MovieListResponse
import javax.inject.Inject

class ChangeBookmarkUseCase  @Inject constructor(
    private val repository: DBRepository,
) {
    suspend operator fun invoke(bookmark: Boolean, movieId: Int) : Boolean {
        return repository.changeBookmark(bookmark, movieId).single()
    }
}