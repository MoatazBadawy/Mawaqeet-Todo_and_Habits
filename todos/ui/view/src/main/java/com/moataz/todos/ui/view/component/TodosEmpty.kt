package com.moataz.todos.ui.view.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moataz.todos.ui.view.R
import com.moataz.todos.ui.view.theme.Black

@Composable
fun TodosEmpty() {
    Text(
        text = stringResource(id = R.string.no_todos),
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.almarai_light)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Black,
            textDecoration = TextDecoration.None,
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
        textAlign = TextAlign.Center,
    )
}
