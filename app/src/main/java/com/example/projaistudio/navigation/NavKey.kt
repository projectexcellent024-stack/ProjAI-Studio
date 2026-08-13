package com.example.projaistudio.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavKey {
    @Serializable
    data object Dashboard : NavKey

    @Serializable
    data object StudioWorkspace : NavKey
}
