package sohee.cheon.moviedb.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.DetailMovieTrailer
import sohee.cheon.moviedb.data.response.MovieCreditInfo
import sohee.cheon.moviedb.data.response.MovieListResponse
import sohee.cheon.moviedb.data.response.SimilarMovieInfo

interface MovieService {
    @GET("authentication")
    fun getStart()

    @GET("movie/popular")
    fun getPopularMovie(
        @Header("Authorization") token: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
        @Query("page") page: Int = 1,
    ) : Call<MovieListResponse>

    @GET("movie/top_rated")
    fun getTopRated(
        @Header("Authorization") token: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
        @Query("page") page: Int = 1,
    ) : Call<MovieListResponse>

    @GET("movie/upcoming")
    fun getUpComing(
        @Header("Authorization") token: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
        @Query("page") page: Int = 1,
    ) : Call<MovieListResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Header("Authorization") token: String,
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
    ) : Call<DetailMovieInfo>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Header("Authorization") token: String,
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR"
    ) : Call<DetailMovieTrailer>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovie(
        @Header("Authorization") token: String,
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
    ) : Call<SimilarMovieInfo>

    @GET("movie/{movie_id}/credits")
    fun getCredit(
        @Header("Authorization") token: String,
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = "kr-KR",
    ) : Call<MovieCreditInfo>
}