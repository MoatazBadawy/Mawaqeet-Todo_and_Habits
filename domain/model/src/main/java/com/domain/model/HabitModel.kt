package com.domain.model


data class HabitModel(
    val id: Long,
    val name: String,
    val type: String,
    val isCompleted: Boolean
)