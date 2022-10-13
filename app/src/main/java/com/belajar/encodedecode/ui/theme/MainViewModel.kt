package com.belajar.encodedecode.ui.theme

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*

class MainViewModel : ViewModel() {

    var userInput by mutableStateOf("")
    var encodeHistory:MutableList<String> = mutableListOf()
    var decodeHistory:MutableList<String> = mutableListOf()

    fun updateUserInput(update:String) {
        userInput = update
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun encodeText(userInput:String):String {
        return Base64.getEncoder().encodeToString(userInput.toByteArray())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decodeText(encoded:String):String {
        val decoded = Base64.getDecoder().decode(encoded)
        return String(decoded)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun submitProccess() {
        encodeHistory.add(encodeText(userInput))
        decodeHistory.add(decodeText(encodeText(userInput)))
        userInput = ""

    }

}