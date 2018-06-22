/**
 *  Cascard
 *  Copyright (C) 2018  Atoiks-Games <atoiks-games@outlook.com>

 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.atoiks.games.cascard;

import java.util.function.Supplier;

public abstract class Game {

    private int lastPlayer;
    private Hand[] players;
    private boolean ended;
    private Supplier<Card> deck;

    protected Card topCard;

    public final void useDeck(final Supplier<Card> deck) {
        this.deck = deck;
    }

    protected final void setPlayers(final Hand[] players) {
        this.lastPlayer = -1;
        this.players = players;
    }

    protected final void dealCards() {
        for (final Hand h : players) {
            h.fillHand(deck);
        }
    }

    protected final void endGame() {
        ended = true;
    }

    public boolean init() {
        dealCards();
        topCard = deck.get();
        return true;
    }

    public final void round() {
        lastPlayer = (lastPlayer + 1) % players.length;
        final Hand player = players[lastPlayer];
        final Card card = pickCard(lastPlayer, player);
        if (card == null) {
            endGame();
            return;
        }

        final int dist = card.compareTo(topCard);
        switch (dist) {
            case 1: case -1:    // top: 3, player: 2 or 4
            case 12: case -12:  // top: 1, player: 13 (or 13-1)
                // Check if player is out of cards (win)
                if (player.isEmpty()) {
                    broadcastWinner(lastPlayer, player);
                    endGame(); return;
                }
                break;
            case 0:             // top: 8, player: 8 (k-k)
                if (player.isEmpty()) {
                    // feed another card
                    player.takeCard(deck.get());
                }
                // The player immediately goes again
                --lastPlayer;
                break;
            default:
                // player has to draw a card from the deck
                player.takeCard(deck.get());
        }

        // Either way, the card played by the player is now the new top card.
        topCard = card;
    }

    protected abstract Card pickCard(int id, Hand hand);

    protected abstract void broadcastWinner(int id, Hand hand);

    public void close() {
        //
    }

    public boolean hasEnded() {
        return ended;
    }
}