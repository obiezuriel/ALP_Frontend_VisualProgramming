package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.model.MeditationSong


@Composable
fun MeditationCard(
    song: MeditationSong,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0x66FFFFFF))
            .clickable { onClick() }
            .padding(10.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.cardmusic),
            contentDescription = song.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            text = song.title,
            fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "${song.duration} MIN",
            fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
            fontSize = 12.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}
