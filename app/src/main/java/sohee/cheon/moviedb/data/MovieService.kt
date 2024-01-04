package sohee.cheon.moviedb.data

import retrofit2.Call
import retrofit2.http.GET
import sohee.cheon.moviedb.data.response.PopularMovieResponse

interface MovieService {
    @GET("/authentication")
    fun getStart()

    @GET("/movie/popularity")
    fun getPopularMovie() : Call<PopularMovieResponse>
}