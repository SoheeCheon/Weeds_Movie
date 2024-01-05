package sohee.cheon.moviedb.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import sohee.cheon.moviedb.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.startApp()

        viewModel.movieListResponse.observe(viewLifecycleOwner) {
            it?.let {
                Log.d("popular", "${it.results[0].id}")
                binding.popularMovieList.setData(it.results)
            }
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            it?.let {
                Log.d("toprate", "${it.results[0].id}")
                binding.topRatedMovieList.setData(it.results)
            }
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            it?.let {
                Log.d("upcoming", "${it.results[0].id}")
                binding.upcomingMovieList.setData(it.results)
            }
        }

        return binding.root
    }
}