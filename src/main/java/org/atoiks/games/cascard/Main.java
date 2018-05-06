package org.atoiks.games.cascard;

public class Main {

    public static void main(String[] args) {
        final CardGenerator deck = new CardGenerator();
        final Hand p1 = new Hand();
        final Hand p2 = new Hand();

        final Game game = new ConsoleGame(deck, p1, p2);
        game.init();
        while (!game.hasEnded()) game.round();
        game.close();
    }
}