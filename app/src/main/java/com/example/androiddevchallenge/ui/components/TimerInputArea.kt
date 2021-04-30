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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.viewmodel.CountDownTimerViewModel

@ExperimentalAnimationApi
@Composable
fun TimerInputArea(
    isRunning: Boolean,
    hours: Int,
    minutes: Int,
    seconds: Int,
    onClickModifyHours: (CountDownTimerViewModel.Companion.TimeOperator) -> Unit,
    onClickModifyMinutes: (CountDownTimerViewModel.Companion.TimeOperator) -> Unit,
    onClickModifySeconds: (CountDownTimerViewModel.Companion.TimeOperator) -> Unit,
) {
    val enabled = (!isRunning)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimerInputSet(
            value = hours,
            timeUnit = CountDownTimerViewModel.Companion.TimeUnit.HOUR,
            enabled = enabled
        ) { timeOperator ->
            onClickModifyHours(timeOperator)
        }
        Spacer(
            modifier = Modifier.width(36.dp)
        )
        TimerInputSet(
            value = minutes,
            timeUnit = CountDownTimerViewModel.Companion.TimeUnit.MIN,
            enabled = enabled
        ) { timeOperator ->
            onClickModifyMinutes(timeOperator)
        }
        Spacer(
            modifier = Modifier.width(36.dp)
        )
        TimerInputSet(
            value = seconds,
            timeUnit = CountDownTimerViewModel.Companion.TimeUnit.SEC,
            enabled = enabled
        ) { timeOperator ->
            onClickModifySeconds(timeOperator)
        }
    }
}

@Preview("時間・分・秒増減ボタンセット", widthDp = 360, heightDp = 172)
@ExperimentalAnimationApi
@Composable
fun TimerInputAreaPreview() {
    TimerInputArea(
        isRunning = false,
        hours = 2,
        minutes = 35,
        seconds = 47,
        onClickModifyHours = {},
        onClickModifyMinutes = {},
        onClickModifySeconds = {}
    )
}
