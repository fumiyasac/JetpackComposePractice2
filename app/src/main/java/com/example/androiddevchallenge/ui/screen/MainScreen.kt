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
package com.example.androiddevchallenge.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.common.CommonAppBar
import com.example.androiddevchallenge.ui.components.CountDownIndicator
import com.example.androiddevchallenge.ui.components.TimerInputArea
import com.example.androiddevchallenge.viewmodel.CountDownTimerViewModel

@ExperimentalAnimationApi
@Composable
fun MainScreen(
    navController: NavController,
    countDownTimerViewModel: CountDownTimerViewModel
) {

    // 時間・分・秒の値変化をキャッチできる様にobserveAsStateで変換する
    val seconds = countDownTimerViewModel.seconds.observeAsState(initial = 0)
    val minutes = countDownTimerViewModel.minutes.observeAsState(initial = 0)
    val hours = countDownTimerViewModel.hours.observeAsState(initial = 0)

    // タイマー実施状態・タイマー進捗・時間表示ををキャッチできる様にobserveAsStateで変換する
    val isRunning = countDownTimerViewModel.isRunning.observeAsState(initial = false)
    val progress = countDownTimerViewModel.progress.observeAsState(initial = 1f)
    val timeShow = countDownTimerViewModel.time.observeAsState(initial = "00:00:00")

    Scaffold(
        topBar = {
            CommonAppBar(
                title = "カウントダウンタイマー",
                navController = navController,
                shouldRoot = true
            )
        },
    ) {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxHeight()
        ) {
            // カラムの中にそれぞれ必要な表示要素を配置
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                // 現在のタイマー進捗度合いを示す部分
                CountDownIndicator(
                    progress = progress.value,
                    timeShow = timeShow.value
                )
                // 時間・分・秒を入力するためのボタンセットエリア
                TimerInputArea(
                    isRunning = isRunning.value,
                    hours = hours.value,
                    minutes = minutes.value,
                    seconds = seconds.value,
                    onClickModifyHours = { timeOperator ->
                        countDownTimerViewModel.modifyTime(
                            CountDownTimerViewModel.Companion.TimeUnit.HOUR,
                            timeOperator
                        )
                    },
                    onClickModifyMinutes = { timeOperator ->
                        countDownTimerViewModel.modifyTime(
                            CountDownTimerViewModel.Companion.TimeUnit.MIN,
                            timeOperator
                        )
                    },
                    onClickModifySeconds = { timeOperator ->
                        countDownTimerViewModel.modifyTime(
                            CountDownTimerViewModel.Companion.TimeUnit.SEC,
                            timeOperator
                        )
                    },
                )
                // 余白スペース
                Spacer(
                    modifier = Modifier.height(40.dp)
                )
                // タイマーの実行または停止をハンドリングするためのボタン
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    onClick = {
                        val shouldHandle = shouldHandleCountDown(
                            hours = hours.value,
                            minutes = minutes.value,
                            seconds = seconds.value
                        )
                        if (shouldHandle) {
                            when (!isRunning.value) {
                                true -> countDownTimerViewModel.startCountDown()
                                false -> countDownTimerViewModel.cancelTimer()
                            }
                        } else null
                    }
                ) {
                    Text(text = "スタートまたはストップ")
                }
            }
        }
    }
}

private fun shouldHandleCountDown(hours: Int, minutes: Int, seconds: Int): Boolean {
    return !(hours == 0 && minutes == 0 && seconds == 0)
}

@Preview("カウントダウンタイマー", widthDp = 360, heightDp = 640)
@ExperimentalAnimationApi
@Composable
fun PomodoroHistoryScreenPreview() {
    MainScreen(
        navController = NavController(LocalContext.current),
        countDownTimerViewModel = CountDownTimerViewModel()
    )
}
