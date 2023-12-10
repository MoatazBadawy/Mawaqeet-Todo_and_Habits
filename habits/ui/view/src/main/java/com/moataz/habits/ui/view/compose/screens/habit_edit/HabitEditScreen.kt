package com.moataz.habits.ui.view.compose.screens.habit_edit

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
import com.moataz.habits.ui.view.compose.theme.Black
import com.moataz.habits.ui.view.compose.theme.Red
import com.moataz.habits.ui.viewmodel.HabitEditingViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HabitEditScreen(
    viewModel: HabitEditingViewModel = hiltViewModel(),
    onDismiss: () -> Unit,
) {
    val habitTitle by viewModel.habitTitle.collectAsState()
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
                    text = stringResource(id = R.string.edit_habit),
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

                OutlinedTextField(
                    value = habitTitle,
                    onValueChange = { viewModel.habitTitle.value = it },
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
                        if (viewModel.habitTitle.value.isNotEmpty()) {
                            IconButton(
                                onClick = { viewModel.habitTitle.value = "" },
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
                    Row {
                        TextButton(
                            onClick = {
                                viewModel.onUpdateHabitClicked()
                                onDismiss()
                            },
                            enabled = habitTitle.isNotEmpty()
                        ) {
                            Text(stringResource(id = R.string.update_habit))
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

                    TextButton(onClick = {
                        viewModel.onDeleteHabitClicked()
                        onDismiss()
                    }) {
                        Text(
                            stringResource(id = R.string.delete_habit),
                            color = Red
                        )
                    }
                }
            }
        }
    }
}
