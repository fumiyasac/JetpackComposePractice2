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
package com.example.androiddevchallenge.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 実装で参考にした記事（ほとんど写経ですみません...）
 * https://github.com/BegumYazici/AndroidComposeDevChallangeWeek2
 *
 * すでにAndroidXは利用可能な状態となっている
 * https://developer.android.com/jetpack/androidx?hl=ja#using_androidx_libraries_in_your_project
 *
 * CountDownTimerクラス
 * (注意) わずかに誤差が生じてしまったり端末がディープスリープするとCountDownTimerのカウントが止まる特徴がある
 * https://developer.android.com/reference/android/os/CountDownTimer
 * https://akira-watson.com/android/countdowntimer.html
 *
 */

class CountDownTimerViewModel : ViewModel() {

    companion object {
        enum class TimeOperator {
            INCREASE, DECREASE
        }

        enum class TimeUnit {
            SEC, MIN, HOUR
        }

        const val MINUTES_IN_HOUR = 60
        const val SECS_IN_MINUTES = 60
        const val MSECS_IN_SEC = 1000
    }

    // CountDownTimerクラスを格納する変数
    private var countDownTimer: CountDownTimer? = null

    // トータル時間を格納する変数
    var totalTime = 0L

    // LiveData(MutableLiveData)で管理するもの
    val isRunning: LiveData<Boolean>
        get() = _isRunning

    private val _seconds = MutableLiveData(0)
    val seconds: LiveData<Int>
        get() = _seconds

    private val _minutes = MutableLiveData(0)
    val minutes: LiveData<Int>
        get() = _minutes

    private val _hours = MutableLiveData(0)
    val hours: LiveData<Int>
        get() = _hours

    private val _isRunning = MutableLiveData(false)

    private val _progress = MutableLiveData(1f)
    val progress: LiveData<Float>
        get() = _progress

    private val _time = MutableLiveData("00:00:00")
    val time: LiveData<String>
        get() = _time

    // オーナーのActivityが終了されたタイミングでViewModelをクリーンアップするタイミングで実行される
    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }

    // カウントダウン処理をスタートする
    fun startCountDown() {

        // MEMO: もしカウントダウン処理がすでに動作しているならば一度キャンセルする
        if (countDownTimer != null) {
            cancelTimer()
        }

        // MEMO: 合計時間を更新する
        totalTime = (getSeconds() * 1000).toLong()

        // MEMO: CountDownTimerオブジェクトをインスタンス化する
        countDownTimer = object : CountDownTimer(totalTime, 1000) {

            // MEMO: onTick内でカウントダウン実行時の処理を記載する
            override fun onTick(millisecs: Long) {

                // MEMO: 時間・分・秒を更新する
                val secs = (millisecs / MSECS_IN_SEC % SECS_IN_MINUTES).toInt()
                if (secs != seconds.value) {
                    _seconds.postValue(secs)
                }
                val minutes = (millisecs / MSECS_IN_SEC / SECS_IN_MINUTES % SECS_IN_MINUTES).toInt()
                if (minutes != this@CountDownTimerViewModel.minutes.value) {
                    _minutes.postValue(minutes)
                }
                val hours = (millisecs / MSECS_IN_SEC / MINUTES_IN_HOUR / SECS_IN_MINUTES).toInt()
                if (hours != this@CountDownTimerViewModel.hours.value) {
                    _hours.postValue(hours)
                }

                // MEMO: 進捗パーセンテージと時間表示を更新する
                _progress.postValue(millisecs.toFloat() / totalTime.toFloat())
                _time.postValue(formatHourMinuteSecond(hours, minutes, secs))
            }

            // MEMO: onTick内でカウントダウン終了時の処理を記載する
            override fun onFinish() {
                _progress.postValue(1.0f)
                _isRunning.postValue(false)
            }
        }

        // MEMO: カウントダウン処理を開始する
        startTimer()
    }

    // タイマーを開始する
    fun startTimer() {

        // MEMO: countDownTimerを開始して_isRunningの状態をtrueにしてタイマーを進めた状態とする
        countDownTimer?.start()
        _isRunning.postValue(true)
    }

    // タイマーを終了する
    fun cancelTimer() {

        // MEMO: countDownTimerを終了して_isRunningの状態をfalseにしてタイマーを止めた状態とする
        countDownTimer?.cancel()
        _isRunning.postValue(false)
    }

    // タイマー計測時間を更新する
    fun modifyTime(timeUnit: TimeUnit, timeOperator: TimeOperator) {

        var seconds = seconds.value ?: 0
        var minutes = minutes.value ?: 0
        var hours = hours.value ?: 0

        // MEMO: 計測対象の時間・分・秒を更新する
        when (timeUnit) {
            TimeUnit.SEC -> {
                seconds = updateTime(seconds, timeOperator).coerceIn(0, 59)
            }
            TimeUnit.MIN ->{
                minutes = updateTime(minutes, timeOperator).coerceIn(0, 59)
            }
            TimeUnit.HOUR ->{
                hours = updateTime(hours, timeOperator).coerceIn(0, 23)
            }
        }

        // MEMO: LiveDataの値を更新してView側へ更新を伝える
        _seconds.postValue(seconds)
        _minutes.postValue(minutes)
        _hours.postValue(hours)
        _time.postValue(formatHourMinuteSecond(hours, minutes, seconds))
    }

    // タイマー計測時間更新処理
    private fun updateTime(currentValue: Int, timeOperator: TimeOperator): Int {
        return when (timeOperator) {
            TimeOperator.INCREASE -> currentValue + 1
            TimeOperator.DECREASE -> currentValue - 1
        }
    }

    // タイマー表示時間更新処理
    private fun formatHourMinuteSecond(hours : Int,minutes : Int,seconds : Int) =
        String.format("%02d:%02d:%02d", hours, minutes, seconds)

    // LiveDataから時間・分・秒の値を取得する
    private fun getSeconds() = ((hours.value ?: 0) * MINUTES_IN_HOUR * SECS_IN_MINUTES) + ((minutes.value
        ?: 0) * SECS_IN_MINUTES) + (seconds.value ?: 0)
}