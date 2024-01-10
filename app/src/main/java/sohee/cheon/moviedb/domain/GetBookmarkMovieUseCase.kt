package sohee.cheon.moviedb.domain

import android.util.Log
import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.MovieInfo
import javax.inject.Inject

class GetBookmarkMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dbRepository: DBRepository
) {
    suspend operator fun invoke(token: String) : ArrayList<DetailMovieInfo> {
        val bookmark = dbRepository.getBookmark().single()
        val result = arrayListOf<DetailMovieInfo>()


        for (book in bookmark) {
            val response =
                movieRepository.getDetailMovie(token, book).single().getOrNull() ?: continue
            result += response
        }

        return result
    }
}