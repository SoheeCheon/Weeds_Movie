package sohee.cheon.moviedb.data

import retrofit2.http.GET

interface MovieService {
    @GET("/authentication")
    fun getStart()

    @GET("/movie/popularity")
    fun getPopularMovie():
}