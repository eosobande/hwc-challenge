package com.challenge.hwg.ui.component

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(text: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(LocalContext.current, text, length).show()
}