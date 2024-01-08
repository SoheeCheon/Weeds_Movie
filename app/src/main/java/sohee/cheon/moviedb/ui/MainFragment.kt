package sohee.cheon.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import sohee.cheon.moviedb.R
import sohee.cheon.moviedb.databinding.FragmentMainBinding
import sohee.cheon.moviedb.ui.custom.ListMainMovieAdapter

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), ListMainMovieAdapter.OnItemClickListener{
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }
    private val popularAdapter by lazy { ListMainMovieAdapter(this, requireContext()) }
    private val topRatedAdapter by lazy { ListMainMovieAdapter(this, requireContext()) }
    private val upcomingAdapter by lazy { ListMainMovieAdapter(this, requireContext()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startApp()

        viewModel.movieListResponse.observe(viewLifecycleOwner) {
            it?.let {
                popularAdapter.submitList(it.results)
                binding.popularMovieList.adapter = popularAdapter
            }
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            it?.let {
                topRatedAdapter.submitList(it.results)
                binding.topRatedMovieList.adapter = topRatedAdapter
            }
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            it?.let {
                upcomingAdapter.submitList(it.results)
                binding.upcomingMovieList.adapter = upcomingAdapter
            }
        }
    }

    override fun onItemClick(id: Int) {
        viewModel.getDetailMovie(id)
        moveDetail()
    }
}