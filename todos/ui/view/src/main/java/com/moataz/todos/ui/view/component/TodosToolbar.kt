package com.moataz.todos.ui.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.theme.Black
import com.moataz.todos.ui.view.theme.GrayLight
import com.moataz.todos.ui.view.theme.WhiteLight

@Composable
fun TodosToolbar(
    title: String,
    onAddTodoClickedIcon: () -> Unit,
) {
    TopAppBar(
        backgroundColor = WhiteLight,
        elevation = 0.dp,
        modifier = Modifier.height(60.dp),
        title = {
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = TodoFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = Black,
                ),
            )
        },
        actions = {
            Box(modifier = Modifier.padding(end = 16.dp)) {
                IconButton(
                    onClick = onAddTodoClickedIcon,
                    modifier = Modifier
                        .background(GrayLight, CircleShape)
                        .size(34.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add new todo",
                    )
                }
            }
        },
    )
}
