package com.moataz.todos.ui.viewmodel

import com.moataz.todos.ui.viewmodel.models.TodoUI

interface TodosClicksListener {
    fun updateTodoCompleted(todo: TodoUI, isCompleted: Boolean)
    fun onEditTodoLongClicked(todo: TodoUI): Boolean
}
