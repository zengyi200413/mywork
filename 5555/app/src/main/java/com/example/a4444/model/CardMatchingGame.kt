package com.example.a4444.model

class CardMatchingGame(count: Int) {
    var score = 0
    val cards: MutableList<Card>

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

    fun reset() {
        for (card in cards) {
            card.isChosen = false
            card.isMatched = false
        }
        score = 0
    }

    private val isMatchPenalty = 2
    private val matchBonus = 4
    private val costToChoose = 1

    fun chooseCardAtIndex(index: Int) {
        val card = cardAtIndex(index)
        if (!card.isMatched) {
            if (card.isChosen) {
                card.isChosen = false
            } else {
                val chosenCards = cards.filter { it.isChosen && !it.isMatched }
                if (chosenCards.size == 2) {
                    val matchScore = card.match(chosenCards.toTypedArray())
                    if (matchScore > 0) {
                        score += matchScore * matchBonus
                        chosenCards.forEach { it.isMatched = true }
                        card.isMatched = true
                    } else {
                        score -= isMatchPenalty
                        chosenCards.forEach { it.isChosen = false }
                    }
                } else {
                    score -= costToChoose
                    card.isChosen = true
                }
            }
        }
    }
}