package com.moataz.todos.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.moataz.habits.ui.view.R
import com.moataz.habits.ui.view.databinding.ItemHabitBinding
import com.moataz.todos.ui.viewmodel.HabitsClicksListener
import com.moataz.todos.ui.viewmodel.models.HabitUI

class HabitsAdapter(
    private var habits: List<HabitUI>,
    private val listeners: HabitsClicksListener,
) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    fun setItems(newItems: List<HabitUI>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(HabitsDiffUtil(habits, newItems, ::areContentsTheSame))
        habits = newItems
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun areContentsTheSame(oldItem: HabitUI, newItem: HabitUI): Boolean {
        return oldItem == newItem
    }

    override fun getItemCount(): Int = habits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_habit,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.binding.run {
            item = habits[position]
            listener = listeners
        }
    }

    inner class HabitViewHolder(val binding: ItemHabitBinding) :
        RecyclerView.ViewHolder(binding.root)
}
