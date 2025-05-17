package com.bimm.takehomeassignmnent.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bimm.takehomeassignmnent.ui.screens.SakeDetailsScreen
import com.bimm.takehomeassignmnent.ui.screens.SakesScreen
import com.bimm.takehomeassignmnent.ui.screens.Screens

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
            SakesScreen (
                onGotoSakeDetailsClick = { navController.navigate(Screens.SAKE_DETAILS.route) }
            )
        }
        composable(Screens.SAKE_DETAILS.route) {
            SakeDetailsScreen (
                onUpButtonClick = { navController.popBackStack() }
            )
        }
    }
}