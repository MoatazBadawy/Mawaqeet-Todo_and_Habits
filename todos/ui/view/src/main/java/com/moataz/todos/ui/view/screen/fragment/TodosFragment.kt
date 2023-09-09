package com.moataz.todos.ui.view.screen.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.adapter.TodosAdapter
import com.moataz.todos.ui.view.databinding.FragmentTodosBinding
import com.moataz.todos.ui.viewmodel.TodosViewModel
import com.moataz.todos.ui.viewmodel.models.TodoUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TodosFragment : Fragment() {
    private val viewModel: TodosViewModel by viewModels()
    private lateinit var todosAdapter: TodosAdapter
    private lateinit var binding: FragmentTodosBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_todos,
            container,
            false,
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeTodoEvent()
        observeAddTodoEvent()
        observeEditTodoEvent()
    }

    private fun initRecyclerView() {
        todosAdapter = TodosAdapter(emptyList(), viewModel)
        binding.todosRecyclerView.adapter = todosAdapter
    }

    private fun observeTodoEvent() {
        lifecycleScope.launch {
            viewModel.todosUIState.collect { todosUIState ->
                todosAdapter.setTodos(todosUIState.todos)
            }
        }
    }

    private fun observeAddTodoEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addTodoClickedEvent.collect {
                    if (it) navigateToAddHabitDialog()
                }
            }
        }
    }

    private fun observeEditTodoEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editTodoLongClickedEvent.collect {
                    navigateToHabitEditingDialog(it)
                }
            }
        }
    }

    private fun navigateToAddHabitDialog() {
        findNavController().navigate(
            R.id.action_todos_fragment_to_todosAdditionDialog,
        )
    }

    private fun navigateToHabitEditingDialog(todoUI: TodoUI) {
        findNavController().navigate(
            TodosFragmentDirections.actionTodosFragmentToTodoEditingDialog(
                todoUI,
            ),
        )
    }
}
