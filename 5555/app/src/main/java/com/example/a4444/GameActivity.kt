package com.example.a4444.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a4444.CardAdapter
import com.example.a4444.R
import com.example.a4444.model.CardMatchingGame

class GameActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CardAdapter
    private lateinit var game: CardMatchingGame
    private lateinit var reset: Button
    private lateinit var score: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        recyclerView = findViewById(R.id.recyclerView)
        reset = findViewById(R.id.reset)
        score = findViewById(R.id.score)

        val layoutManager = GridLayoutManager(this, getColumnCount())
        recyclerView.layoutManager = layoutManager

        game = CardMatchingGame(24) // 创建24张牌
        adapter = CardAdapter(game.cards, game, score) // 将 scoreTextView 传递给适配器
        recyclerView.adapter = adapter

        reset.setOnClickListener {
            game.reset()
            adapter.notifyDataSetChanged()
            score.text = getString(R.string.score_format, game.score)
        }

        score.text = getString(R.string.score_format, game.score)
    }

    private fun getColumnCount(): Int {
        return if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 6 else 4
    }

    override fun onClick(v: View?) {
        if (v is Button) {
            val index = recyclerView.getChildAdapterPosition(v)
            game.chooseCardAtIndex(index)
            adapter.notifyDataSetChanged()
            score.text = getString(R.string.score_format, game.score)
        }
    }
}