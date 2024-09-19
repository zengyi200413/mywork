package com.example.a3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener

const val FIRST_MSG="FIRST_MSG"
const val SECOND_MSG="SECOND_MSG"
class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private val inputLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val inputText = data?.getStringExtra("input_text")
                inputText?.let {
                    resultTextView.text = it
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val first=findViewById<Button>(R.id.first)
        val textView=findViewById<TextView>(R.id.editTextText)
        first.setOnClickListener{
            val intent=Intent(this,SecondActivity::class.java)
            intent.putExtra(FIRST_MSG,textView.text.toString())
            startActivity(intent)
        }
        findViewById<Button>(R.id.third)?.setOnClickListener {

            startActivity(Intent(this, ForthActivity::class.java))
        }

        findViewById<Button>(R.id.second)?.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            inputLauncher.launch(intent)
        }

        resultTextView = findViewById(R.id.textView)




        findViewById<Button>(R.id.forth)?.setOnClickListener{
            startActivity(Intent(this,FifthActivity::class.java))
        }


    }


}