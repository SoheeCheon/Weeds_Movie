package sohee.cheon.moviedb.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.MovieListResponse
import sohee.cheon.moviedb.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
): MovieRepository {
    override fun getPopularMovie(token: String): Flow<Result<MovieListResponse>> = flow {
        val call = service.getPopularMovie(token)
        try {
            val result : Response<MovieListResponse> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getPopularMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getPopularMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getTopRated(token: String): Flow<Result<MovieListResponse>> = flow {
        val call = service.getTopRated(token)
        try {
            val result : Response<MovieListResponse> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getPopularMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getPopularMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getUpcoming(token: String): Flow<Result<MovieListResponse>> = flow {
        val call = service.getUpComing(token)
        try {
            val result : Response<MovieListResponse> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getPopularMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getPopularMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getDetailMovie(token: String, movieId: Int): Flow<Result<DetailMovieInfo>> = flow {
        val call = service.getDetailMovie(token, movieId = movieId)
        try {
            val result : Response<DetailMovieInfo> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getPopularMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getPopularMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }
}