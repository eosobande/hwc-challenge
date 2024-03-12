package com.challenge.hwg

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.challenge.hwg.ui.component.AppBar
import com.challenge.hwg.ui.component.FeaturedLabel
import com.challenge.hwg.ui.component.TestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FeaturedLabelTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun labelIsDisplayed_AndTextIsCorrect() {
        var text = "house"
        composeRule.setContent {
            FeaturedLabel(type = text)
            text = stringResource(id = R.string.featured, text)
        }

        composeRule.onNodeWithTag(TestTags.FeaturedLabel)
            .assertIsDisplayed()
            .assertTextEquals(text.uppercase())
    }

}