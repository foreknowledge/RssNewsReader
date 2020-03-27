package com.foreknowledge.rssnewsreader.util

import androidx.recyclerview.widget.DiffUtil
import com.foreknowledge.rssnewsreader.model.data.News

class NewsDiffUtil(private val newList: List<News>, private val oldList: List<News>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
}