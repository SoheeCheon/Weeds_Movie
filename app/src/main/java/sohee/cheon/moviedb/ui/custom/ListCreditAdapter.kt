package sohee.cheon.moviedb.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.data.response.Credit
import sohee.cheon.moviedb.data.response.TrailerInfo
import sohee.cheon.moviedb.databinding.ItemActorBinding
import sohee.cheon.moviedb.databinding.ItemTrailerBinding

class ListCreditAdapter (
    private val context: Context
) : ListAdapter<Credit, ListCreditAdapter.CreditViewHolder>(CreditDiffUtil) {
    private val moviePosterBasePath = BuildConfig.IMAGE_BASE_URL
    class CreditViewHolder(binding: ItemActorBinding): RecyclerView.ViewHolder(binding.root) {
        val profile = binding.profile
        val name = binding.name
        val character = binding.character
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val view = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CreditViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val item = getItem(position)

        holder.apply {
            if (item.profilePath != null) {
                Glide.with(context)
                    .load(moviePosterBasePath + item.profilePath)
                    .into(holder.profile)
            }
        }

        holder.name.text = item.name
        holder.character.text = item.character
    }

}

object CreditDiffUtil : DiffUtil.ItemCallback<Credit>() {
    override fun areContentsTheSame(oldItem: Credit, newItem: Credit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Credit, newItem: Credit): Boolean {
        return oldItem == newItem
    }
}