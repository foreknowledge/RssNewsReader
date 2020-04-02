package com.foreknowledge.rssnewsreader.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.foreknowledge.rssnewsreader.holder.BaseViewHolder

abstract class BaseRecyclerAdapter<B : ViewDataBinding, T : Any>(
    @LayoutRes private val layoutResId: Int
) : RecyclerView.Adapter<BaseViewHolder<B, T>>() {
    protected var items = listOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        object : BaseViewHolder<B, T>(layoutResId, parent) {}

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int) = items[position]

    open fun updateItems(newItems: List<T>?) {
        if (newItems != null) {
            items = newItems
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<B, T>, position: Int) {
        holder.bind(getItem(position))
    }
}