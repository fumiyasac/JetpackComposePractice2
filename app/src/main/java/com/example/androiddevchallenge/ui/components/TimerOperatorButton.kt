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
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.viewmodel.CountDownTimerViewModel

@ExperimentalAnimationApi
@Composable
fun TimerOperatorButton(
    isEnabled: Boolean,
    timeOperator: CountDownTimerViewModel.Companion.TimeOperator,
    onClick: (CountDownTimerViewModel.Companion.TimeOperator) -> Unit
) {
    Button(
        onClick = {
            onClick.invoke(timeOperator)
        },
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            disabledBackgroundColor = Color.LightGray
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp)
    ) {
        when (timeOperator) {
            CountDownTimerViewModel.Companion.TimeOperator.INCREASE -> Icon(
                Icons.Outlined.ArrowDropUp,
                null,
                Modifier.size(24.dp)
            )
            CountDownTimerViewModel.Companion.TimeOperator.DECREASE -> Icon(
                Icons.Outlined.ArrowDropDown,
                null,
                Modifier.size(24.dp)
            )
        }
    }
}

@Preview("増加ボタン", widthDp = 48, heightDp = 24)
@ExperimentalAnimationApi
@Composable
fun IncreaseTimerOperatorButtonPreview() {
    Box {
        TimerOperatorButton(
            isEnabled = true,
            timeOperator = CountDownTimerViewModel.Companion.TimeOperator.INCREASE,
            onClick = {},
        )
    }
}

@Preview("減少ボタン", widthDp = 48, heightDp = 24)
@ExperimentalAnimationApi
@Composable
fun DecreaseTimerOperatorButtonPreview() {
    Box {
        TimerOperatorButton(
            isEnabled = false,
            timeOperator = CountDownTimerViewModel.Companion.TimeOperator.DECREASE,
            onClick = {},
        )
    }
}
