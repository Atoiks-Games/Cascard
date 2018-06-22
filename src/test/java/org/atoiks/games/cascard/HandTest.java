/**
 *   Cascard  Copyright (C) 2018  Atoiks-Games <atoiks-games@outlook.com>
 *   This program comes with ABSOLUTELY NO WARRANTY;
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions;
 */

package org.atoiks.games.cascard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HandTest {

    @Test
    public void defaultHandSizeIs7() {
        assertEquals(7, new Hand().getHandSize());
    }

    @Test
    public void defaultHandIsEmpty() {
        final Hand hand = new Hand();
        assertTrue(hand.isEmpty());
        assertEquals(0, hand.getRemainingCards());
    }

    @Test
    public void fillOnEmptyReturnsHandSize() {
        final Hand h = new Hand();
        assertEquals(h.getHandSize(), h.fillHand(() -> null));
    }

    @Test
    public void playCardRemovesCardFromHand() {
        final Hand h = new Hand();
        assertTrue(h.takeCard(new Card(1)));
        assertTrue(h.takeCard(new Card(2)));
        assertTrue(h.takeCard(new Card(3)));

        assertEquals(new Card(1), h.playFirstCard());
        assertEquals(new Card(3), h.playLastCard());
        assertEquals(1, h.getRemainingCards());
    }

    @Test
    public void clearHandMakesHandEmpty() {
        final Hand h = new Hand();
        h.fillHand(() -> new Card(0));
        assertFalse(h.isEmpty());

        h.clearHand();
        assertTrue(h.isEmpty());
    }

    @Test
    public void forEachCardVisitsNonNullCards() {
        final Hand h = new Hand();
        h.takeCard(null);
        h.takeCard(new Card(1));
        h.takeCard(null);

        final boolean[] ref = { false };
        h.forEachCard(card -> {
            if (new Card(1).equals(card)) {
                ref[0] = true;
            }
        });

        assertTrue(ref[0]);
    }

    @Test
    public void compactShiftsNulls() {
        final Hand h = new Hand();
        h.takeCard(new Card(1));
        h.takeCard(new Card(2));
        h.takeCard(new Card(3));
        h.takeCard(new Card(4));
        h.takeCard(new Card(5));

        assertNotNull(h.playCard(1));
        assertNotNull(h.playCard(2));
        assertEquals(3, h.getRemainingCards());

        assertNull(h.playCard(1));
        assertNull(h.playCard(2));

        h.compact();
        assertEquals(new Card(1), h.playCard(0));
        assertEquals(new Card(4), h.playCard(1));
        assertEquals(new Card(5), h.playCard(2));
        assertTrue(h.isEmpty());
    }
}