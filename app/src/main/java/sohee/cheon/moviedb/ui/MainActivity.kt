package sohee.cheon.moviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import sohee.cheon.moviedb.BuildConfig
import sohee.cheon.moviedb.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (supportFragmentManager.backStackEntryCount == 0) finish()

            viewModel.popList()
            supportFragmentManager.popBackStack()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setToken("Bearer ${BuildConfig.AUTHRIZATION}")

        this.onBackPressedDispatcher.addCallback(this, callback)

        setContentView(binding.root)
    }

}