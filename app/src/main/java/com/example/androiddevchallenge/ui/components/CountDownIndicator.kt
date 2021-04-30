/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.teal200

@ExperimentalAnimationApi
@Composable
fun CountDownIndicator(
    progress: Float,
    timeShow: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = teal200,
                modifier = Modifier.size(210.dp),
                progress = progress,
                strokeWidth = 16.dp
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ReusableHeaderText(
                    text = timeShow,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
private fun ReusableHeaderText(text: String, color: Color) {
    Text(
        text = text,
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h1,
        color = color
    )
}

@Preview("カウントダウン円形表示部分", widthDp = 290, heightDp = 290)
@ExperimentalAnimationApi
@Composable
fun CountDownIndicatorPreview() {
    CountDownIndicator(
        progress = 0.63F,
        timeShow = "00:05:30"
    )
}
