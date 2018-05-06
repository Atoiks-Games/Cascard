# Cascard

Think Uno! without perks, also much less forgiving...

## Build Instructions:

You need jdk 8 or above.

Run `gradlew build` to build it or `gradlew run` to run after building

## Rule

At the start of the game, the computer will deal each player seven cards.
Computer draws a card, called the top card.
Player 1 goes first (and then player 2, player 3, ...).
Player has to play a card from their hand.
This card can either be:

1. The exact same card(__1__). In that case, the player goes again. If that was the last card, the computer will feed you another card.
2. One away(__2__). The round is done, jumps to next player.
3. Completely non-related card. In that case, the player takes another card (dealt by computer) and then jumps to next player.

The game ends as soon as a player has no more cards in their hand

(__1__): If playing with poker cards, ignore the suit. Just check the card value (A, 2, 3, ..., J, Q, K)

(__2__): 13 (K) and 1 (A) and vice-versa is also considered one away
