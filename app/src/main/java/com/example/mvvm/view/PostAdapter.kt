package com.example.mvvm.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.PostItemBinding
import com.example.mvvm.model.PostItem
import com.example.mvvm.viewModel.DiffUtilCallback

class  PostAdapter(private val postList: ArrayList<PostItem>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    private lateinit var binding: PostItemBinding
    private val TAG: String = "Android"

    inner class ViewHolder(itemBinding: PostItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        val idTxt: TextView = itemBinding.id
        val titleTxt: TextView = itemBinding.title
        val bodyTxt: TextView = itemBinding.body

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.post_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idTxt.text = postList[position].id.toString()
        Log.d(TAG, "onBindViewHolder: " + holder.idTxt.toString())
        holder.titleTxt.text = postList[position].title
        holder.bodyTxt.text = postList[position].body
    }


    override fun getItemCount(): Int {
        return postList.size
    }


    fun updatePostList(newUpdatedPost: List<PostItem>) {
        //     Log.d(TAG, "updatepostList: $newupdatedPost")
        val diffUtilCallback  = DiffUtilCallback(this.postList,newUpdatedPost)
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(diffUtilCallback)
        postList.clear()
        postList.addAll(newUpdatedPost)
        diffResult.dispatchUpdatesTo(this)

        //  Log.d(TAG, "updatepostList: $postList")
    }

}