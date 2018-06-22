/**
 *   Cascard  Copyright (C) 2018  Atoiks-Games <atoiks-games@outlook.com>
 *   This program comes with ABSOLUTELY NO WARRANTY;
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions;
 */

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