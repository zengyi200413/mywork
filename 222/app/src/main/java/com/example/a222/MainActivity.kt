package com.example.a222

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class MainActivity : AppCompatActivity() {
    val expert = ProgramExpert()
    private var textViewCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.button)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val textView = findViewById<TextView>(R.id.textView)
        val button2 = findViewById<Button>(R.id.button2)
        val linearLayoutContainer = findViewById<LinearLayout>(R.id.linearLayoutContainer)
        button.setOnClickListener {
            val selectedfeature = spinner.selectedItem.toString()
            textView.text = expert.getLanguage(selectedfeature)


            button2.setOnClickListener {
                val newTextView = TextView(this).apply {
                    text = getString(R.string.message) + (++textViewCount)
                    textSize = 18f
                    gravity = Gravity.CENTER
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(10, 10, 10, 10)  // 设置 TextView 的边距
                    }
                }
                linearLayoutContainer.addView(newTextView)


            }
        }
    }
}