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