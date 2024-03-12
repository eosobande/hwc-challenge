package com.challenge.hwg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HWGCodeChallengeTheme {
                HWGCodeChallengeApp()
            }
        }
    }
}
