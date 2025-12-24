package com.obie.alp_frontend_visualprogramming.ui.view

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import android.util.Log

@Composable
fun MeditationPlayerView(meditationId: Int, navController: NavHostController, songTitle: String = "Now Playing"){
    val audioUrl = "http://10.0.2.2:3000/api/meditations/$meditationId/stream"
    var player: MediaPlayer? by remember { mutableStateOf(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0) }
    var duration by remember { mutableStateOf(0) }
    var errorMessage by remember { mutableStateOf("") }

    DisposableEffect(audioUrl){
        player = MediaPlayer().apply {
            try {
                setDataSource(audioUrl)
                setOnPreparedListener {
                    duration = this.duration
                    // Auto-play when audio is ready
                    this.start()
                    isPlaying = true
                    Log.d("MeditationPlayer", "Audio prepared and auto-playing. Duration: $duration ms")
                }
                setOnErrorListener { mp, what, extra ->
                    Log.e("MeditationPlayer", "Error: what=$what, extra=$extra")
                    errorMessage = "Error loading audio"
                    false
                }
                prepareAsync()
                Log.d("MeditationPlayer", "Loading: $audioUrl")
            } catch (e: Exception) {
                Log.e("MeditationPlayer", "Exception: ${e.message}", e)
                errorMessage = e.message ?: "Unknown error"
            }
        }

        onDispose {
            player?.stop()
            player?.release()
            player = null
        }
    }

    // Update position
    LaunchedEffect(isPlaying) {
        while (isPlaying) {
            currentPosition = player?.currentPosition ?: 0
            kotlinx.coroutines.delay(100)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.playsongbg),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.2f))
        ) {
            // Back Button
            Box(
                modifier = Modifier
                    .padding(start = 24.dp, top = 40.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF332A86).copy(alpha = 0.8f))
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

            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // Title (Song Name)
                Text(
                    text = songTitle,
                    color = Color.White,
                    fontSize = 32.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Subtitle (Duration text)
                Text(
                    text = "Soothing Mindful Melodies",
                    color = Color(0xFFE0E0E0),
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(80.dp))

                // Play/Pause + Skip Controls
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Rewind Button (5 seconds backward)
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF332A86).copy(alpha = 0.6f))
                            .clickable {
                                try {
                                    val newPosition = (currentPosition - 5000).coerceAtLeast(0)
                                    player?.seekTo(newPosition)
                                    currentPosition = newPosition
                                    Log.d("MeditationPlayer", "Rewind to: $newPosition ms")
                                } catch (e: Exception) {
                                    Log.e("MeditationPlayer", "Rewind error: ${e.message}")
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FastRewind,
                            contentDescription = "Rewind 5s",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(60.dp))

                    // Play/Pause Button
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF332A86))
                            .clickable {
                                try {
                                    if (isPlaying) {
                                        player?.pause()
                                        Log.d("MeditationPlayer", "Paused")
                                    } else {
                                        player?.start()
                                        Log.d("MeditationPlayer", "Started playing")
                                    }
                                    isPlaying = !isPlaying
                                } catch (e: Exception) {
                                    Log.e("MeditationPlayer", "Play error: ${e.message}")
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = Color.White,
                            modifier = Modifier.size(50.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(60.dp))

                    // Forward Button (5 seconds forward)
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF332A86).copy(alpha = 0.6f))
                            .clickable {
                                try {
                                    val newPosition = (currentPosition + 5000).coerceAtMost(duration)
                                    player?.seekTo(newPosition)
                                    currentPosition = newPosition
                                    Log.d("MeditationPlayer", "Forward to: $newPosition ms")
                                } catch (e: Exception) {
                                    Log.e("MeditationPlayer", "Forward error: ${e.message}")
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FastForward,
                            contentDescription = "Forward 5s",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))

                // Time Display
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = formatTime(currentPosition),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                    )

                    // Progress indicator (simple line)
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(2.dp)
                            .background(Color(0xFFFDDE8F).copy(alpha = 0.3f))
                            .padding(horizontal = 8.dp)
                    )

                    Text(
                        text = formatTime(duration),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                // Error Message
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = "Error: $errorMessage",
                        color = Color(0xFFFF6B6B),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                    )
                }
            }
        }
    }
}

fun formatTime(milliseconds: Int): String {
    val totalSeconds = milliseconds / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}
