package com.example.googlescholar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.googlescholar.ui.theme.Grey3
import androidx.compose.material3.Text
import com.example.googlescholar.ui.theme.GoogleScholarTheme
import com.example.googlescholar.ui.theme.Grey0
import com.example.googlescholar.ui.theme.Grey4

@Composable
fun Search(
    navController: NavHostController
) {
    GoogleScholarTheme {
        Surface(
            color = Grey0,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 20.dp)
            ) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Grey3,
                            modifier = Modifier
                                .size(32.dp)
                                .padding(end = 8.dp)
                                .clickable {
                                    navController.popBackStack()
                                }
                        )
                        SearchBar (
                            onDone = { navController.navigate(Screen.ResultScreen.route) }
                        )
                    }
                }
                item {
                    Spacer(16)
                }
                repeat(4) {
                    item {
                        Text("Article $it", color = Grey4)
                        Divider()
                    }
                }
            }
        }
    }
}
