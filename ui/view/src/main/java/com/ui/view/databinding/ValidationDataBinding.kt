package com.ui.view.databinding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:isFieldValid", "app:errorMessage")
fun setErrorText(textInputLayout: TextInputLayout, isValid: Boolean, errorMessage: String) {
    textInputLayout.error = if (isValid) null else errorMessage
}

@BindingAdapter("app:showIfTrue")
fun showIfTrue(view: View, condition: Boolean) {
    view.isVisible = condition
}