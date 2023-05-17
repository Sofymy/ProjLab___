package com.bme.projlab.ui.items

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.bme.projlab.ui.screens.topnavscreen.SettingsAccountScreen
import com.bme.projlab.ui.screens.topnavscreen.SettingsNotificationsScreen

typealias ComposableFun = @Composable () -> Unit

sealed class SettingsTabItems(var icon: ImageVector, var title: String, var screen: ComposableFun) {
    object SettingsNotifications : SettingsTabItems(Icons.Default.Notifications, "Notifications", { SettingsNotificationsScreen() })
    object SettingsAccount : SettingsTabItems(Icons.Default.ManageAccounts, "Account", { SettingsAccountScreen() })
}