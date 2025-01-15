package com.example.card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.card.ui.theme.CardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardTheme {
                Surface (
                    modifier = Modifier.fillMaxSize()
                ) {
                    GreetingText(
                        message = "Happy Friday!",
                        name = "* Andy & Lucy *",
                        from = "From TingHan",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingText(message: String, name: String, from: String, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = message,
            fontSize = 90.sp,
            lineHeight = 130.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = name,
            fontSize = 55.sp,
            lineHeight = 130.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = from,
            fontSize = 35.sp,
            modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.End)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    CardTheme {
        GreetingText(
            message = "Happy Friday!",
            name = "* Andy & Lucy *",
            from = "From TingHan",
            modifier = Modifier.padding(8.dp)
        )
    }
}