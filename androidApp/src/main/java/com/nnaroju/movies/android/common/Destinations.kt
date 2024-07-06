package com.nnaroju.movies.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val title: String
    val route: String
    val routeWithArgs: String
}

object Home : Destination {
    override val title: String
        get() = "Movies"
    override val route: String
        get() = "home"
    override val routeWithArgs: String
        get() = route
}

object Details : Destination {
    override val title: String
        get() = "Movie Details"
    override val route: String
        get() = "details"
    override val routeWithArgs: String
        get() = "$route/{movieId}"

    val arguments = listOf(
        navArgument(name = "movieId") {
            type = NavType.IntType
        }
    )
}

val movieDestinations = listOf(Home, Details)