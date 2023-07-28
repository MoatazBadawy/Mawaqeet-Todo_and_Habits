package com.moataz.habits.ui.view.databinding

import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["app:changeTextStatue"])
fun changeTextStatue(textView: TextView, isCompleted: Boolean) {
    if (isCompleted) {
        textView.paintFlags = textView.paintFlags or android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
    } else {
        textView.paintFlags =
            textView.paintFlags and android.graphics.Paint.STRIKE_THRU_TEXT_FLAG.inv()
    }
}

@BindingAdapter(value = ["app:changeCheckBoxStatue"])
fun changeCheckBoxStatue(checkBox: CheckBox, isCompleted: Boolean) {
    checkBox.isChecked = isCompleted == true
}
