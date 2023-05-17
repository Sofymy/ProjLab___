package com.bme.projlab.ui.items


sealed class SearchDestinationTabItems(var title: String) {
    object SearchDestinationExact : SearchDestinationTabItems( "Exact")
    object SearchDestinationPreferences : SearchDestinationTabItems( "Preferences")
    object SearchDestinationRandom : SearchDestinationTabItems( "Random")
}