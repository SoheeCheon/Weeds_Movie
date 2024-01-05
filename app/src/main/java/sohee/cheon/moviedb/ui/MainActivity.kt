package sohee.cheon.moviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import sohee.cheon.moviedb.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setToken("Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYjM3MzhjYTUzZmQwMjEzZDA5MTQzZTIzNTNjOTFjZSIsInN1YiI6IjYxZjM0NzlhNWY2YzQ5MDAxYjQ2YWJiNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.B14O5k9sWshOPBBSVFIRA93cykqDljI1tueUsb-vzDE")

        setContentView(binding.root)
    }
}