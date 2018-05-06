package org.atoiks.games.cascard;

import java.util.Random;

import java.util.function.Supplier;

public class CardGenerator implements Supplier<Card> {

    private final Random gen = new Random();

    @Override
    public Card get() {
        // Returns Card[id=[1, 13]]
        return new Card(gen.nextInt(13) + 1);
    }
}