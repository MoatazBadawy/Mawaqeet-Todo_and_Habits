package com.moataz.habits.ui.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.moataz.habits.ui.viewmodel.models.HabitUI

class HabitsDiffUtil(
    private val oldList: List<HabitUI>,
    private val newList: List<HabitUI>,
    private val areContentsTheSame: (HabitUI, HabitUI) -> Boolean,
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
