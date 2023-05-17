package com.bme.projlab.ui.items

sealed class SearchDatesTabItems(var title: String) {
    object SearchDatesExact : SearchDatesTabItems( "Exact")
    object SearchDatesPreferences : SearchDatesTabItems( "Preferences")
    object SearchDatesRandom : SearchDatesTabItems( "Random")
}