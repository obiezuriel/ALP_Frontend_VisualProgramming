package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.model.MoodData
import com.obie.alp_frontend_visualprogramming.ui.uistate.MoodUIState

@Composable
fun AffirmationContent(
    moodState: MoodUIState,
    onMoodSubmit: (String) -> Unit,
    onBookmarkClick: () -> Unit,
    onNavigateToFavorites: () -> Unit
) {
    var triggerIconAnimation by remember { mutableStateOf(false) }

    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    //trigger animation
    LaunchedEffect(triggerIconAnimation) {
        if (triggerIconAnimation) {
            //scale up
            scale.animateTo(1.3f, animationSpec = tween(50))

            //jiggle
            rotation.animateTo(-15f, animationSpec = tween(40))
            rotation.animateTo(15f, animationSpec = tween(40))
            rotation.animateTo(-15f, animationSpec = tween(40))
            rotation.animateTo(15f, animationSpec = tween(40))
            rotation.animateTo(0f, animationSpec = tween(40))

            //final
            scale.animateTo(1f, animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ))

            triggerIconAnimation = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        //bg
        Image(
            painter = painterResource(id = R.drawable.affirmation_bg),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        //bookmark
        Image(
            painter = painterResource(id = R.drawable.affirmation_bookmark_icon),
            contentDescription = "Go to Favorites",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 62.dp, end = 14.dp)
                .size(60.dp)
                .scale(scale.value)
                .rotate(rotation.value)
                .clickable { onNavigateToFavorites() },
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //title
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Today's Affirmation",
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                    fontSize = 32.sp,
                    color = Color.White
                )

                Text(
                    text = "What colors your mood in the stars\ntoday?",
                    fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                    fontSize = 20.sp,
                    color = Color.White,
                    lineHeight = 22.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            //cloud
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.affirmation_cloud),
                    contentDescription = "Cloud",
                    modifier = Modifier
                        .width(450.dp)
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )

                // Affirmation text + bookmark
                Box(
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .offset(y = (-10).dp)
                ) {
                    //affirmation text
                    Text(
                        text = when (moodState) {
                            is MoodUIState.Start -> "\"Your energy feels unique today.\nMind telling me how your mood is?\""
                            is MoodUIState.Loading -> "Loading..."
                            is MoodUIState.Success -> "\"${moodState.mood.affirmation_text}\""
                            is MoodUIState.Error -> "Oops, something went wrong!"
                        },
                        fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                        fontSize = 20.sp,
                        color = Color(color = 0xFF484599),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp
                    )

                    //star
                    if (moodState is MoodUIState.Success) {
                        Image(
                            painter = painterResource(id = R.drawable.affirmation_bookmark),
                            contentDescription = "Save to Bookmark",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.CenterEnd)
                                .offset(
                                    x = 20.dp,
                                    y = -20.dp
                                )
                                .clickable {
                                    onBookmarkClick()
                                    triggerIconAnimation = true
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            //mascot
            Image(
                painter = painterResource(id = R.drawable.affirmation_maskot),
                contentDescription = "Mascot",
                modifier = Modifier
                    .size(250.dp)
                    .offset(y = (-80).dp)
                    .zIndex(1f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(0.dp))

            //mood buttons
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .offset(y = (-60).dp),
                contentAlignment = Alignment.TopCenter
            ) {
                //happy
                Image(
                    painter = painterResource(id = R.drawable.affirmation_happy),
                    contentDescription = "I'm Happy",
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .offset(y = 0.dp)
                        .clickable { onMoodSubmit("HAPPY") },
                    contentScale = ContentScale.Fit
                )

                //notbad
                Image(
                    painter = painterResource(id = R.drawable.affirmation_notbad),
                    contentDescription = "Not Bad",
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .offset(y = 62.dp)
                        .clickable { onMoodSubmit("NEUTRAL") },
                    contentScale = ContentScale.Fit
                )

                //sad
                Image(
                    painter = painterResource(id = R.drawable.affirmation_sad),
                    contentDescription = "I'm Sad",
                    modifier = Modifier
                        .width(200.dp)
                        .height(50.dp)
                        .offset(y = 124.dp)
                        .clickable { onMoodSubmit("SAD") },
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AffirmationContentPreview_Start() {
    AffirmationContent(
        moodState = MoodUIState.Start,
        onMoodSubmit = {},
        onBookmarkClick = {},
        onNavigateToFavorites = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AffirmationContentPreview_Success() {
    val dummyMood = MoodData(
        id = 1,
        mood_type = "HAPPY",
        affirmation_text = "You are doing great today! Keep shining!"
    )

    AffirmationContent(
        moodState = MoodUIState.Success(dummyMood),
        onMoodSubmit = {},
        onBookmarkClick = {},
        onNavigateToFavorites = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AffirmationContentPreview_Loading() {
    AffirmationContent(
        moodState = MoodUIState.Loading,
        onMoodSubmit = {},
        onBookmarkClick = {},
        onNavigateToFavorites = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AffirmationContentPreview_Error() {
    AffirmationContent(
        moodState = MoodUIState.Error("Network error"),
        onMoodSubmit = {},
        onBookmarkClick = {},
        onNavigateToFavorites = {}
    )
}