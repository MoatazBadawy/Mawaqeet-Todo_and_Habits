package com.moataz.habits.ui.viewmodel.models

import android.os.Parcelable
import com.moataz.habits.ui.viewmodel.utils.HabitType
import kotlinx.parcelize.Parcelize

@Parcelize
data class HabitUI(
    val id: Long = 0,
    val name: String = "",
    val type: String = HabitType.SPIRITUALITY.pathName,
    val isCompleted: Boolean = false,
) : Parcelable
