package com.dev.tasktime

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.dev.tasktime.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.ZoneId


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

//        setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val alarmScheduler = AlarmScheduler(this)
        var alarmItem: AlarmItem? = null

        //val btnSchedule = findViewById<R.id.btn_schedule>()
        val btnSchedule = binding.btnSchedule
        btnSchedule.setOnClickListener {
            run {
              var secondText = binding.textinputDelay.text.toString()
               var messageText = binding.textinputMessage.text.toString()
                alarmItem = AlarmItem(
                    time = LocalDateTime.now(ZoneId.systemDefault())
                        .plusSeconds(secondText.toLong()),
                    message = messageText
                )
                alarmItem?.let(alarmScheduler::schedule)
            }

        }

        val btnCancel = binding.btnCancel
        btnCancel.setOnClickListener {
            alarmItem?.let(alarmScheduler::cancel)
        }
    }


}
