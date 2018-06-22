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

package org.atoiks.games.cascard.environment;

import org.atoiks.games.cascard.Card;
import org.atoiks.games.cascard.Game;
import org.atoiks.games.cascard.Hand;

import java.util.Vector;
import java.util.function.Supplier;

import java.awt.BorderLayout;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class SwingGame extends Game {

    @Override
    public boolean init() {
        int inp = -1;
        while (inp < 2) {
            try {
                final String raw = JOptionPane.showInputDialog(
                        null,
                        "How many players are playing? (minimum of 2)",
                        "Game Configuration",
                        JOptionPane.QUESTION_MESSAGE);

                if (raw == null) {
                    // Player hit the exit button, quit
                    return false;
                }
                inp = Integer.parseInt(raw);
            } catch (NumberFormatException ex) {
                // restart the loop, ask for input again
            }
        }

        final Hand[] players = new Hand[inp];
        for (int i = 0; i < players.length; ++i) {
            players[i] = new Hand();
        }
        this.setPlayers(players);

        return super.init();
    }

    @Override
    protected Card pickCard(final int id, final Hand player) {
        final int opt = JOptionPane.showOptionDialog(
                null,
                "Player " + (id + 1) + ": Continue Game?",
                "Ready",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (opt == JOptionPane.NO_OPTION) {
            // Player gives up, end game
            return null;
        }

        final Vector<String> vector = new Vector<>();
        player.forEachCard(card -> vector.add(Integer.toString(card.id)));
        final JList<String> list = new JList<>(vector);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Top card is " + topCard.id + ". Please chose a card from below:"), BorderLayout.NORTH);
        panel.add(list, BorderLayout.CENTER);

        int[] indicies = {};
        while (indicies.length != 1) {
            JOptionPane.showMessageDialog(null, panel, "Player " + (id + 1) + ": Choose your card", JOptionPane.PLAIN_MESSAGE);
            indicies = list.getSelectedIndices();
        }
        player.compact();
        return player.playCard(indicies[0]);
    }

    @Override
    protected void broadcastWinner(final int id, final Hand player) {
        JOptionPane.showMessageDialog(null, "Player " + (id + 1) + " won!", "GG", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void close() {
    }
}