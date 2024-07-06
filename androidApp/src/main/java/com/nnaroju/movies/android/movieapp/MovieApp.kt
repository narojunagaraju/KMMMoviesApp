package com.nnaroju.movies.android.movieapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nnaroju.movies.android.common.Details
import com.nnaroju.movies.android.common.Home
import com.nnaroju.movies.android.common.MovieAppBar
import com.nnaroju.movies.android.common.movieDestinations
import com.nnaroju.movies.android.details.DetailsScreen
import com.nnaroju.movies.android.details.DetailsViewModel
import com.nnaroju.movies.android.home.HomeScreen
import com.nnaroju.movies.android.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val snackBarHostState = remember { SnackbarHostState() }

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        Color.Transparent
    }

    SideEffect {
        systemUiController.setSystemBarsColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = Home.routeWithArgs
        ) {
            composable(Home.routeWithArgs) {
                val homeViewModel: HomeViewModel = koinViewModel()
                HomeScreen(
                    uiState = homeViewModel.uiState,
                    loadNextMovies = {
                        homeViewModel.loadMovies(forceReload = it)
                    },
                    navigateToDetail = {
                        navController.navigate("${Details.route}/${it.id}")
                    }
                )
            }

            composable(
                Details.routeWithArgs
            ) { navBackStackEntry ->
                val movieId = navBackStackEntry.arguments?.getString("movieId") ?: "0"
                val detailsViewModel: DetailsViewModel = koinViewModel(
                    parameters = { parametersOf(movieId.toInt()) }
                )
                DetailsScreen(detailsScreenState = detailsViewModel.uiState)
            }
        }
    }

}