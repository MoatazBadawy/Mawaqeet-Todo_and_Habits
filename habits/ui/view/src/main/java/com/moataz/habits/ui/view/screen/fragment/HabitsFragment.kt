package com.moataz.habits.ui.view.screen.fragment

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
import com.moataz.habits.ui.view.R
import com.moataz.habits.ui.view.adapter.HabitsAdapter
import com.moataz.habits.ui.view.databinding.FragmentHabitBinding
import com.moataz.habits.ui.viewmodel.HabitsViewModel
import com.moataz.habits.ui.viewmodel.models.HabitUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HabitsFragment : Fragment() {
    private val viewModel: HabitsViewModel by viewModels()
    private lateinit var habitsAdapter: HabitsAdapter
    private lateinit var binding: FragmentHabitBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_habit,
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
        observeEvents()
    }

    private fun initRecyclerView() {
        habitsAdapter = HabitsAdapter(emptyList(), viewModel)
        binding.habitsRecyclerView.adapter = habitsAdapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            viewModel.habitsUIState.collect { habitsMainState ->
                habitsAdapter.setItems(habitsMainState.habits)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addHabitClickedEvent.collect {
                    if (it) {
                        navigateToAddHabitDialog()
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.editHabitLongClickedEvent.collect {
                    navigateToHabitEditingDialog(it)
                }
            }
        }
    }

    private fun navigateToAddHabitDialog() {
        findNavController().navigate(
            R.id.action_habits_fragment_to_habitAdditionDialog,
        )
    }

    private fun navigateToHabitEditingDialog(habitUI: HabitUI) {
        findNavController().navigate(
            HabitsFragmentDirections.actionHabitsFragmentToHabitEditingDialog(
                habitUI,
            ),
        )
    }
}
