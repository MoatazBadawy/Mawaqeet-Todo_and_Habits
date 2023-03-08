package com.ui.uistate

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HabitUIState(
    val id : Long = 0,
    val name: String = "",
    val type: String = "",
    val isCompleted: Boolean = false,
) : Parcelable