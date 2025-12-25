package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.obie.alp_frontend_visualprogramming.ui.uistate.MoodUIState
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.FavoriteViewModel
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.MoodViewModel

@Composable
fun AffirmationView(
    moodViewModel: MoodViewModel,
    favoriteViewModel: FavoriteViewModel,
    navController: NavHostController
) {
    val userId = 1

    AffirmationContent(
        moodState = moodViewModel.moodUIState,
        onMoodSubmit = { moodType ->
            moodViewModel.submitMood(userId, moodType)
        },
        onBookmarkClick = {
            val currentState = moodViewModel.moodUIState
            if (currentState is MoodUIState.Success) {
                favoriteViewModel.createFavorite(userId, currentState.mood.affirmation_text)
            }
        },
        onNavigateToFavorites = {
            navController.navigate("Favorites")
        }
    )
}