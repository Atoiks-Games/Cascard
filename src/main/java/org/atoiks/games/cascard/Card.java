/**
 *   Cascard  Copyright (C) 2018  Atoiks-Games <atoiks-games@outlook.com>
 *   This program comes with ABSOLUTELY NO WARRANTY;
 *   This is free software, and you are welcome to redistribute it
 *   under certain conditions;
 */

package org.atoiks.games.cascard;

import java.io.Serializable;

public final class Card implements Serializable, Comparable<Card> {

    private static final long serialVersionUID = 98621238323246L;

    public final int id;

    public Card(final int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + '[' + id + ']';
    }

    @Override
    public int compareTo(final Card card) {
        return id - card.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() == this.getClass()) {
            return id == ((Card) obj).id;
        }
        return false;
    }
}