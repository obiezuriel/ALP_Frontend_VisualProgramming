package com.obie.alp_frontend_visualprogramming.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.obie.alp_frontend_visualprogramming.ui.model.FavoriteData
import com.obie.alp_frontend_visualprogramming.ui.uistate.AllFavoriteUIState

@Composable
fun FavoriteListContent(
    favoriteState: AllFavoriteUIState,
    onBackClick: () -> Unit,
    onEditFavorite: (FavoriteData, String) -> Unit,
    onDeleteFavorite: (FavoriteData) -> Unit
) {
    // State untuk dialog
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedFavorite by remember { mutableStateOf<FavoriteData?>(null) }
    var editNoteText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF9D8AC7))
    ) {
        // Hang star
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.hang_star_p),
                contentDescription = "Pink Star",
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 20.dp),
                contentScale = ContentScale.Fit
            )

            Image(
                painter = painterResource(id = R.drawable.hang_star_y),
                contentDescription = "Yellow Star",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 10.dp),
                contentScale = ContentScale.Fit
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 24.dp, end = 24.dp)
        ) {
            // Back button
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { onBackClick() },
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title
            Text(
                text = "Bookmark",
                fontFamily = FontFamily(Font(R.font.helveticaneuebold)),
                fontSize = 32.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Content based on state
            when (favoriteState) {
                is AllFavoriteUIState.Start -> {
                    // Empty state
                }

                is AllFavoriteUIState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            color = Color.White,
                            strokeWidth = 4.dp
                        )
                    }
                }

                is AllFavoriteUIState.Success -> {
                    if (favoriteState.favorites.isEmpty()) {
                        Text(
                            text = "No bookmarks yet.\nSave your favorite affirmations!",
                            fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    } else {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(favoriteState.favorites) { favorite ->
                                FavoriteCard(
                                    favorite = favorite,
                                    onEditClick = {
                                        selectedFavorite = favorite
                                        editNoteText = favorite.note ?: ""
                                        showEditDialog = true
                                    },
                                    onDeleteClick = {
                                        selectedFavorite = favorite
                                        showDeleteDialog = true
                                    }
                                )
                            }
                        }
                    }
                }

                is AllFavoriteUIState.Error -> {
                    Text(
                        text = "Oops! ${favoriteState.message}",
                        fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }

    // Edit Dialog
    if (showEditDialog && selectedFavorite != null) {
        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = {
                Text(
                    text = "Edit Note",
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                )
            },
            text = {
                Column {
                    Text(
                        text = "\"${selectedFavorite!!.affirmation_text}\"",
                        fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = editNoteText,
                        onValueChange = { editNoteText = it },
                        label = { Text("Note/Title") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF9D8AC7),
                            focusedLabelColor = Color(0xFF9D8AC7)
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEditFavorite(selectedFavorite!!, editNoteText)
                        showEditDialog = false
                        selectedFavorite = null
                    }
                ) {
                    Text("Save", color = Color(0xFF9D8AC7))
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancel", color = Color.Gray)
                }
            }
        )
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog && selectedFavorite != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Delete Bookmark",
                    fontFamily = FontFamily(Font(R.font.helveticaneuebold))
                )
            },
            text = {
                Text(
                    text = "Are you sure you want to delete this bookmark?",
                    fontFamily = FontFamily(Font(R.font.helveticaneuelight))
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteFavorite(selectedFavorite!!)
                        showDeleteDialog = false
                        selectedFavorite = null
                    }
                ) {
                    Text("Delete", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancel", color = Color.Gray)
                }
            }
        )
    }
}

@Composable
fun FavoriteCard(
    favorite: FavoriteData,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Title row dengan icon Edit & Delete
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Title/Note
            Text(
                text = if (favorite.note.isNullOrEmpty()) "Untitled" else favorite.note,
                fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )

            // Edit & Delete icons
            Row {
                // Edit icon (kuning)
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color(0xFFFFE08F),
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                // Delete icon (merah)
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color(0xFFFF6B6B),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Affirmation text card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF5E6F0))
                .padding(16.dp)
        ) {
            Text(
                text = favorite.affirmation_text,
                fontFamily = FontFamily(Font(R.font.helveticaneuelight)),
                fontSize = 14.sp,
                color = Color(0xFF484599)
            )
        }
    }
}

// Previews
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavoriteListContentPreview_Start() {
    FavoriteListContent(
        favoriteState = AllFavoriteUIState.Start,
        onBackClick = {},
        onEditFavorite = { _, _ -> },
        onDeleteFavorite = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavoriteListContentPreview_Success() {
    val dummyFavorites = listOf(
        FavoriteData(
            id = 1,
            user_id = 1,
            affirmation_text = "Your smile is contagious! Keep shining.",
            note = "My morning motivation",
            created_at = "2024-01-01"
        ),
        FavoriteData(
            id = 2,
            user_id = 1,
            affirmation_text = "You are doing great today!",
            note = "",
            created_at = "2024-01-02"
        )
    )

    FavoriteListContent(
        favoriteState = AllFavoriteUIState.Success(dummyFavorites),
        onBackClick = {},
        onEditFavorite = { _, _ -> },
        onDeleteFavorite = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavoriteListContentPreview_Empty() {
    FavoriteListContent(
        favoriteState = AllFavoriteUIState.Success(emptyList()),
        onBackClick = {},
        onEditFavorite = { _, _ -> },
        onDeleteFavorite = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FavoriteListContentPreview_Loading() {
    FavoriteListContent(
        favoriteState = AllFavoriteUIState.Loading,
        onBackClick = {},
        onEditFavorite = { _, _ -> },
        onDeleteFavorite = {}
    )
}