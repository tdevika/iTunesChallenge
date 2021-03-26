package com.devika.ituneschallenge.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.devika.ituneschallenge.data.domain.UiState


@BindingAdapter(value = ["setProgressState"])
fun setProgressState(
    progressBar: ProgressBar,
    uiState: UiState?
) {
    setVisibility(uiState, progressBar)
}

@BindingAdapter(value = ["setVisibility"])
fun setVisibilityState(
    view: View,
    uiState: UiState?
) {
    setVisibility(uiState, view)
}

@BindingAdapter(value = ["setEmptyState"])
fun setEmptyState(
    textView: TextView,
    uiState: UiState?
) {
    uiState.let {
        setVisibility(uiState, textView)
    }

}

@BindingAdapter(value = ["setErrorState"])
fun setErrorState(
    textView: TextView,
    uiState: UiState?
) {
    uiState.let {
        when (uiState) {
            is UiState.Error -> {
                textView.text = uiState.message
                textView.visibility = View.VISIBLE
            }
            else -> textView.visibility = View.GONE
        }
    }
}

private fun setVisibility(
    uiState: UiState?,
    view: View
) {
    when (uiState) {
        is UiState.Progress -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}