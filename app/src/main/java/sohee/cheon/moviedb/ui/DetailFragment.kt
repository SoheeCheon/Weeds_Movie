package sohee.cheon.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import sohee.cheon.moviedb.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment: Fragment() {
    private val binding by lazy { FragmentDetailBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.movieDetail.observe(viewLifecycleOwner) {

        }

        return binding.root
    }
}