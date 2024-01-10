package sohee.cheon.moviedb.ui.custom

import android.app.ActionBar
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.data.response.DetailMovieInfo
import sohee.cheon.moviedb.data.response.MovieInfo
import sohee.cheon.moviedb.databinding.ItemMainMovieBinding

class ListBookmarkAdapter(
    private val clickListener: OnItemClickListener?,
    private val context: Context,
) : ListAdapter<DetailMovieInfo, ListBookmarkAdapter.MovieViewHolder>(BookmarkMovieDiff) {
    private val moviePosterBasePath = BuildConfig.IMAGE_BASE_URL

    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    class MovieViewHolder(binding: ItemMainMovieBinding): RecyclerView.ViewHolder(binding.root) {
        val movieLayout = binding.root
        val moviePoster = binding.moviePoster
        val movieTitle = binding.movieTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMainMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)

        holder.movieLayout.setOnClickListener {
            clickListener?.onItemClick(item.id)
        }

        holder.apply {
            Glide.with(context)
                .load(moviePosterBasePath + item.posterPath)
                .override(ActionBar.LayoutParams.WRAP_CONTENT)
                .into(holder.moviePoster)
            holder.movieTitle.text = item.title
        }
    }

}

object BookmarkMovieDiff : DiffUtil.ItemCallback<DetailMovieInfo>() {
    override fun areContentsTheSame(oldItem: DetailMovieInfo, newItem: DetailMovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: DetailMovieInfo, newItem: DetailMovieInfo): Boolean {
        return oldItem == newItem
    }
}