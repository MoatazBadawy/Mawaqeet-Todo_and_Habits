package com.domain.mapper

import com.core.database.entity.HabitEntity
import com.domain.model.HabitModel

fun HabitEntity.toHabitModel(): HabitModel {
    return HabitModel(
        id = id,
        name = name,
        type = type,

        isCompleted = isCompleted,
    )
}

fun HabitModel.toHabitEntity(): HabitEntity {
    return HabitEntity(
        id = id,
        name = name,
        type = type,
        isCompleted = isCompleted
    )
}