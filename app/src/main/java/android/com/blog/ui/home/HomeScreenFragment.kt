package android.com.blog.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.com.blog.R
import android.com.blog.core.Resource
import android.com.blog.data.model.remote.HomeScreenDataSource
import android.com.blog.databinding.FragmentHomeScreenBinding
import android.com.blog.domain.HomeScreenRepoImpl
import android.com.blog.presentation.HomeScreenViewFactory
import android.com.blog.presentation.HomeScreenViewModel
import android.com.blog.ui.home.adapter.HomeScreenAdapter
import android.util.Log
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
private lateinit var binding: FragmentHomeScreenBinding

private val viewModel by viewModels<HomeScreenViewModel> {HomeScreenViewFactory(HomeScreenRepoImpl(
    HomeScreenDataSource()
))  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeScreenBinding.bind(view)
        //hago un progress bar en la Activity xml
        viewModel.fetchLatestPosts().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                   binding.ProgressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.ProgressBar.visibility = View.GONE
                    binding.rvHome.adapter = HomeScreenAdapter(result.data)
                }
                is Resource.Failure -> {
                    binding.ProgressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }
        })

    }
}