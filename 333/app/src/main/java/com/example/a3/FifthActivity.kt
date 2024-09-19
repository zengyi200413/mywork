package com.example.a3

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class FifthActivity : AppCompatActivity() {
    private var chronometer:Chronometer?=null
    private var isRunning=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fifth)

        chronometer=findViewById(R.id.chronometer)
        findViewById<Button>(R.id.button_start)?.setOnClickListener{
            if(!isRunning){
                chronometer?.base=SystemClock.elapsedRealtime()
                chronometer?.start()
                isRunning=true
            }
        }
        findViewById<Button>(R.id.button_stop)?.setOnClickListener{
            if(isRunning){
                chronometer?.stop()
                isRunning=false
            }
        }
        findViewById<Button>(R.id.button_restart)?.setOnClickListener {
            chronometer?.base = SystemClock.elapsedRealtime()
            chronometer?.stop()
            isRunning = false
        }
        savedInstanceState?.let {
            chronometer?.base = savedInstanceState.getLong("BASE")
            isRunning = savedInstanceState.getBoolean("RUNNING", false)
            if (isRunning) chronometer?.start()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("BASE", chronometer?.base ?: 0L)
        outState.putBoolean("RUNNING", isRunning)
    }

    }

