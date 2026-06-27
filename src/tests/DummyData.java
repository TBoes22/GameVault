package tests;
import database.*;
import models.*;
import security.LoginHandler;

import java.time.LocalDate;

public class DummyData {
    public static void main(String[] args){
        PlayerHandler playerHandler = new PlayerHandler();
        playerHandler.createTable();
        LoginHandler loginHandler = new LoginHandler();

        Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        Player player4 = null;
        Player player5 = null;

        try {
            player1 = new Player(1, "Max Mustermann", "maxmustermann@mail.de", loginHandler.hashPW("testpassword1"));
            playerHandler.savePlayer(player1);
            player2 = new Player(2, "Marie Mustermann", "mariemustermann@mail.de", loginHandler.hashPW("testpassword2"));
            playerHandler.savePlayer(player2);
            player3 = new Player(3, "Peter Mustermann", "petermustermann@mail.de", loginHandler.hashPW("testpassword3"));
            playerHandler.savePlayer(player3);
            player4 = new Player(4, "Lea Mustermann", "leamustermann@mail.de", loginHandler.hashPW("testpassword4"));
            playerHandler.savePlayer(player4);
            player5 = new Player(5, "Hans Mustermann", "hansmustermann@mail.de", loginHandler.hashPW("testpassword5"));
            playerHandler.savePlayer(player5);
        }catch (Exception e){
            e.printStackTrace();
        }

        GameHandler gameHandler = new GameHandler();
        gameHandler.createTable();

        Game game1 = new Game(1245620, "Elden Ring", new Developer("From Software"), LocalDate.of(2022, 2, 25), new Genre("Souls-Like"), new Platform("PC"), 59.99, Status.COMPLETED);
        gameHandler.saveGame(game1);
        Game game2 = new Game(1085660, "Destiny 2", new Developer("Bungie"), LocalDate.of(2017, 9, 6), new Genre("Looter Shooter"), new Platform("PC"), 0.00, Status.COMPLETED);
        gameHandler.saveGame(game2);
        Game game3 = new Game(1962700, "Subnautica 2", new Developer("Unknown Worlds Entertainment"), LocalDate.of(2026, 5, 14), new Genre("Survival"), new Platform("PC"), 29.99, Status.WISHLIST);
        gameHandler.saveGame(game3);
        Game game4 = new Game(2183900, "Warhammer 40,000: Space Marine 2", new Developer("Saber Interactive"), LocalDate.of(2024, 9, 9), new Genre("Third-Person Shooter"), new Platform("PC"), 59.99, Status.SOLD);
        gameHandler.saveGame(game4);
        Game game5 = new Game(1091500, "Cyberpunk 2077", new Developer("CD PROJEKT RED"), LocalDate.of(2020, 12, 10), new Genre("Cyberpunk"), new Platform("Playstation 5"), 59.99, Status.COMPLETED);
        gameHandler.saveGame(game5);
        Game game6 = new Game(1794680, "Vampire Survivors", new Developer("poncle"), LocalDate.of(2022, 10, 22), new Genre("Rougelike"), new Platform("PC"), 4.99, Status.COMPLETED);
        gameHandler.saveGame(game6);

        WishlistHandler wishlistHandler = new WishlistHandler();
        wishlistHandler.createTable();

        wishlistHandler.addGameToWishlist(player1.getPlayerId(),new Game(373420, "Divinity: Original Sin", new Developer("Larian Studios"), new Platform("PC"), 39.99));
        wishlistHandler.addGameToWishlist(player2.getPlayerId(), new Game(2868840, "Slay the Spire 2", new Developer("Mega Crit"), new Platform("PC"), 22.99));
        wishlistHandler.addGameToWishlist(player3.getPlayerId(), new Game(3768760, "007 First Light", new Developer("IO Interactive"), new Platform("PC"),69.99));
        wishlistHandler.addGameToWishlist(player4.getPlayerId(), new Game(620980, "Beat Saber", new Developer("Beat Games"), new Platform("VR Headset"), 29.99));
        wishlistHandler.addGameToWishlist(player5.getPlayerId(), new Game(2483190, "Forza Horizon 6", new Developer("Playground Games"), new Platform("Xbox"),69.99));



        ReviewHandler reviewHandler = new ReviewHandler();
        reviewHandler.createTable();

        Review review1 = new Review(9999, 1, 1245620, 10, "Masterwork!");
        reviewHandler.saveReview(review1, 1, 1245620);
        Review review2 = new Review(9998, 2, 1085660, 10, "Has its problems but nothing comes close to the gunplay and endgame-activities with friends.");
        reviewHandler.saveReview(review2, 2, 1085660);
        Review review3 = new Review(9997, 3, 2183900, 7, "Solid Third-Person Shooter. Would Recommend!");
        reviewHandler.saveReview(review3, 3, 2183900);
        Review review4 = new Review(9996, 4, 1091500, 9, "The Story is close to perfect and the gameplay is so fun.");
        reviewHandler.saveReview(review4, 4, 1091500);
        Review review5 = new Review(9995, 5, 1794680, 10, "Most addicting bullethell rougelike you will ever play.");
        reviewHandler.saveReview(review5, 5, 1794680);
    }
}
