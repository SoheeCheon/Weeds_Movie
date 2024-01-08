package sohee.cheon.moviedb.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.data.response.MovieInfo
import sohee.cheon.moviedb.data.response.TrailerInfo
import sohee.cheon.moviedb.databinding.ItemMainMovieBinding
import sohee.cheon.moviedb.databinding.ItemTrailerBinding

class ListTrailerAdapter(
    private val clickListener: OnItemClickListener?,
    private val context: Context
) : ListAdapter<TrailerInfo, ListTrailerAdapter.TrailerViewHolder>(TrailerDiffUtil) {
    private val moviePosterBasePath = BuildConfig.IMAGE_BASE_URL

    interface OnItemClickListener {
        fun onVideoClick(id: String)
    }

    class TrailerViewHolder(binding: ItemTrailerBinding): RecyclerView.ViewHolder(binding.root) {
        val moviePoster = binding.trailerThumbnail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = ItemTrailerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrailerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        val item = getItem(position)

        holder.moviePoster.setOnClickListener {
            clickListener?.onVideoClick(item.key)
        }

        holder.apply {
            Glide.with(context)
                .load(moviePosterBasePath + item.key)
                .into(holder.moviePoster)
        }
    }

}

object TrailerDiffUtil : DiffUtil.ItemCallback<TrailerInfo>() {
    override fun areContentsTheSame(oldItem: TrailerInfo, newItem: TrailerInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: TrailerInfo, newItem: TrailerInfo): Boolean {
        return oldItem == newItem
    }
}