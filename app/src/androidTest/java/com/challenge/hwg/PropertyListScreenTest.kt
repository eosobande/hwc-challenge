package com.challenge.hwg

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.challenge.hwg.ui.component.AppBar
import com.challenge.hwg.ui.component.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PropertyListScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun appBarIsDisplayed() {
        composeRule.onNodeWithTag(TestTags.AppBar).assertIsDisplayed()
    }

    @Test
    fun backButtonDoesNotExist() {
        composeRule.onNodeWithTag(TestTags.BackButton).assertDoesNotExist()
    }

}