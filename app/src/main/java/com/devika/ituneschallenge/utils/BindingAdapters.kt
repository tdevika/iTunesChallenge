package com.devika.ituneschallenge.utils

import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.devika.ituneschallenge.data.domain.UiState


@BindingAdapter(value = ["setProgressState"])
fun setProgressState(
    progressBar: ProgressBar,
    uiState: UiState?
){
   when(uiState){
       is UiState.Progress-> progressBar.isVisible = true
       else-> progressBar.isVisible= false
   }
}

@BindingAdapter(value = ["setErrorState"])
fun setErrorState(
    textView: TextView,
    uiState: UiState?
){
    uiState.let {
        when(uiState){
            is UiState.Error-> {
                textView.text = uiState.message
                textView.isVisible = true}
            else -> textView.isVisible=false
        }
    }
}