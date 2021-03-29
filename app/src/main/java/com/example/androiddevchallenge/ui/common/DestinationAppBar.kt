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
package com.example.androiddevchallenge.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun CommonAppBar(
    title: String,
    navController: NavController,
    shouldRoot: Boolean = false
) {
    if (shouldRoot) {
        RootAppBar(
            title = title
        )
    } else {
        DestinationAppBar(
            title = title,
            navController = navController
        )
    }
}

@Preview("Root CommonAppBar Component")
@Composable
fun RootCommonAppBarPreview() {
    CommonAppBar(
        title = "ポモドーロタイマーアプリ",
        navController = NavController(LocalContext.current),
        shouldRoot = true
    )
}

@Preview("Destination CommonAppBar Component")
@Composable
fun DestinationCommonAppBarPreview() {
    CommonAppBar(
        title = "ポモドーロタイマー履歴表示",
        navController = NavController(LocalContext.current),
        shouldRoot = false
    )
}
