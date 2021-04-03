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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.ui.common.CommonAppBar

@Composable
fun PomodoroTrackerScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CommonAppBar(
                title = "ポモドーロタイマーアプリ",
                navController = navController,
                shouldRoot = true
            )
        },
    ) {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxHeight()
        ) {
            Column (
                modifier = Modifier.padding(16.dp),
            ) {
                // MEMO: ボタンを押下してカウントをインクリメントする処理をまとめたもの
                PomodoroTrackerContent1()
                // MEMO:
                PomodoroTrackerContent2()
            }
        }
    }
}

@Composable
fun PomodoroTrackerContent1() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val (currentClickCount, setClickCount) = remember {
            mutableStateOf(value = 0)
        }
        Text(
            text = "PomodoroTrackerScreen",
            style = MaterialTheme.typography.h6
        )
        Spacer(
            modifier = Modifier
                .padding(top = 12.dp)
        )
        Button(
            onClick = {
                val newClicks = currentClickCount + 1
                setClickCount(newClicks)
            }
        ) {
            Text(text = "Click This Button !!!")
        }
        Spacer(
            modifier = Modifier
                .padding(top = 24.dp)
        )
        Text(text = "Button Clicked ($currentClickCount) Times !!!")
    }
}

@Composable
fun PomodoroTrackerContent2() {
    var name by rememberSaveable { mutableStateOf("") }
    PomodoroTrackerParts(name = name, onNameChange = { name = it })
}

@Composable
fun PomodoroTrackerParts(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Hello, $name",
            style = MaterialTheme.typography.h6
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { onNameChange(it) },
            label = { Text("Name") }
        )
    }
}

@Preview("Pomodoro Tracker Screen", widthDp = 360, heightDp = 640)
@Composable
fun PomodoroTrackerScreenPreview() {
    PomodoroTrackerScreen(
        navController = NavController(LocalContext.current),
    )
}
