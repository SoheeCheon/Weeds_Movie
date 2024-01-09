package sohee.cheon.moviedb

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import sohee.cheon.moviedb.data.DBRepositoryImpl
import sohee.cheon.moviedb.data.MovieRepositoryImpl
import sohee.cheon.moviedb.domain.MovieRepository
import sohee.cheon.moviedb.data.service.MovieService
import sohee.cheon.moviedb.data.db.Bookmark
import sohee.cheon.moviedb.domain.DBRepository
import javax.inject.Singleton

@HiltAndroidApp
class MovieDB: Application() {}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain: Interceptor.Chain ->
            val origin = chain.request()
            chain.proceed(origin.newBuilder().apply {
                addHeader("Authorization", "")
            }.build())
        }


    private val retrofit = Retrofit.Builder()
        .client(okHttpClient.build())
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

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepository(
        service: MovieService,
    ) : MovieRepository {
        return MovieRepositoryImpl(service)
    }

    @Provides
    fun provideDBRepository(
        bookmarkDB : Bookmark.BookmarkSQLiteHelper
    ) : DBRepository {
        return DBRepositoryImpl(bookmarkDB)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {
    @Provides
    fun provideIoDispatcher() : CoroutineDispatcher {
        return Dispatchers.IO
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    fun provideBookMarkSQLiteHelper(
        @ApplicationContext context: Context
    ): Bookmark.BookmarkSQLiteHelper {
        return Bookmark.BookmarkSQLiteHelper(context)
    }
}