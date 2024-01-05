package sohee.cheon.moviedb.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.data.response.PopularMovieResponse

interface MovieService {
    @GET("authentication")
    fun getStart()

    @GET("movie/popular")
    fun getPopularMovie(
        @Header("Authorization") token: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
        @Query("page") page: Int = 1,
    ) : Call<PopularMovieResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Header("Authorization") token: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
        @Query("page") page: Int = 1,
    ) : Call<PopularMovieResponse>

    @GET("movie/upcoming")
    fun getUpComing(
        @Header("Authorization") token: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
        @Query("page") page: Int = 1,
    ) : Call<PopularMovieResponse>
}