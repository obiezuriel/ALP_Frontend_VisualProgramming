package com.obie.alp_frontend_visualprogramming.ui.view

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavHostController
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.uistate.MoodUIState
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.MoodViewModel

@Composable
fun AffirmationView(
    viewModel: MoodViewModel,
    navController: NavHostController
) {
    val moodState = viewModel.moodUIState
    val userId = 1

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

                //affirmation text
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                        .offset(y = (-10).dp)
                ) {
                    Text(
                        text = when (moodState) {
                            is MoodUIState.Start -> "\"Your energy feels unique today.\nMind telling me how your mood is?\""
                            is MoodUIState.Loading -> "Loading..."
                            is MoodUIState.Success -> "\"${moodState.mood.affirmation_text}\""
                            is MoodUIState.Error -> "Oops, something went wrong!"
                        },
                        fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                        fontSize = 20.sp,
                        color = Color(color = 0xFF484599),
                        textAlign = TextAlign.Center,
                        lineHeight = 20.sp,
                        modifier = Modifier.weight(1f)
                    )

                    //fav
                    if (moodState is MoodUIState.Success) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(id = R.drawable.affirmation_bookmark),
                            contentDescription = "Bookmark",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    // TODO: Navigate ke Bookmark/Favorite screen
                                    // navController.navigate("Favorites")
                                }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            //maskot
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

            //mood
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
                        .clickable {
                            viewModel.submitMood(userId, "HAPPY")
                        },
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
                        .clickable {
                            viewModel.submitMood(userId, "NEUTRAL")
                        },
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
                        .clickable {
                            viewModel.submitMood(userId, "SAD")
                        },
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun AffirmationViewPreview() {
//    AffirmationView()
//}
