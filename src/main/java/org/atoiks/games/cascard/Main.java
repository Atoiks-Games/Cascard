package org.atoiks.games.cascard;

public class Main {

    public static void main(String[] args) {
        final CardGenerator deck = new CardGenerator();
        final Game game = new ConsoleGame(deck);

        if (game.init()) while (!game.hasEnded()) game.round();
        game.close();
    }
}