package com.ui.uistate

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class HabitUIState(
    val id : Long = 0,
    val name: String = "",
    val type: String = "",
    val isCompleted: Boolean = false,
) : Parcelable