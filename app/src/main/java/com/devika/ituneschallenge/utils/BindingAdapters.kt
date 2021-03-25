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
    when (uiState) {
        is UiState.Progress -> progressBar.visibility = View.VISIBLE
        else -> progressBar.visibility = View.GONE
    }
}

@BindingAdapter(value = ["setVisibility"])
fun setVisibilityState(
    view: View,
    uiState: UiState?
) {
    when (uiState) {
        is UiState.Success -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
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

@BindingAdapter(value = ["setEmptyState"])
fun setEmptyState(
    textView: TextView,
    uiState: UiState?
) {
    uiState.let {
        when (uiState) {
            is UiState.Empty -> {
                textView.text = " No Artist Found! Please Search"
                textView.visibility = View.VISIBLE
            }
            else -> {
                textView.visibility = View.GONE
            }
        }
    }

}