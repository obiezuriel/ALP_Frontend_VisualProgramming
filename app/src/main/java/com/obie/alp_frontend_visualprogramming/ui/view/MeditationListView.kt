package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.model.MeditationWrapper
import com.obie.alp_frontend_visualprogramming.ui.uistate.MeditationUIState
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.MeditationViewModel

@Composable
fun MeditationListView(viewModel: MeditationViewModel, navController: NavHostController){
    val state = viewModel.meditationState

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.meditationbg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        when(state){
            is MeditationUIState.Start, is MeditationUIState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is MeditationUIState.Success -> {
                val data = (state as MeditationUIState.Success).meditations
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                ) {
                    Text(
                        text = "Meditation",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Let your mind settle under gentle starlight.",
                        color = Color(0xFFE0E0E0),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(0.dp)
                    ){
                        items(data){ item ->
                            MeditationCard(item){ id ->
                                navController.navigate("MeditationDetail/$id")
                            }
                        }
                    }
                }
            }
            is MeditationUIState.Error -> {
                Text(text = (state as MeditationUIState.Error).message, modifier = Modifier.align(Alignment.Center), color = Color.White)
            }
        }
    }
}

@Composable
fun MeditationCard(item: MeditationWrapper.MeditationData, onClick: (Int) -> Unit){
    Column(
        modifier = Modifier
            .clickable { onClick(item.id) }
    ) {
        Image(
            painter = painterResource(R.drawable.cardmusic),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = item.title,
            color = Color.White,
            fontSize = 13.sp,
            fontFamily = FontFamily(Font(R.font.helveticaneuebold))
        )
        Text(
            text = "${item.duration} MIN",
            color = Color(0xFFFDDE8F),
            fontSize = 11.sp,
            fontFamily = FontFamily(Font(R.font.helveticaneuebold))
        )
    }
}
