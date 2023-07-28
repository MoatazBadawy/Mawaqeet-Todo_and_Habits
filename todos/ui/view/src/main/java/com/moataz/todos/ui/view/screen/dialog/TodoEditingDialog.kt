package com.moataz.todos.ui.view.screen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.databinding.FragmentTodoDialogEditingBinding
import com.moataz.todos.ui.view.utils.setWidthPercent
import com.moataz.todos.ui.viewmodel.TodoEditingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoEditingDialog : DialogFragment() {
    private val viewModel: TodoEditingViewModel by viewModels()
    private lateinit var binding: FragmentTodoDialogEditingBinding
    private val argument: TodoEditingDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_todo_dialog_editing,
            container,
            false,
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.isCancelable = false
    }

    private fun initNavArgs() {
        viewModel.setupNavArgs(argument)
        viewModel.apply {
            todo.value = argument.todoUI
            todoTitle.value = todo.value.title
        }
    }

    private fun observeEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.isCancelClicked.collect {
                if (it) {
                    dismiss()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initNavArgs()
        observeEvents()
    }
}
