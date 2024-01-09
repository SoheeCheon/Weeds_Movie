package sohee.cheon.moviedb.domain

import kotlinx.coroutines.flow.single
import sohee.cheon.moviedb.data.DetailMovie
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val dbRepository: DBRepository
) {
    suspend operator fun invoke(token: String, movieId: Int): DetailMovie {
        val movieInfo = repository.getDetailMovie(token, movieId).single().getOrNull()
        val trailer = repository.getMovieTrailer(token, movieId).single().getOrNull()
        val credit = repository.getCredit(token, movieId).single().getOrNull()
        val similarMovie = repository.getSimilarMovie(token, movieId).single().getOrNull()
        val bookmark = dbRepository.checkBookmark(movieId).single()

        var movieHeader = ""
        movieInfo?.let {
            movieHeader = "${it.releaseDate}, ${it.productionCountries[0].iso.uppercase()}"
            for (gerne in it.genreIds ?: listOf()) {
                movieHeader += ", ${gerne.name}"
            }
        }

        return DetailMovie(
            movieInfo = movieInfo!!,
            movieHeader = movieHeader,
            movieTrailer = trailer,
            credit = credit,
            similarMovie = similarMovie,
            bookmark = bookmark
        )
    }
}