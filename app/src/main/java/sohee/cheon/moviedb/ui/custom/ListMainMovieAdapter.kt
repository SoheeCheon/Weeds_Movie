package sohee.cheon.moviedb.ui.custom

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import sohee.cheon.moviedb.data.response.MovieInfo
import sohee.cheon.moviedb.databinding.ItemMainMovieBinding

class ListMainMovieAdapter(
    private val clickListener: OnItemClickListener,
    private val context: Context
) : ListAdapter<MovieInfo, ListMainMovieAdapter.MovieViewHolder>(MovieDiffUtil) {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
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
            clickListener.onItemClick(position)
        }

        holder.apply {
            Glide.with(context)
                .load(item.posterPath)
                .into(holder.moviePoster)
            holder.movieTitle.text = item.title
        }
    }

}

object MovieDiffUtil : DiffUtil.ItemCallback<MovieInfo>() {
    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}