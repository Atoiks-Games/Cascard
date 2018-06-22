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