package com.foreknowledge.rssnewsreader.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.foreknowledge.rssnewsreader.BR

abstract class BaseViewHolder<out B : ViewDataBinding, T>(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context).inflate(layoutResId, parent, false)
) {
    private val binding: B = DataBindingUtil.bind(itemView)!!

    fun bind(item: T) {
        try {
            binding.run {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}