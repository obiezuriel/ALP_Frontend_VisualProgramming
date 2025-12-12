package com.obie.alp_frontend_visualprogramming.ui.route

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.model.BottomNavigationItem
import com.obie.alp_frontend_visualprogramming.ui.view.AllJournalingView
import com.obie.alp_frontend_visualprogramming.ui.view.CreateJournalView
import com.obie.alp_frontend_visualprogramming.ui.view.JournalDetailView
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.AllJournalingViewModel
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.JournalDetailViewModel

@Composable
fun AppRouting(){
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
    val journalDetailViewModel: JournalDetailViewModel = viewModel()
    val navController: NavHostController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {
        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF332A86)) {
                    bottomNavigationItems.forEachIndexed {index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.title){
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Image(
                                    painter = painterResource(item.icon),
                                    contentDescription = "Icon Navbar"
                                )
                            }
                        )
                    }
                }
            }) { innerPadding ->
            NavHost(navController = navController, startDestination = "Journal"){
                composable("Journal"){
                    AllJournalingView(allJournalingViewModel, navController)
                }

                composable("Journal/{journalId}"){
                    backStackEntry ->
                    JournalDetailView(journalId = backStackEntry.arguments?.getString("journalId")?.toIntOrNull() ?: 0, viewModel = journalDetailViewModel)
                }

                composable("CreateJournal"){
                    CreateJournalView()
                }
            }

        }

    }

}