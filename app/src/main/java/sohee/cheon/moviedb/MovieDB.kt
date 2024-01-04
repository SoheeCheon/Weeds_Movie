package sohee.cheon.moviedb

import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import sohee.cheon.moviedb.data.MovieRepositoryImpl
import sohee.cheon.moviedb.domain.MovieRepository
import sohee.cheon.moviedb.data.MovieService
import javax.inject.Singleton

@HiltAndroidApp
class MovieDB {

    object RetrofitModule {
        private const val BASE_URL = "https://api.themoviedb.org/3"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        @Singleton
        @Provides
        fun provideMovieService() : MovieService {
            return retrofit.create(MovieService::class.java)
        }
    }

    object RepositoryModule {
        @Provides
        fun provideMovieRepository(
            service: MovieService
        ) : MovieRepository {
            return MovieRepositoryImpl(service)
        }
    }

    object DispatcherModule {
        @Provides
        fun provideIoDispatcher() : CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}