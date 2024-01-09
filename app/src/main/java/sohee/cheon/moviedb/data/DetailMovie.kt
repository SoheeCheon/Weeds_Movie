package sohee.cheon.moviedb.data

import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.DetailMovieTrailer
import sohee.cheon.moviedb.data.response.MovieCreditInfo
import sohee.cheon.moviedb.data.response.SimilarMovieInfo

data class DetailMovie(
    val movieInfo: DetailMovieInfo,
    val movieHeader: String,
    val movieTrailer: DetailMovieTrailer?,
    val credit: MovieCreditInfo?,
    val similarMovie: SimilarMovieInfo?,
    var bookmark: Boolean
)
