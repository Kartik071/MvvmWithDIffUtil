package com.example.mvvm.viewModel

import com.example.mvvm.model.PostItem

data class DiffUtilCallback(val postList: ArrayList<PostItem>,
    val newUpdatedPost: List<PostItem>) : androidx.recyclerview.widget.DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return postList.size
    }

    override fun getNewListSize(): Int {
        return newUpdatedPost.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return postList[oldItemPosition].id == newUpdatedPost[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val postItemOld: PostItem = postList[oldItemPosition]
        val postItemNew: PostItem = newUpdatedPost[newItemPosition]

        return postItemOld.body == postItemNew.body

    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}