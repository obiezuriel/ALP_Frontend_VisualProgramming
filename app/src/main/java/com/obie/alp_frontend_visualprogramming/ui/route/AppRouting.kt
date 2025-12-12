package com.obie.alp_frontend_visualprogramming.ui.route

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.model.BottomNavigationItem
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.AllJournalingViewModel

@Composable
fun AppRoute(){
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            title = "Affirmation",
            icon = R.drawable.affirmation_navbar
        ),
        BottomNavigationItem(
            title = "Journal",
            icon = R.drawable.journaling_navbar
        ),
        BottomNavigationItem(
            title = "Meditation",
            icon = R.drawable.meditation_navbar
        )
    )
    val allJournalingViewModel: AllJournalingViewModel = viewModel()
    val navController: NavHostController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    }

}