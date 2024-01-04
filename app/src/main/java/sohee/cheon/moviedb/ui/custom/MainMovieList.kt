package sohee.cheon.moviedb.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import sohee.cheon.moviedb.databinding.ListMainMovieBinding

class MainMovieList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding = ListMainMovieBinding.inflate(LayoutInflater.from(context), this, false)

    init {

    }
}