package org.atoiks.games.cascard;

import org.atoiks.games.cascard.environment.*;

import java.awt.GraphicsEnvironment;

public class Main {

    public static void main(String[] args) {
        final CardGenerator deck = new CardGenerator();
        final Game game = GraphicsEnvironment.isHeadless() ? new ConsoleGame() : new SwingGame();

        try {
            game.useDeck(deck);
            if (game.init()) {
                while (!game.hasEnded()) {
                    game.round();
                }
            }
        } finally {
            game.close();
        }
    }
}