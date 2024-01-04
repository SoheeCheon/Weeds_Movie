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
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _popularMovies = MutableLiveData<PopularMovieResponse>()
    val popularMovieResponse : LiveData<PopularMovieResponse> = _popularMovies

    fun getMovie() {
        CoroutineScope(ioDispatcher).launch {
            val result = getMovieUseCase()

            _popularMovies.postValue(result)
        }
    }
}