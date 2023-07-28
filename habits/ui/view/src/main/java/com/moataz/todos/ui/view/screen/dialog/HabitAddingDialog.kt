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
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.databinding.FragmentHabitDialogAddingBinding
import com.moataz.todos.ui.view.utils.setWidthPercent
import com.moataz.todos.ui.viewmodel.HabitAddingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HabitAddingDialog : DialogFragment() {
    private val viewModel: HabitAddingViewModel by viewModels()
    private lateinit var binding: FragmentHabitDialogAddingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_habit_dialog_adding,
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
        observeEvents()
    }
}
