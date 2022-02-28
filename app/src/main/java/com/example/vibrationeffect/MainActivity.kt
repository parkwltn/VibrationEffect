package com.example.vibrationeffect

import android.content.Context
import android.os.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnVibrate).setOnClickListener {
            //진동 객체 얻기
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
                        as VibratorManager
                vibratorManager.defaultVibrator

            } else {
                getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            //기본 세기로 진동 울리기
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                vibrator.vibrate(VibrationEffect.createOneShot(500,
                VibrationEffect.DEFAULT_AMPLITUDE))

            } else {
                // backward compatibility for Android API < 26
                // noinspection deprecation
                vibrator.vibrate(500)
            }
        }
    }
}