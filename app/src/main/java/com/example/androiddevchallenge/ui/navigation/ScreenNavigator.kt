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
package com.example.androiddevchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screen.PomodoroHistoryScreen
import com.example.androiddevchallenge.ui.screen.PomodoroTrackerScreen

enum class Destinations {
    PomodoroTrackerScreen {
        override fun toString(): String {
            return "PomodoroTracker"
        }
        override fun getRoute(): String {
            return "PomodoroTracker"
        }
    },
    PomodoroHistoryScreen {
        override fun toString(): String {
            return "PomodoroHistory"
        }
        override fun getRoute(): String {
            return "PomodoroHistory"
        }
    };

    abstract fun getRoute(): String
}

@Composable
fun ScreenNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Destinations.PomodoroTrackerScreen.getRoute()) {
        composable(
            route = Destinations.PomodoroTrackerScreen.getRoute()
        ) { PomodoroTrackerScreen(navController) }
        composable(
            route = Destinations.PomodoroHistoryScreen.getRoute()
        ) { PomodoroHistoryScreen(navController) }
    }
}