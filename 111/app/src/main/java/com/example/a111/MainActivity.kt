package com.example.a111

import android.os.Bundle

import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.orientation=LinearLayout.VERTICAL


        val density = resources.displayMetrics.density

        val imageView = ImageView(this)
        imageView.id = R.id.imageView
        imageView.layoutParams = ConstraintLayout.LayoutParams(
            (308 * resources.displayMetrics.density).toInt(),
            (235 * resources.displayMetrics.density).toInt()
        )
        imageView.setImageResource(R.drawable.flag)
        linearLayout.addView(imageView)

        val button = Button(this)
        button.id = R.id.button
        button.width = (141 * density).toInt()
        button.height = (59 * density).toInt()
        button.text=getString(R.string.button)
        linearLayout.addView(button)

        setContentView(linearLayout)

//        val textView = TextView(this)
        val textView = TextView(this).apply {

            text = getString(R.string.textview)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        textView.textSize = 30F
        linearLayout.addView(textView)

        button.setOnClickListener{
            textView.text=resources.getString(R.string.clicked)
        }
    }
}