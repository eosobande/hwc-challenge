package com.challenge.hwg.presentation

import com.challenge.model.Property

sealed interface PropertyListState {

    /**
     * Fetching properties list
     */
    data object Loading : PropertyListState

    /**
     * Request error, no data to be displayed
     */
    data class Error(val message: String) : PropertyListState

    /**
     * Properties data retrieved
     */
    data class Success(
        val properties: List<Property>,
        val city: String
    ) : PropertyListState

}