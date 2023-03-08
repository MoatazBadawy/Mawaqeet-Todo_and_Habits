package com.ui.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ui.uistate.HabitUIState

class HabitsDiffUtil(
    private val oldList: List<HabitUIState>,
    private val newList: List<HabitUIState>,
    private val areContentsTheSame: (HabitUIState, HabitUIState) -> Boolean
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