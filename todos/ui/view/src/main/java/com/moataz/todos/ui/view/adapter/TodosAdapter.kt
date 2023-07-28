package com.moataz.todos.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.databinding.ItemTodoBinding
import com.moataz.todos.ui.viewmodel.TodosClicksListener
import com.moataz.todos.ui.viewmodel.models.TodoUI

class TodosAdapter(
    private var todos: List<TodoUI>,
    private val listeners: TodosClicksListener,
) : RecyclerView.Adapter<TodosAdapter.HabitViewHolder>() {

    fun setItems(newItems: List<TodoUI>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(TodosDiffUtil(todos, newItems, ::areContentsTheSame))
        todos = newItems
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun areContentsTheSame(oldItem: TodoUI, newItem: TodoUI): Boolean {
        return oldItem == newItem
    }

    override fun getItemCount(): Int = todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_todo,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.binding.run {
            item = todos[position]
            listener = listeners
        }
    }

    inner class HabitViewHolder(val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root)
}
