package com.example.a4444.model

class Card(private var suit: String, private var rank: String, var isChosen: Boolean = false, var isMatched: Boolean = false) {
    companion object {
        val rankStrings = arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
        val validSuits = arrayOf("♥", "♦", "♠", "♣")
    }

    override fun toString(): String {
        return suit + rank
    }

    fun match(otherCards: Array<Card>): Int {
        var score = 0
        if (otherCards.size == 2) {
            val card1 = otherCards[0]
            val card2 = otherCards[1]

            // Check if all three cards have the same rank
            if (card1.rank == rank && card2.rank == rank) {
                score = 12
            }
            // Check if all three cards have the same suit
            else if (card1.suit == suit && card2.suit == suit) {
                score = 3
            }
            // Check if two cards have the same rank and one has the same suit
            else if ((card1.rank == rank || card2.rank == rank) && (card1.suit == suit || card2.suit == suit)) {
                score = 2
            }
            // Check if two cards have the same rank
            else if (card1.rank == card2.rank) {
                score = 1
            }
        }
        return score
    }
}