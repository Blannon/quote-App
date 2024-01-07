package com.blannon_network.quoteapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blannon_network.quoteapp.domain.model.Quote

@Composable
fun QuoteScreen(quote: Quote){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .background(Color.LightGray)
    ) {
        
        quote.tags.forEach{tag ->
            AssistChip(
                onClick = { /*TODO*/ },
                label = {
                    Text(
                        text =tag,
                        fontFamily = FontFamily.Serif
                    )
                })
        }
        Icon(
            imageVector = Icons.Default.FormatQuote ,
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
        )

        Text(
            text = quote.content,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(
            modifier =Modifier
                .size(8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(94f),
            contentAlignment = Alignment.CenterEnd){
            Text(
                text = "- ${quote.author}",
                fontSize = 16.sp,
                fontFamily = FontFamily.SansSerif
            )
        }



    }
}