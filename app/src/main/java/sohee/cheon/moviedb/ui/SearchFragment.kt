package sohee.cheon.moviedb.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModelProvider
import sohee.cheon.moviedb.databinding.FragmentSearchBinding
import sohee.cheon.moviedb.ui.custom.ListMainMovieAdapter
import sohee.cheon.moviedb.ui.custom.ListSearchMovieAdapter

class SearchFragment: BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    ListSearchMovieAdapter.OnItemClickListener {
    private val adapter by lazy { ListSearchMovieAdapter(this, requireContext()) }
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reset.setOnClickListener {
            binding.searchMovie.text.clear()
        }

        binding.searchMovie.setOnEditorActionListener {v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                Log.d("Click enter", "${binding.searchMovie.text}")
                binding.searchMovie.performClick()
                viewModel.getSearch(binding.searchMovie.text.toString())
                true
            } else {
                false
            }
        }

        viewModel.searchMovies.observe(viewLifecycleOwner) {
            adapter.submitList(it.results)
            binding.searchMovieList.adapter = adapter
        }

        binding.backArrow.setOnClickListener {
            back()
            viewModel.clearSearch()
        }
    }

    override fun onItemClick(id: Int) {
        viewModel.getDetailMovie(id)
        move(DetailFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearSearch()
    }
}