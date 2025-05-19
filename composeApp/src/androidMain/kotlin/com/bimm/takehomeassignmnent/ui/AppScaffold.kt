package com.bimm.takehomeassignmnent.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bimm.takehomeassignmnent.presentation.SakeLocationsSharedViewModel
import com.bimm.takehomeassignmnent.ui.screens.SakeDetailsScreen
import com.bimm.takehomeassignmnent.ui.screens.SakesScreen
import com.bimm.takehomeassignmnent.ui.screens.Screens
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screens.SAKES.route,
        modifier = modifier,
    ) {
        composable(Screens.SAKES.route) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(Screens.SAKES.route)
            }
            val sakeLocationsSharedViewModel: SakeLocationsSharedViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            SakesScreen (
                sakeLocationsSharedViewModel = sakeLocationsSharedViewModel,
                onGotoSakeDetailsClick = { navController.navigate(Screens.SAKE_DETAILS.route) }
            )
        }
        composable(Screens.SAKE_DETAILS.route) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(Screens.SAKES.route)
            }
            val sakeLocationsSharedViewModel: SakeLocationsSharedViewModel = koinViewModel(viewModelStoreOwner = parentEntry)
            SakeDetailsScreen (
                sakeLocationsSharedViewModel = sakeLocationsSharedViewModel,
                onUpButtonClick = { navController.popBackStack() }
            )
        }
    }
}