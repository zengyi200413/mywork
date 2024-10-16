package com.example.a4444

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a4444.model.Card
import com.example.a4444.model.CardMatchingGame

class CardAdapter(
    private val cardList: List<Card>,
    private val game: CardMatchingGame,
    private val scoreTextView: TextView
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardButton: Button = itemView.findViewById(R.id.cardButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cardList[position]
        holder.cardButton.text = if (card.isChosen) card.toString() else ""
        holder.cardButton.isEnabled = !card.isMatched

        // 根据卡片的状态设置背景图片
        when {
            card.isMatched -> holder.cardButton.setBackgroundResource(R.drawable.card_matched_image)
            card.isChosen -> holder.cardButton.setBackgroundResource(R.drawable.card_chosen_image)
            else -> holder.cardButton.setBackgroundResource(R.drawable.card_back_image)
        }

        holder.cardButton.setOnClickListener {
            game.chooseCardAtIndex(position)
            notifyDataSetChanged()
            scoreTextView.text = it.context.getString(R.string.score_format, game.score)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}