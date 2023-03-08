package com.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ui.uistate.HabitUIState
import com.ui.view.R
import com.ui.view.databinding.ItemHabitBinding
import com.ui.viewmodel.HabitsClicksListener

class HabitsAdapter(
    private var habits: List<HabitUIState>, private val listeners: HabitsClicksListener
) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {

    fun setItems(newItems: List<HabitUIState>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(HabitsDiffUtil(habits, newItems, ::areContentsTheSame))
        habits = newItems
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun areContentsTheSame(oldItem: HabitUIState, newItem: HabitUIState): Boolean {
        return oldItem == newItem
    }

    override fun getItemCount(): Int = habits.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        return HabitViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_habit, parent, false
            )
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