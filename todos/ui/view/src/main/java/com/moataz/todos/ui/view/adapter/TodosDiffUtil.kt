package com.moataz.todos.ui.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.moataz.todos.ui.viewmodel.models.TodoUI

class TodosDiffUtil(
    private val oldList: List<TodoUI>,
    private val newList: List<TodoUI>,
    private val areContentsTheSame: (TodoUI, TodoUI) -> Boolean,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }
}
