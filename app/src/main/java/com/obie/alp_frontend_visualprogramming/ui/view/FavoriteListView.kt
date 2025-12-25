package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.FavoriteViewModel

@Composable
fun FavoriteListView(
    viewModel: FavoriteViewModel,
    navController: NavHostController
) {
    val userId = 1

    //load
    LaunchedEffect(Unit) {
        viewModel.getAllFavorites(userId)
    }

    FavoriteListContent(
        favoriteState = viewModel.allFavoriteUIState,
        onBackClick = {
            navController.popBackStack()
        },
        onFavoriteClick = { favorite ->
            // TODO: Navigate to detail/edit screen if needed
            // For now, just placeholder
        }
    )
}