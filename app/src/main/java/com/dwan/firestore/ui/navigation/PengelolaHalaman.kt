package com.dwan.firestore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.dwan.firestore.ui.pages.HomeScreen
import androidx.navigation.compose.rememberNavController
import com.dwan.firestore.ui.pages.InsertMhsView

@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    // NavController untuk mengatur navigasi, dengan default value dari remember
    navController: NavHostController = rememberNavController()
) {
    // NavHost sebagai container untuk navigasi
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route, // Rute awal/pertama yang ditampilkan
        modifier = modifier
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
            )
        }
        composable(DestinasiInsert.route) {
            InsertMhsView(
                onBack = { navController.popBackStack() },
                onNavigate = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
    }
}