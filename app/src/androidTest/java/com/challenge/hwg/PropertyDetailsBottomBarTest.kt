package com.challenge.hwg

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.challenge.hwg.presentation.PropertyDetailsState
import com.challenge.hwg.presentation.PropertyDetailsState.Converting
import com.challenge.hwg.presentation.PropertyDetailsState.Error
import com.challenge.hwg.presentation.PropertyDetailsState.Loaded
import com.challenge.hwg.ui.component.AppBar
import com.challenge.hwg.ui.component.PropertyDetailsBottomBar
import com.challenge.hwg.ui.component.TestTags
import com.challenge.model.Price
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PropertyDetailsBottomBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun priceValueDisplayed_AndIsCorrectOnConverting() {
        val price = Price("50.99")
        composeRule.setContent {
            PropertyDetailsBottomBar(uiState = Converting(price)) {}
        }
        composeRule.onNodeWithTag(TestTags.PriceValue)
            .assertIsDisplayed()
            .assertTextEquals("$price")
    }

    @Test
    fun priceValueDisplayed_AndIsCorrectOnError() {
        val price = Price("50.99")
        composeRule.setContent {
            PropertyDetailsBottomBar(uiState = Error("", price)) {}
        }
        composeRule.onNodeWithTag(TestTags.PriceValue)
            .assertIsDisplayed()
            .assertTextEquals("$price")
    }

    @Test
    fun priceValueDisplayed_AndIsCorrectOnLoaded() {
        val price = Price("50.99")
        composeRule.setContent {
            PropertyDetailsBottomBar(uiState = Loaded(price)) {}
        }
        composeRule.onNodeWithTag(TestTags.PriceValue)
            .assertIsDisplayed()
            .assertTextEquals("$price")
    }

    @Test
    fun spinnerDisplayedOnConvertingState_AndCurrenciesAreNotDisplayed() {
        composeRule.setContent {
            PropertyDetailsBottomBar(uiState = Converting(Price(""))) {}
        }
        composeRule.onNodeWithTag(TestTags.ProgressSpinner).assertIsDisplayed()
        Price.Currency.entries.forEach {
            composeRule.onNodeWithTag(it.name).assertDoesNotExist()
        }
    }

    @Test
    fun spinnerDoesNotExistOnLoadedState_AndCurrenciesAreDisplayed() {
        composeRule.setContent {
            PropertyDetailsBottomBar(uiState = Loaded(Price(""))) {}
        }
        composeRule.onNodeWithTag(TestTags.ProgressSpinner).assertDoesNotExist()
        Price.Currency.entries.forEach {
            composeRule.onNodeWithTag(it.name)
                .assertIsDisplayed()
                .assertTextEquals(it.name)
        }
    }

    @Test
    fun spinnerDoesNotExistOnErrorState_AndCurrenciesAreDisplayed() {
        composeRule.setContent {
            PropertyDetailsBottomBar(uiState = Error("", Price(""))) {}
        }
        composeRule.onNodeWithTag(TestTags.ProgressSpinner).assertDoesNotExist()
        Price.Currency.entries.forEach {
            composeRule.onNodeWithTag(it.name)
                .assertIsDisplayed()
                .assertTextEquals(it.name)
        }
    }

    @Test
    fun onlySelectedCurrencyIsDisabledOnLoadedState() {
        val price = Price("")
        composeRule.setContent {
            PropertyDetailsBottomBar(uiState = Loaded(price)) {}
        }
        composeRule.onNodeWithTag(price.currency.name)
            .assertIsDisplayed()
            .assertIsNotEnabled()
        Price.Currency.entries.forEach {
            if (it != price.currency) {
                composeRule.onNodeWithTag(Price.Currency.USD.name)
                    .assertIsDisplayed()
                    .assertIsEnabled()
            }
        }
    }

    @Test
    fun onlySelectedCurrencyIsDisabledOnErrorState() {
        val price = Price("", Price.Currency.GBP)
        composeRule.setContent {
            PropertyDetailsBottomBar(
                uiState = Error("", price)
            ) {}
        }
        composeRule.onNodeWithTag(price.currency.name)
            .assertIsDisplayed()
            .assertIsNotEnabled()
        Price.Currency.entries.forEach {
            if (it != price.currency) {
                composeRule.onNodeWithTag(Price.Currency.USD.name)
                    .assertIsDisplayed()
                    .assertIsEnabled()
            }
        }
    }

}