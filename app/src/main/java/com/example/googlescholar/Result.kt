package com.example.googlescholar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
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
import com.example.googlescholar.ui.theme.Grey0
import com.example.googlescholar.ui.theme.Grey3

@Composable
fun Result(
    navController: NavHostController
) {
    Surface(
        color = Grey0,
    ) {
        LazyColumn (
            modifier = Modifier
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
                    SearchBar(
                        onDone = { navController.navigate(Screen.ResultScreen.route) }
                    )
                }
            }
            item { Spacer(value = 8) }
            repeat(10) {
                item {
                    ArticleHome(data = Model.ArticleData("Title", "Author", "2014", 2532))
                    Spacer(value = 8)
                }
            }
        }
    }
}