package com.example.googlescholar

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.example.googlescholar.ui.theme.BlueLight
import com.example.googlescholar.ui.theme.GoogleScholarTheme
import com.example.googlescholar.ui.theme.Grey0
import com.example.googlescholar.ui.theme.Grey1
import com.example.googlescholar.ui.theme.Grey2
import com.example.googlescholar.ui.theme.Grey3
import com.example.googlescholar.ui.theme.Grey4
import com.example.googlescholar.ui.theme.Typography

@Composable
fun Home(
    navController: NavController
) {
    val keyboard_controller = LocalSoftwareKeyboardController.current
    GoogleScholarTheme {
        Surface(
            color = Grey0,
        ) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ){
                item {
                    Spacer(20)
                    Logo()
                    Spacer(32)
                    SearchTypes()
                    Spacer(12)
                    SearchBar (
                        onClick = { navController.navigate(Screen.SearchScreen.route) }
                    )
                    keyboard_controller?.hide()
                    Spacer(64)
                    TrendingTitle()
                    Spacer(6)
                }
                repeat(3) {
                    item {
                        ArticleHome(
                            Model.ArticleData(
                                title = "Artificial intelligence in medicine",
                                authors = "P Hamet, J Tremblay - Metabolism, 2017 - Elsevier",
                                year = "2021",
                                citiations = 1877
                            )
                        )
                        Spacer(16)
                    }
                }
                item {
                    Spacer(32)
                }
            }
        }
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(Grey2)
            .size(2.dp)
    ) {}
}

@Composable
fun TrendingTitle() {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = "Trending".uppercase(),
            style = Typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = Grey3
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_trending),
            contentDescription = "Trending)",
            tint = Grey3,
            modifier = Modifier
                .size(24.dp)
                .padding(start = 8.dp)
        )
    }

}

@Composable
fun ArticleHome(data: Model.ArticleData) {
    val uriHandler = LocalUriHandler.current
    Box(
        modifier = Modifier
            .border(2.dp, Grey2, RoundedCornerShape(16.dp))
            .noRippleClickable {
                uriHandler.openUri(data.link)
            }
            .padding(16.dp)
    ) {
        Column {
            Text(
                data.title,
                style = Typography.displayLarge,
            )
            Spacer(8)
            Text(
                data.authors,
                style = Typography.bodyMedium,
                color = BlueLight,
            )
            Spacer(8)
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_citation),
                    tint = Grey4,
                    contentDescription = "Citations",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
                Text(
                    data.citiations.toString(),
                    style = Typography.bodyMedium,
                    color = Grey3,
                )
            }
        }
    }

}

@Composable
fun Logo() {
    Image(
        painter = painterResource(id = R.drawable.scholar_logo_64dp),
        contentDescription = "Google Scholar Logo",
        modifier = Modifier.size(193.dp, 32.dp)
    )
}

@Composable
fun Spacer(value: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(value.dp)
    ) {}
}


@Composable
fun SearchField() {
    Text(
        text = "Search",
        style = Typography.displayMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Grey1)
            .padding(16.dp)
    )

}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

@Composable
fun SearchTypes() {
    var articlesOn by remember { mutableStateOf(true) }
    var caseLawOn by remember { mutableStateOf(false) }
    var authorsOn by remember { mutableStateOf(false) }

    @Composable
    fun SearchTypesButton(text: String, bold: Boolean = false) {
        Text(
            text = text,
            style = Typography.bodyMedium,
            fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal,
            textDecoration = if (bold) TextDecoration.Underline else null,
            color = Grey4,
            modifier = Modifier
                .padding(end = 20.dp)
                .noRippleClickable {
                    articlesOn = false
                    caseLawOn = false
                    authorsOn = false
                    when (text) {
                        "Articles" -> articlesOn = true
                        "Case Law" -> caseLawOn = true
                        "Authors" -> authorsOn = true
                    }
                }
        )
    }

    Row {
        SearchTypesButton("Articles", articlesOn)
        SearchTypesButton("Case Law", caseLawOn)
        SearchTypesButton("Authors", authorsOn)
    }
}

@Composable
fun SearchBar(
    onClick: (String) -> Unit = {},
    onDone: (String) -> Unit = {}
): String {
    var text by remember { mutableStateOf("") }
    val placeholder = "Search"
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        onValueChange = { text = it },
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onDone(text)
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Grey4,
            unfocusedTextColor = Grey4,
            focusedContainerColor = Grey1,
            unfocusedContainerColor = Grey1,
            disabledContainerColor = Grey1,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { if (it.isFocused) onClick(text) }
            .padding(vertical = 4.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Voice Search",
                tint = Grey3,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    )
    return text
}


