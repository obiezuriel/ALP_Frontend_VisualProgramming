package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun MeditationDetailView(meditation: MeditationWrapper.MeditationData, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5E6F0))
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image Section with Back Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.detailmusic),
                    contentDescription = meditation.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                // Back Button
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

            // Content Section
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp)
            ) {
                // Title
                Text(
                    text = meditation.title,
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                    color = Color(0xFF2C2C2C)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Duration
                Text(
                    text = "${meditation.duration} MIN",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                    color = Color(0xFF666666),
                    letterSpacing = 0.5.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Description
                Text(
                    text = meditation.description,
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                    color = Color(0xFF4A4A4A)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Listening Count
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.Headphones,
                        contentDescription = "Listening",
                        tint = Color(0xFF9B9B9B),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "34,234 Listening",
                        color = Color(0xFF9B9B9B),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                    )
                }
            }

            // Play Button - Fixed at Bottom
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(vertical = 24.dp)
            ) {
                Button(
                    onClick = { navController.navigate("MeditationPlayer/${meditation.id}/${meditation.title}") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF332A86)),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        "PLAY",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                    )
                }
            }

        }

    }

}
