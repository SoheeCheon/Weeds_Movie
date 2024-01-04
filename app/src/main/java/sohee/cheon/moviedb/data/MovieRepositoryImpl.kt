package sohee.cheon.moviedb.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import sohee.cheon.moviedb.data.response.PopularMovieResponse
import sohee.cheon.moviedb.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService
): MovieRepository {
    override fun getPopularMovie(): Flow<Result<PopularMovieResponse>> = flow {
        val call = service.getPopularMovie()
        try {
            val result : Response<PopularMovieResponse> = call.execute()
            val responseBody = result.body()
            if (result.code() != 200 || responseBody == null) {
                val error = Exception("${result.code()} : ${result.message()}")
                Log.e("getPopularMovie", "$error")
                emit(Result.failure(error))
            } else {
                emit(Result.success(responseBody))
            }
        } catch (e : Exception) {
            emit(Result.failure(e))
        }
    }
}