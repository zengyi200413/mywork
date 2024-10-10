package com.example.a4444

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.a4444.model.CardMatchingGame

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var game: CardMatchingGame
    private lateinit var gridlayout: GridLayout
    private lateinit var reset: Button
    private lateinit var score: TextView

    private val cardButtons = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // 初始化视图组件
        gridlayout = findViewById(R.id.gridlayout)
        reset = findViewById(R.id.reset)
        score = findViewById(R.id.score)

        for (v in gridlayout.children) {
            if (v is Button) {
                v.setOnClickListener(this)
                cardButtons.add(v)
            }
        }

        game = CardMatchingGame(cardButtons.count())
        updateUI()

        reset.setOnClickListener {
            game = CardMatchingGame(cardButtons.count())
            updateUI()
        }
    }

    @SuppressLint("DefaultLocale")
    private fun updateUI() {
        for (button in cardButtons) {
            val index = cardButtons.indexOf(button)
            val card = game.cardAtIndex(index)
            button.isEnabled = !card.isMatched
            if (card.isChosen) {
                button.text = card.toString()
                setBackgroundColor()// 设置背景颜色
            } else {
                button.text = ""
                button.setBackgroundResource(R.drawable.card_back)
            }
        }
        score.text = String.format("%s%d", getString(R.string.score), game.score)
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            val index = cardButtons.indexOf(v)
            game.chooseCardAtIndex(index)
            updateUI()
        }
    }

    private fun setBackgroundColor() {
        // 可以在这里设置背景颜色或其他样式
    }
}