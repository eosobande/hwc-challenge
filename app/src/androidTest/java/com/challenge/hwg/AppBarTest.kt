package com.challenge.hwg

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.challenge.hwg.ui.component.AppBar
import com.challenge.hwg.ui.component.TestTags
import org.junit.Rule
import org.junit.Test

class AppBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun appBarIsDisplayed() {
        composeRule.setContent {
            AppBar(title = "", navigateUp = null)
        }
        composeRule.onNodeWithTag(TestTags.AppBar).assertIsDisplayed()
    }

    @Test
    fun titleIsDisplayedAndTextIsCorrect() {
        composeRule.setContent {
            AppBar(title = "Limerick", navigateUp = null)
        }
        composeRule.onNodeWithTag(TestTags.PageTitle)
            .assertIsDisplayed()
            .assertTextEquals("Limerick")
    }

    @Test
    fun backButtonDoesNotExist() {
        composeRule.setContent {
            AppBar(title = "", navigateUp = null)
        }
        composeRule.onNodeWithTag(TestTags.BackButton).assertDoesNotExist()
    }

    @Test
    fun backButtonIsDisplayed() {
        composeRule.setContent {
            AppBar(title = "", navigateUp = {})
        }
        composeRule.onNodeWithTag(TestTags.BackButton).assertIsDisplayed()
    }

}