package sohee.cheon.moviedb.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sohee.cheon.moviedb.data.DetailMovie
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.MovieListResponse
import sohee.cheon.moviedb.data.response.SearchMovieResponse
import sohee.cheon.moviedb.domain.ChangeBookmarkUseCase
import sohee.cheon.moviedb.domain.GetBookmarkMovieUseCase
import sohee.cheon.moviedb.domain.GetDetailMovieUseCase
import sohee.cheon.moviedb.domain.GetMovieUseCase
import sohee.cheon.moviedb.domain.GetTopRatedUseCase
import sohee.cheon.moviedb.domain.GetUpcomingUseCase
import sohee.cheon.moviedb.domain.SearchMovieUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getTopRatedUseCase: GetTopRatedUseCase,
    private val getUpcomingUseCase: GetUpcomingUseCase,
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val changeBookmarkUseCase: ChangeBookmarkUseCase,
    private val getBookmarkMovieUseCase: GetBookmarkMovieUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {
    private val _popularMovies = MutableLiveData<MovieListResponse>()
    val movieListResponse : LiveData<MovieListResponse> = _popularMovies

    private val _topRatedMovies = MutableLiveData<MovieListResponse>()
    val topRatedMovies : LiveData<MovieListResponse> = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<MovieListResponse>()
    val upcomingMovies : LiveData<MovieListResponse> = _upcomingMovies

    private val _bookmarkMovies = MutableLiveData<List<DetailMovieInfo>>()
    val bookmarkMovies : LiveData<List<DetailMovieInfo>> = _bookmarkMovies

    private val _movieDetail = MutableLiveData<DetailMovie>()
    val movieDetail : LiveData<DetailMovie> = _movieDetail

    // 비슷한 영화를 클릭할시 이전 화면의 데이터 저장
    private val _listMovie = MutableLiveData<List<DetailMovie>>()
    val listMovie : LiveData<List<DetailMovie>> = _listMovie

    private val _token = MutableLiveData<String>()
    val token : LiveData<String> = _token

    private val _searchMovies = MutableLiveData<SearchMovieResponse>()
    val searchMovies : LiveData<SearchMovieResponse> = _searchMovies

    fun setToken(token: String) {
        _token.value = token
    }

    fun startApp() {
        getMovie()
        getTopRateMovie()
        getUpcoming()
        getBookmark()
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

    private fun getBookmark() {
        CoroutineScope(ioDispatcher).launch {
            val result = getBookmarkMovieUseCase(_token.value ?: "")
            _bookmarkMovies.postValue(result)
        }
    }

    fun getDetailMovie(id: Int) {
        CoroutineScope(ioDispatcher).launch {
            val result = getDetailMovieUseCase(_token.value ?: "", id)
            Log.d("ViewModel getDetailMovie", "$result")
            _movieDetail.postValue(result)
        }
    }

    fun putList() {
        val list = _listMovie.value ?: arrayListOf()
        _movieDetail.value?.let {
            val updateList = list.plus(it)
            _listMovie.value = updateList
        }
    }

    fun popList() {
        val list = _listMovie.value ?: arrayListOf()
        if (list.isEmpty()) return
        _movieDetail.value = _listMovie.value?.last()
        _movieDetail.value?.let {
            val updateList = list.minus(it)
            _listMovie.value = updateList
        }
    }

    fun getSearch(word: String) {
        CoroutineScope(ioDispatcher).launch {
            val result = searchMovieUseCase(_token.value ?: "", word)

            result?.let {
                _searchMovies.postValue(result)
            }
        }
    }

    fun clearDetail() {
        _movieDetail.value = null
    }

    fun clearSearch() {
        _searchMovies.value = null
    }

    fun changeBookmark() {
        val movie = _movieDetail.value
        movie?.let {
            CoroutineScope(ioDispatcher).launch {
                val result = changeBookmarkUseCase(movie.bookmark, movie.movieInfo.id)

                val currentList = _bookmarkMovies.value ?: listOf()

                if (result) {
                    val updateList = currentList.plus(it.movieInfo)
                    _bookmarkMovies.postValue(updateList)
                } else {
                    val updateList = currentList.plus(it.movieInfo)
                    _bookmarkMovies.postValue(updateList)
                }

                _movieDetail.postValue(
                    DetailMovie(
                        movieHeader = movie.movieHeader,
                        movieInfo = movie.movieInfo,
                        movieTrailer = movie.movieTrailer,
                        similarMovie = movie.similarMovie,
                        credit = movie.credit,
                        bookmark = result
                    )
                )
            }
        }
    }
}