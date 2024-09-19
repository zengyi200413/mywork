package com.example.a3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class ThirdActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var confirmButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)

        editText = findViewById(R.id.editText)
        confirmButton = findViewById(R.id.confirmButton)

        confirmButton.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.isNotBlank()) {
                val data = Intent()
                data.putExtra("input_text", inputText)
                setResult(RESULT_OK, data)
                finish()
            }
        }
    }
}