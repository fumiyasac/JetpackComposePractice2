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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.typography
import com.example.androiddevchallenge.viewmodel.CountDownTimerViewModel

@ExperimentalAnimationApi
@Composable
fun TimerInputSet(
    value: Int?,
    timeUnit: CountDownTimerViewModel.Companion.TimeUnit,
    enabled: Boolean,
    onClick: (CountDownTimerViewModel.Companion.TimeOperator) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = timeUnit.name,
                fontSize = 14.sp,
                color = Color.Black,
                style = typography.caption,
            )
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        TimerOperatorButton(
            timeOperator = CountDownTimerViewModel.Companion.TimeOperator.INCREASE,
            isEnabled = enabled,
            onClick = onClick
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = String.format("%02d", value ?: 0),
            fontSize = 32.sp,
            color = Color.Black
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TimerOperatorButton(
            timeOperator = CountDownTimerViewModel.Companion.TimeOperator.DECREASE,
            isEnabled = enabled,
            onClick = onClick
        )
    }
}

@Preview("時間単位増減ボタン", widthDp = 48, heightDp = 172)
@ExperimentalAnimationApi
@Composable
fun TimerInputSetPreview() {
    TimerInputSet(
        value = 56,
        timeUnit = CountDownTimerViewModel.Companion.TimeUnit.HOUR,
        enabled = true,
        onClick = {}
    )
}
