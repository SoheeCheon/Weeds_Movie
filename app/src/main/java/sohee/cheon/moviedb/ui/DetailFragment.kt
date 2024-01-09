package sohee.cheon.moviedb.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.R
import sohee.cheon.moviedb.databinding.FragmentDetailBinding
import sohee.cheon.moviedb.ui.custom.ListCreditAdapter
import sohee.cheon.moviedb.ui.custom.ListMainMovieAdapter
import sohee.cheon.moviedb.ui.custom.ListTrailerAdapter
import java.text.DecimalFormat

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate), ListTrailerAdapter.OnItemClickListener, ListMainMovieAdapter.OnItemClickListener {
    private val viewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }
    private val trailerAdapter by lazy { ListTrailerAdapter(this, requireContext()) }
    private val creditAdapter by lazy { ListCreditAdapter(requireContext()) }
    private val similarMovieAdapter by lazy { ListMainMovieAdapter(this, requireContext()) }

    private val imageBaseUrl = BuildConfig.IMAGE_BASE_URL
    private val youtubeBaseUrl = BuildConfig.YOUTUBE_BASE_URL

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            binding.movieTitle.text = it.movieInfo.title

            Glide.with(requireContext())
                .load(imageBaseUrl + it.movieInfo.posterPath)
                .into(binding.moviePosterImage)

            binding.releaseDate.text = it.movieHeader
            binding.vote.text = DecimalFormat("#.#").format(it.movieInfo.voteAverage)
            binding.language.text = it.movieInfo.originalLanguage.uppercase()
            binding.status.text = it.movieInfo.status
            binding.revenue.text = DecimalFormat("#,###").format(it.movieInfo.revenue)
            binding.originalTitle.text = it.movieInfo.originalTitle
            binding.tagline.text = it.movieInfo.tagline
            binding.overview.text = it.movieInfo.overView

            if (it.movieTrailer?.results?.size == 0) {

            }

            trailerAdapter.submitList(it.movieTrailer?.results)
            binding.trailersList.adapter = trailerAdapter

            creditAdapter.submitList(it.credit?.cast)
            binding.castActorList.adapter = creditAdapter

            similarMovieAdapter.submitList(it.similarMovie?.results)
            binding.similarMovieList.adapter = similarMovieAdapter

            if (it.bookmark) {
                val src = ResourcesCompat.getDrawable(resources, R.drawable.bookmark, null)
                binding.bookmark.setImageDrawable(src)
            } else {
                val src = ResourcesCompat.getDrawable(resources, R.drawable.non_bookmark, null)
                binding.bookmark.setImageDrawable(src)
            }
        }

        binding.bookmark.setOnClickListener {
            viewModel.changeBookmark()
        }

        binding.backArrow.setOnClickListener {
             back()
        }
    }

    override fun onItemClick(id: Int) {
        viewModel.getDetailMovie(id)
        move(DetailFragment())
    }
    override fun onVideoClick(key: String) {
        // youtube로 이동
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeBaseUrl + key))
        requireContext().startActivity(intent)
    }
}