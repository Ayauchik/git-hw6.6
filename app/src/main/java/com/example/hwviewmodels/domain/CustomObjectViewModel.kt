package com.example.hwviewmodels.domain

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CustomObjectViewModel : ViewModel() {
    private val _text = mutableStateOf("")
    val text: State<String> = _text

    fun onTextChange(newText: String){
        _text.value = newText
    }
}