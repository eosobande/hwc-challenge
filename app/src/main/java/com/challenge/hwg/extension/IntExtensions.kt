package com.challenge.hwg.extension

/**
 * Property ratings range from 0 .. 100 but needs to be formatted on a 0 .. 10 scale for the UI
 */
val Int.rating: String
    get() = div(10.0).toString()

/**
 * compose LinearProgressIndicator progress scale is between 1 and 0
 * Convert our 0 .. 100 range value to 0 .. 1
 */
val Int.progress: Float
    get() = div(100f)