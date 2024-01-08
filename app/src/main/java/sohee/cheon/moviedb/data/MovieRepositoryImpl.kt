package sohee.cheon.moviedb.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.DetailMovieTrailer
import sohee.cheon.moviedb.data.response.MovieCreditInfo
import sohee.cheon.moviedb.data.response.MovieListResponse
import sohee.cheon.moviedb.data.response.SearchMovieResponse
import sohee.cheon.moviedb.data.response.SimilarMovieInfo
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
                Log.e("getTopRated", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getTopRated", "$responseBody")
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
                Log.e("getUpcoming", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getUpcoming", "$responseBody")
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
                Log.e("getDetailMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getDetailMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getMovieTrailer(token: String, movieId: Int): Flow<Result<DetailMovieTrailer>> = flow {
        val call = service.getMovieTrailer(token, movieId = movieId)
        try {
            val result : Response<DetailMovieTrailer> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getMovieTrailer", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getMovieTrailer", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getSimilarMovie(token: String, movieId: Int): Flow<Result<SimilarMovieInfo>> = flow {
        val call = service.getSimilarMovie(token, movieId = movieId)
        try {
            val result : Response<SimilarMovieInfo> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getSimilarMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getSimilarMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getCredit(token: String, movieId: Int): Flow<Result<MovieCreditInfo>> = flow {
        val call = service.getCredit(token, movieId = movieId)
        try {
            val result : Response<MovieCreditInfo> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getSimilarMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("getSimilarMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }

    override fun searchMovie(token: String, word: String): Flow<Result<SearchMovieResponse>> = flow {
        val call = service.searchMovie(token, word = word)
        try {
            val result : Response<SearchMovieResponse> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("searchMovie", "${result}")
                emit(Result.failure(error))
            } else {
                Log.d("searchMovie", "$responseBody")
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }
}