package com.challenge.hwg.ui

import androidx.navigation.NavHostController

const val PropertiesRoute = "properties"
const val PropertyDetailsRoute = "properties/{index}"

/**
 * Neat method to navigate to Details screen with required param
 * @param index index of selected property in properties list
 */
fun NavHostController.navigateToPropertyDetails(index: Int) =
    navigate(PropertyDetailsRoute.replace("{index}", "$index"))

