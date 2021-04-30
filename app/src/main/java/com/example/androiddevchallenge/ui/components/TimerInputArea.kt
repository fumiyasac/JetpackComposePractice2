package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding

import com.example.androiddevchallenge.viewmodel.CountDownTimerViewModel


@ExperimentalAnimationApi
@Composable
fun TimerInputArea(
    hours: Int,
    minutes: Int,
    seconds: Int,
    onClickModifyHours: () -> Unit,
    onClickModifyMinutes: () -> Unit,
    onClickModifySeconds: () -> Unit,
    isRunning: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(4.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimerComponent(
            value = hours.value,
            timeUnit = CountDownTimerViewModel.Companion.TimeUnit.HOUR,
            enabled = isRunning.value != true
        ) {
            countDownTimerViewModel.modifyTime(CountDownTimerViewModel.Companion.TimeUnit.HOUR, it)
        }

        Spacer(modifier = Modifier.width(36.dp))

        TimerComponent(
            value = minutes.value,
            timeUnit = CountDownTimerViewModel.Companion.TimeUnit.MIN,
            enabled = isRunning.value != true
        ) {
            countDownTimerViewModel.modifyTime(CountDownTimerViewModel.Companion.TimeUnit.MIN, it)
        }

        Spacer(modifier = Modifier.width(36.dp))

        TimerComponent(
            value = seconds.value,
            timeUnit = CountDownTimerViewModel.Companion.TimeUnit.SEC,
            enabled = isRunning.value != true
        ) {
            countDownTimerViewModel.modifyTime(CountDownTimerViewModel.Companion.TimeUnit.SEC, it)
        }
    }
}
