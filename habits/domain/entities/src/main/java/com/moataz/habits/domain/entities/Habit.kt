package com.moataz.habits.domain.entities

data class Habit(
    val id: Long,
    val name: String,
    val type: String,
    val isCompleted: Boolean,
)
