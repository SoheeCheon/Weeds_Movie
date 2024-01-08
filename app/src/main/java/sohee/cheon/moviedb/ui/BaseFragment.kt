package sohee.cheon.moviedb.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import sohee.cheon.moviedb.R

abstract class BaseFragment<Binding: ViewBinding>(
    private val bindingInflater: (layoutInflater: LayoutInflater) -> Binding,
): Fragment() {
    private var _binding: Binding? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun moveDetail() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction().add(R.id.fragment_container, DetailFragment())
        transaction.replace(R.id.fragment_container, DetailFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun back() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}