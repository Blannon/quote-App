package com.blannon_network.quoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShuffleOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.blannon_network.quoteapp.domain.model.Quote
import com.blannon_network.quoteapp.presentation.ErrorScreen
import com.blannon_network.quoteapp.presentation.LoadingScreen
import com.blannon_network.quoteapp.presentation.MainViewModel
import com.blannon_network.quoteapp.presentation.QuoteScreen
import com.blannon_network.quoteapp.ui.theme.QuoteAppTheme
import com.blannon_network.quoteapp.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteAppTheme {
                val apiState: ApiState = mainViewModel.response.value
                val scope: CoroutineScope = rememberCoroutineScope()

                LaunchedEffect(
                    key1 =true ,
                    block = {mainViewModel.getQuote()}
                )

                Scaffold (
                    topBar = {
                        TopAppBar(
                            title = { Text(
                                text ="Quote",
                                fontFamily = FontFamily.Serif
                            )
                            })
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                scope.launch {
                                    mainViewModel.getQuote()
                                }
                            }) {
                            Icon(
                                imageVector = Icons.Rounded.ShuffleOn ,
                                contentDescription =null,
                                tint = Color.White
                            )
                            
                        }
                    }
                ){paddingValues ->
                    AnimatedContent(
                        targetState = apiState,
                        label = "animated_content",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        transitionSpec = {
                            fadeIn(
                                animationSpec = tween(
                                    durationMillis = 250,
                                    easing = LinearEasing
                                )
                            ) togetherWith fadeOut(
                                animationSpec = tween(
                                    durationMillis = 250,
                                    easing = LinearEasing
                                )
                            )
                        } ) {result ->
                            when(result){
                                is ApiState.Success -> {
                                    val quote: Quote = result.data.body()!!
                                    QuoteScreen(quote = quote)
                                }
                                
                                is ApiState.Loading -> {
                                    LoadingScreen()
                                }
                                is ApiState.Failure -> {
                                    ErrorScreen(error = result.error.message?: "Try Again Something went Wrong")
                                }
                                else -> Unit
                            }


                    }

                }

            }

        }
    }
}
