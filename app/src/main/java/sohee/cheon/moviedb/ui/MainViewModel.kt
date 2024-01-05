package sohee.cheon.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.MovieListResponse
import sohee.cheon.moviedb.domain.GetDetailMovieUseCase
import sohee.cheon.moviedb.domain.GetMovieUseCase
import sohee.cheon.moviedb.domain.GetTopRatedUseCase
import sohee.cheon.moviedb.domain.GetUpcomingUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _popularMovies = MutableLiveData<MovieListResponse>()
    val movieListResponse : LiveData<MovieListResponse> = _popularMovies

    private val _topRatedMovies = MutableLiveData<MovieListResponse>()
    val topRatedMovies : LiveData<MovieListResponse> = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<MovieListResponse>()
    val upcomingMovies : LiveData<MovieListResponse> = _upcomingMovies

    private val _movieDetail = MutableLiveData<DetailMovieInfo>()
    val movieDetail : LiveData<DetailMovieInfo> = _movieDetail

    private val _token = MutableLiveData<String>()
    val token : LiveData<String> = _token

    fun setToken(token: String) {
        _token.value = token
    }

    fun startApp() {
        getMovie()
        getTopRateMovie()
        getUpcoming()
    }

    private fun getMovie() {
        CoroutineScope(ioDispatcher).launch {
            val result = getMovieUseCase(_token.value ?: "")
            _popularMovies.postValue(result)
        }
    }

    private fun getTopRateMovie() {
        CoroutineScope(ioDispatcher).launch {
            val result = getTopRatedUseCase(_token.value ?: "")
            _topRatedMovies.postValue(result)
        }
    }

    private fun getUpcoming() {
        CoroutineScope(ioDispatcher).launch {
            val result = getUpcomingUseCase(_token.value ?: "")
            _upcomingMovies.postValue(result)
        }
    }

    fun getDetailMovie(id: Int) {
        CoroutineScope(ioDispatcher).launch {
            val result = getDetailMovieUseCase(_token.value ?: "", id)
            _movieDetail.postValue(result)
        }
    }
}