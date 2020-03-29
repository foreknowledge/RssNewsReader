package com.foreknowledge.rssnewsreader.util

import androidx.recyclerview.widget.DiffUtil
import com.foreknowledge.rssnewsreader.model.News

class NewsDiffUtil(private val newList: List<News>, private val oldList: List<News>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return  oldItem.title == newItem.title &&
                oldItem.link == newItem.link &&
                oldItem.description == newItem.description &&
                oldItem.imageUrl == newItem.imageUrl &&
                oldItem.keywords == newItem.keywords
    }

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
}