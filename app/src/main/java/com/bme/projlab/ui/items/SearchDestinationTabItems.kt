package com.bme.projlab.ui.items


sealed class SearchDestinationTabItems(var title: String) {
    object SearchDestinationExact : SearchDestinationTabItems( "Yes, of course!")
    object SearchDestinationPreferences : SearchDestinationTabItems( "No, surprise me!")
}