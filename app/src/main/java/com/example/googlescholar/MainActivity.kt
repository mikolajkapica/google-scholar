package com.example.googlescholar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            navController = rememberNavController()
//            SetupNavGraph(navController)
            val state = remember {
                MutableTransitionState(false).apply {
                    // Start the animation immediately.
                    targetState = true
                }
            }
            Column {
                AnimatedVisibility(visibleState = state) {
                    Text(text = "Hello, world!")
                }

                // Use the MutableTransitionState to know the current animation state
                // of the AnimatedVisibility.
                Text(
                    text = when {
                        state.isIdle && state.currentState -> "Visible"
                        !state.isIdle && state.currentState -> "Disappearing"
                        state.isIdle && !state.currentState -> "Invisible"
                        else -> "Appearing"
                    }
                )
            }
        }
    }
}
