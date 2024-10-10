package com.example.a4444.model

class CardMatchingGame(count: Int)  {
     var score = 0

    private val cards: MutableList<Card>

    init {
        val deck = Deck()
        cards = mutableListOf()
        for (i in 1..count) {
            val card: Card? = deck.drawRandomCard()
            if (card != null) {
                cards.add(card)
            }
        }
    }

    fun cardAtIndex(index: Int): Card {
        return cards[index]
    }

    private val ismatchpenalty = 2
    private val atchbonus = 4
    private val costToChoose = 1

    fun chooseCardAtIndex(index: Int) {
        val card= cardAtIndex(index)
        if (!card.isMatched) {
            if (card.isChosen) {
                card.isChosen = false
            } else {
                for (otherCard in cards) {
                    if (otherCard.isChosen && !otherCard.isMatched) {
                        val matchScore = card.match(arrayOf(otherCard))
                        if (matchScore > 0) {
                            score += matchScore * atchbonus
                            otherCard.isMatched = true
                            card.isMatched = true
                        } else {
                            score -= ismatchpenalty
                            otherCard.isChosen = false
                        }
                        break
                    }
                }
                score -= costToChoose
                card.isChosen = true
            }
        }
    }
}