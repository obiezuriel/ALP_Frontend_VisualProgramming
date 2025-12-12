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
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.JournalDetailViewModel


@Composable
fun JournalDetailView(
    journalId: Int,
    viewModel: JournalDetailViewModel
){
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
                        .clickable { /* Handle back navigation */ },
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
                Text(
                    text = "Title",
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                    color = Color(0xFF2C2C2C)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "THURSDAY, 4 Dec 2025",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                    color = Color(0xFF666666),
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                    color = Color(0xFF4A4A4A)
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun JournalDetailViewPreview(){
    JournalDetailView()
}
