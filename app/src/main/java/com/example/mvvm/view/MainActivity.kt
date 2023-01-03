package com.example.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.viewModel.PostListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostListViewModel
    private val postAdapter = PostAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[PostListViewModel::class.java]
        viewModel.refresh()
        observeViewModel()
         binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter=postAdapter
            Log.d("AndroidMain", "onCreate: $postAdapter")
        }

    }

    private fun observeViewModel() {
        viewModel.posts.observe(this, Observer { postList ->
            postList?.let {
                Log.d("main", "observeViewModel: $postList")
                postAdapter.updatePostList(postList  )
            }

        })
    }

}