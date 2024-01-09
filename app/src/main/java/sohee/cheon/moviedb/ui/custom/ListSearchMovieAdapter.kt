package sohee.cheon.moviedb.ui.custom

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.data.response.MovieInfo
import sohee.cheon.moviedb.databinding.ItemSearchMovieBinding

class ListSearchMovieAdapter (
    private val clickListener: OnItemClickListener?,
    private val context: Context,
) : ListAdapter<MovieInfo, ListSearchMovieAdapter.SearchMovieViewHolder>(SearchMovieDiffUtil) {
    private val moviePosterBasePath = BuildConfig.IMAGE_BASE_URL

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    class SearchMovieViewHolder(binding: ItemSearchMovieBinding): RecyclerView.ViewHolder(binding.root) {
        val movieLayout = binding.root
        val moviePoster = binding.moviePoster
        val movieTitle = binding.movieTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieViewHolder {
        val view = ItemSearchMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMovieViewHolder, position: Int) {
        val item = getItem(position)

        holder.movieLayout.setOnClickListener {
            clickListener?.onItemClick(item.id)
        }

        holder.apply {
            Glide.with(context)
                .load(moviePosterBasePath + item.posterPath)
                .override(LayoutParams.WRAP_CONTENT)
                .into(holder.moviePoster)
            holder.movieTitle.text = item.title
        }
    }

}

object SearchMovieDiffUtil : DiffUtil.ItemCallback<MovieInfo>() {
    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}