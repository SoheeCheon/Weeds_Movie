package sohee.cheon.moviedb.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object Retrofit {
    private const val BASE_URL = "https://api.themoviedb.org/3"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val movieService = retrofit.create(MovieService::class.java)
}