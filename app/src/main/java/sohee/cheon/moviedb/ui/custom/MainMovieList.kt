package sohee.cheon.moviedb.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import sohee.cheon.moviedb.R
import sohee.cheon.moviedb.data.response.MovieInfo
import sohee.cheon.moviedb.databinding.ListMainMovieBinding

class MainMovieList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ListMainMovieAdapter.OnItemClickListener {
    private val binding by lazy { ListMainMovieBinding.inflate(LayoutInflater.from(context), this, false) }
    private val adapter by lazy { ListMainMovieAdapter(this, context) }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MainMovieList,
            0, 0
        ).apply {
            binding.movieTitle.text = getString(R.styleable.MainMovieList_MovieListTitle)
            recycle()
            addView(binding.root)
        }
    }

    fun setData(items: List<MovieInfo>) {
        adapter.submitList(items)
        binding.movieList.adapter = adapter
    }

    override fun onItemClick(position: Int) {
//        movieClick(movies[position])
    }

    // 상세 페이지로 이동
    private fun movieClick(item: MovieInfo): Int {
        return item.id
    }
}