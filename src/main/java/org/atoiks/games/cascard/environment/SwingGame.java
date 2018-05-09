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

    public SwingGame(final Supplier<Card> deck) {
        this.deck = deck;
    }

    @Override
    public boolean init() {
        int inp = -1;
        while (inp < 2) {
            try {
                inp = Integer.parseInt(JOptionPane.showInputDialog(
                        null,
                        "How many players are playing? (minimum of 2)",
                        null,
                        JOptionPane.QUESTION_MESSAGE));
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
        JOptionPane.showMessageDialog(null, "Player " + (id + 1) + "'s turn", null, JOptionPane.INFORMATION_MESSAGE);
        final Vector<String> vector = new Vector<>();
        player.forEachCard(card -> vector.add(Integer.toString(card.id)));
        final JList<String> list = new JList<>(vector);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Top card is " + topCard.id + "\n\nPlease chose a card from below:"), BorderLayout.NORTH);
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
    protected void broadcastWinner(int id, Hand hand) {
        JOptionPane.showMessageDialog(null, "Player " + (id + 1) + " won!", null, JOptionPane.WARNING_MESSAGE);
    }


    @Override
    public void close() {
    }
}