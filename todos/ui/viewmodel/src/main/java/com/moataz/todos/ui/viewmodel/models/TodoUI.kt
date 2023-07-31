package com.moataz.todos.ui.viewmodel.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoUI(
    val id: Long = 0,
    val title: String = "",
    val isCompleted: Boolean = false,
) : Parcelable
