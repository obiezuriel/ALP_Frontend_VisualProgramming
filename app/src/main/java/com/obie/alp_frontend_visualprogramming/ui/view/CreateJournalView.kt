package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.obie.alp_frontend_visualprogramming.R
import com.obie.alp_frontend_visualprogramming.ui.viewmodel.CreateJournalViewModel

@Composable
fun CreateJournalView(
    viewModel: CreateJournalViewModel,
    navController: NavHostController
){
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.background_journal_create),
                contentDescription = "background",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFF7B71D9))
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {

            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(top = 16.dp, start = 0.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF332A86))
                    .border(3.dp, Color(0xFFFFE08F), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(180.dp))


            Text(
                text = "\"Unfold your thoughts.\"",
                fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "Title:",
                fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.title,
                onValueChange = { viewModel.title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE8D5F5),
                    unfocusedContainerColor = Color(0xFFE8D5F5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Date:",
                fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.date,
                onValueChange = { viewModel.date = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE8D5F5),
                    unfocusedContainerColor = Color(0xFFE8D5F5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Write your journal here:",
                fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.content,
                onValueChange = { viewModel.content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFE8D5F5),
                    unfocusedContainerColor = Color(0xFFE8D5F5),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))


            Button(
                onClick = { /* Handle save */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5B4BA8)
                ),
                shape = RoundedCornerShape(30.dp),
                border = BorderStroke(4.dp, Color(0xFFFFE08F))
            ) {
                Text(
                    text = "SAVE",
                    fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}



//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//private fun CreateJournalViewPreview(){
//    CreateJournalView()
//}
