package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.uistate.JournalDetailUIState
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.JournalDetailViewModel


@Composable
fun JournalDetailView(
    journalId: Int,
    viewModel: JournalDetailViewModel,
    navController: NavHostController
){
    LaunchedEffect(journalId) {
        viewModel.getJournalById(journalId)
    }

    val dataStatus = viewModel.journalDetailStatus

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6F0))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background_journal_detail),
                    contentDescription = "Background",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 40.dp)
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF332A86))
                        .border(3.dp, Color(0xFFFFE08F), CircleShape)
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            ) {
                when(dataStatus){
                    is JournalDetailUIState.Start -> {

                    }

                    is JournalDetailUIState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(50.dp),
                                color = Color(0xFF332A86),
                                strokeWidth = 4.dp
                            )
                        }
                    }

                    is JournalDetailUIState.Success -> {
                        Text(
                            text = dataStatus.journal.title,
                            fontSize = 32.sp,
                            fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                            color = Color(0xFF2C2C2C)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = dataStatus.journal.date,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                            color = Color(0xFF666666),
                            letterSpacing = 0.5.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = dataStatus.journal.content,
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                            color = Color(0xFF4A4A4A)
                        )
                    }

                    is JournalDetailUIState.Error -> {

                    }

                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//private fun JournalDetailViewPreview(){
//    JournalDetailView()
//}
