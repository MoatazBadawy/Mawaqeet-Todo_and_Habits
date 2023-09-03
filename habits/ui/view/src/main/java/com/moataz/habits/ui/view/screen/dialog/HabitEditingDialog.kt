package com.moataz.habits.ui.view.screen.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.moataz.habits.ui.view.R
import com.moataz.habits.ui.view.databinding.FragmentHabitDialogEditingBinding
import com.moataz.habits.ui.view.utils.DialogConstants.DIALOG_WIDTH_PERCENT
import com.moataz.habits.ui.view.utils.setWidthPercent
import com.moataz.habits.ui.viewmodel.HabitEditingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HabitEditingDialog : DialogFragment() {
    private val viewModel: HabitEditingViewModel by viewModels()
    private lateinit var binding: FragmentHabitDialogEditingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_habit_dialog_editing,
            container,
            false,
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeDialog()
        observeCancelClickEvent()
    }

    private fun initializeDialog() {
        setWidthPercent(DIALOG_WIDTH_PERCENT)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.isCancelable = false
    }

    private fun observeCancelClickEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isCancelClicked.collect { clicked ->
                    if (clicked) dismiss()
                }
            }
        }
    }
}
