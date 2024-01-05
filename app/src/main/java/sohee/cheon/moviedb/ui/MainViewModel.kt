package sohee.cheon.moviedb.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sohee.cheon.moviedb.data.response.PopularMovieResponse
import sohee.cheon.moviedb.domain.GetMovieUseCase
import sohee.cheon.moviedb.domain.GetTopRatedUseCase
import sohee.cheon.moviedb.domain.GetUpcomingUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _popularMovies = MutableLiveData<PopularMovieResponse>()
    val popularMovieResponse : LiveData<PopularMovieResponse> = _popularMovies

    private val _topRatedMovies = MutableLiveData<PopularMovieResponse>()
    val topRatedMovies : LiveData<PopularMovieResponse> = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<PopularMovieResponse>()
    val upcomingMovies : LiveData<PopularMovieResponse> = _upcomingMovies

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
}