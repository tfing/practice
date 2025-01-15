package com.example.lemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemon.ui.theme.LemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun MakeLemonade(modifier: Modifier = Modifier) {
    var state by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(1) }
    var squeezeMax by remember { mutableStateOf(1) }

    val defaultColor = Color.Green.hashCode()
    var squeezeColor by remember { mutableStateOf(defaultColor) }

    val actImage = when(state) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val desString = when(state) {
        1 -> R.string.des_tree
        2 -> R.string.des_lemon
        3 -> R.string.des_drink
        else -> R.string.des_again
    }

    val actString = when(state) {
        1 -> R.string.act_tree
        2 -> R.string.act_lemon
        3 -> R.string.act_drink
        else -> R.string.act_again
    }

    val actColor = when(state) {
        1 -> Color.Green.hashCode()
        2 -> squeezeColor
        3 -> 0xFFFFFF00.toInt()
        else -> Color.LightGray.hashCode()
    }

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(actColor)
            ),
            onClick = {
                if (state == 2) {
                    squeezeCount += 1
                    if (squeezeCount == squeezeMax) {
                        state = 3
                    } else {
                        val blue = (0xFF * (1 - (squeezeCount.toFloat() / squeezeMax.toFloat()))).toUInt()
                        Log.d("state", "blue $blue")
                        squeezeColor = (0xFFFFFF00.toUInt() + blue).toInt()
                    }
                } else {
                    state += 1
                    when (state) {
                        2 -> {
                            squeezeColor = 0xFFFFFFFF.toInt()
                            squeezeMax = (3..6).random()
                            squeezeCount = 0
                        }
                        5 -> state = 1
                    }
                }
                Log.d("state", "state $state max $squeezeMax count $squeezeCount color ${squeezeColor.toUInt().toString(16)}")
            }
        ) {
            Image(
                painter = painterResource(actImage),
                contentDescription = stringResource(desString),
            )
        }
        Spacer( modifier = Modifier.height(18.dp) )
        Text(
            text = stringResource(actString),
            fontSize = 20.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonApp() {
    MakeLemonade(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}