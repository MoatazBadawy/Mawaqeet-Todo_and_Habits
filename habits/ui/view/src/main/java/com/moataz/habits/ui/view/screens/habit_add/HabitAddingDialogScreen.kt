package com.moataz.habits.ui.view.screens.habit_add

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.moataz.habits.ui.view.R
import com.moataz.habits.ui.view.component.HabitTypeChip
import com.moataz.habits.ui.view.theme.Black
import com.moataz.habits.ui.viewmodel.HabitAddingViewModel
import com.moataz.habits.ui.viewmodel.utils.HabitType

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HabitAddingDialogScreen(
    viewModel: HabitAddingViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val habitTitle by viewModel.habitName.collectAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottei_plant_growing))

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Surface(shape = MaterialTheme.shapes.medium.copy(all = CornerSize(16.dp))) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.new_habit),
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.h6
                )
                Card(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(MaterialTheme.shapes.medium),
                    elevation = 8.dp
                ) {
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier.fillMaxSize(),
                        iterations = LottieConstants.IterateForever
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                HabitTypeChips(viewModel = viewModel)

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = habitTitle,
                    onValueChange = { viewModel.habitName.value = it },
                    label = { Text(stringResource(id = R.string.habit_name)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Black,
                        disabledBorderColor = Black,
                        cursorColor = Black,
                        textColor = Black,
                        focusedLabelColor = Black,
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    ),
                    trailingIcon = {
                        if (viewModel.habitName.value.isNotEmpty()) {
                            IconButton(
                                onClick = { viewModel.habitName.value = "" },
                                modifier = Modifier
                                    .clip(MaterialTheme.shapes.small)
                                    .size(18.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = {
                            viewModel.onAddHabitClicked()
                            onDismiss()
                        },
                        enabled = habitTitle.isNotEmpty()
                    ) {
                        Text(stringResource(id = R.string.add))
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    TextButton(
                        onClick = {
                            onDismiss()
                        },
                    ) {
                        Text(
                            stringResource(id = R.string.cancel),
                            color = Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HabitTypeChips(viewModel: HabitAddingViewModel) {
    val currentHabitType by viewModel.habitType.collectAsState()

    Spacer(modifier = Modifier.padding(top = 4.dp))
    LazyRow(modifier = Modifier.padding(horizontal = 8.dp)) {
        items(HabitType.values().toList()) { habitType ->
            HabitTypeChip(
                text = habitType.displayName,
                checked = habitType == currentHabitType,
                onCheckedChange = { if (it) viewModel.onChipChooseHabitType(habitType) }
            )
        }
    }
}
