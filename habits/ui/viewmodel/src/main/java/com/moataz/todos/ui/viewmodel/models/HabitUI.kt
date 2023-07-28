package com.moataz.todos.ui.viewmodel.models

import android.os.Parcelable
import com.moataz.todos.ui.viewmodel.utils.HabitType
import kotlinx.parcelize.Parcelize

@Parcelize
data class HabitUI(
    val name: String = "",
    val type: String = HabitType.SPIRITUALITY.pathName,
    val isCompleted: Boolean = false,
) : Parcelable
