package sohee.cheon.moviedb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sohee.cheon.moviedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}