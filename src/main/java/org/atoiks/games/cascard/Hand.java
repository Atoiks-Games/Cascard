/**
 *  Tron
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

import java.util.Arrays;

import java.util.function.Consumer;
import java.util.function.Supplier;

import java.io.Serializable;

public final class Hand implements Serializable {

    private static final long serialVersionUID = 73153847253L;

    private final Card[] buffer;

    public Hand() {
        this(7);
    }

    public Hand(final int handSize) {
        this.buffer = new Card[handSize];
    }

    public int getHandSize() {
        return buffer.length;
    }

    public int getRemainingCards() {
        int k = 0;
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] != null) ++k;
        }
        return k;
    }

    public boolean isEmpty() {
        return getRemainingCards() == 0;
    }

    public void clearHand() {
        Arrays.fill(buffer, null);
    }

    public Card playCard(final int index) {
        final Card k = buffer[index];
        buffer[index] = null;
        return k;
    }

    public Card playFirstCard() {
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] != null) return playCard(i);
        }
        return null;
    }

    public Card playLastCard() {
        for (int i = buffer.length; i > 0; --i) {
            if (buffer[i - 1] != null) return playCard(i - 1);
        }
        return null;
    }

    public void compact() {
        for (int i = 0, k = 0; i < buffer.length; ++i) {
            if (buffer[i] != null) {
                if (i != k) {
                    buffer[k] = buffer[i];
                    buffer[i] = null;
                }
                ++k;
            }
        }
    }

    public boolean takeCard(final Card newCard) {
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] == null) {
                buffer[i] = newCard;
                return true;
            }
        }
        return false;
    }

    public int fillHand(final Supplier<Card> deck) {
        int k = 0;
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] == null) {
                buffer[i] = deck.get();
                ++k;
            }
        }
        return k;
    }

    public void forEachCard(final Consumer<? super Card> f) {
        for (int i = 0; i < buffer.length; ++i) {
            if (buffer[i] != null) f.accept(buffer[i]);
        }
    }
}