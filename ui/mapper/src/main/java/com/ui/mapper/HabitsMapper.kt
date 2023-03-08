package com.ui.mapper

import com.domain.model.HabitModel
import com.ui.uistate.HabitUIState

fun HabitModel.toHabitUIState(): HabitUIState {
    return HabitUIState(
        id = id,
        name = name,
        type = type,
        isCompleted = isCompleted,
    )
}

fun HabitUIState.toHabitModel(): HabitModel {
    return HabitModel(
        id = id,
        name = name,
        type = type,
        isCompleted = isCompleted,
    )
}