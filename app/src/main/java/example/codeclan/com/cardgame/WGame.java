package example.codeclan.com.cardgame;

import java.util.ArrayList;

/**
 * Created by user on 21/01/2017.
 */

public class WGame {

    private WDeck deck;
    private ArrayList<Player> players;
    private int turnCount;
    private Player player1;
    private Player player2;
    private WTable table;

    public WGame() {
        this.deck = new WDeck();
        this.players = new ArrayList<>();
        this.turnCount = 0;
        this.player1 = new Player("Player");
        this.player2 = new Player("Android");
        this.table = new WTable();
        setupGame();
    }

    private void setupGame() {
        deck.distributeCards(player1, player2);
        players.add(player1);
        players.add(player2);
    }

    public int numOfPlayers() {
        return players.size();
    }

    public int getTurnCount() {
        return turnCount;
    }

    public WDeck getDeck() {
        return deck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public WTable getTable() {
        return table;
    }

    ////// not tested //////////

    public void singleCard() {
        table.addCardToTable(player1.giveCard());
        table.addCardToTable(player2.giveCard());
    }

    ////// not tested //////////

    public void doubleCard() {
        table.addCardToTable(player1.giveCard());
        table.addCardToTable(player1.giveCard());
        table.addCardToTable(player2.giveCard());
        table.addCardToTable(player2.giveCard());
    }

    ////// not tested //////////

    public void trippleCard() {
        table.addCardToTable(player1.giveCard());
        table.addCardToTable(player1.giveCard());
        table.addCardToTable(player1.giveCard());
        table.addCardToTable(player2.giveCard());
        table.addCardToTable(player2.giveCard());
        table.addCardToTable(player2.giveCard());
    }

    ////// not tested //////////

    public void higherCard(Player player) {
        table.moveCardsAside();
        table.giveSideCardsToPlayer(player);
    }

    //////////////////// GAME LOGIC /////////////////////

    public String play() {

        /// NEW TURN, EACH PLAYER PLAYS 1 CARD
        while (player1.cardCount() > 0 && player2.cardCount() > 0) {
            singleCard();

            /// BOTH PLAYERS HAVE MORE THAN 3 CARDS THEY PLAYED CARDS OF SAME VALUE
            /// AFTER SINGLE TURN AT THE BEGINNING OF WHILE LOOP
            if (player1.cardCount() >= 3 && player2.cardCount() >= 3
                    && table.getCardsOnTable().get(0).getValue() == table.getCardsOnTable().get(1).getValue()) {
                table.moveCardsAside();
                trippleCard();
                System.out.println("A");

                /// BOTH PLAYERS HAVE MORE THAN 3 CARDS THEY PLAYED CARDS OF SAME VALUE
                while (player1.cardCount() >= 3 && player2.cardCount() >= 3
                        && table.getCardsOnTable().get(2).getValue() == table.getCardsOnTable().get(5).getValue()) {
                    table.moveCardsAside();
                    trippleCard();
                    turnCount += 1;
                    System.out.println("B");
                }

                ///BOTH PLAYERS HAVE MORE THAN 2 CARDS AND THEY PLAYED CARDS OF SAME VALUE
                if (player1.cardCount() >= 2 && player2.cardCount() >= 2
                        && table.getCardsOnTable().get(2).getValue() == table.getCardsOnTable().get(5).getValue()) {
                    table.moveCardsAside();
                    doubleCard();
                    System.out.println("C");

                    if (table.getCardsOnTable().get(1).getValue() == table.getCardsOnTable().get(3).getValue()) {
                        table.moveCardsAside();
                        if (player1.cardCount() == 0 && player2.cardCount() == 0) {
                            table.distributeCardsFromSide(player1, player2);
                            System.out.println("D");
                        }
                        else if (player1.cardCount() > player2.cardCount()) {
                            table.giveSideCardsToPlayer(player1);
                            System.out.println("E");
                        }
                        else {
                            table.giveSideCardsToPlayer(player2);
                            System.out.println("F");
                        }
                    }

                    else if (table.getCardsOnTable().get(1).getValue() > table.getCardsOnTable().get(3).getValue()) {
                        higherCard(player1);
                        System.out.println("G");
                    }

                    else {
                        higherCard(player2);
                        System.out.println("H");
                    }
                    turnCount += 1;
                }

                /// BOTH PLAYERS HAVE MORE THAN 1 CARD AND THEY PLAYED CARDS OF SAME VALUE
                else if (player1.cardCount() >= 1 && player2.cardCount() >= 1
                        && table.getCardsOnTable().get(2).getValue() == table.getCardsOnTable().get(5).getValue()) {
                    table.moveCardsAside();
                    singleCard();
                    System.out.println("I");

                    if (table.getCardsOnTable().get(0).getValue() == table.getCardsOnTable().get(1).getValue()) {
                        table.moveCardsAside();
                        if (player1.cardCount() == 0 && player2.cardCount() == 0) {
                            table.distributeCardsFromSide(player1, player2);
                            System.out.println("J");
                        }
                        else if (player1.cardCount() > player2.cardCount()) {
                            table.giveSideCardsToPlayer(player1);
                            System.out.println("K");
                        }
                        else {
                            table.giveSideCardsToPlayer(player2);
                            System.out.println("L");
                        }
                    }

                    else if (table.getCardsOnTable().get(0).getValue() > table.getCardsOnTable().get(1).getValue()) {
                        higherCard(player1);
                        System.out.println("M");
                    }

                    else {
                        higherCard(player2);
                        System.out.println("N");
                    }
                    turnCount += 1;
                }

                /// ONE OF THE PLAYERS DOES NOT HAVE ANY MORE CARDS AND THEY PLAYED CARDS OF SAME VALUE
                else if (table.getCardsOnTable().get(2).getValue() == table.getCardsOnTable().get(5).getValue()) {
                    table.moveCardsAside();
                    if (player1.cardCount() == 0 && player2.cardCount() == 0) {
                        table.distributeCardsFromSide(player1, player2);
                        System.out.println("O");
                    }
                    else if (player1.cardCount() > player2.cardCount()) {
                        table.giveSideCardsToPlayer(player1);
                        System.out.println("P");
                    }
                    else {
                        table.giveSideCardsToPlayer(player2);
                        System.out.println("Q");
                    }
                    turnCount += 1;
                }

                /// FIRST PLAYER PLAYED HIGHER CARD
                else if (table.getCardsOnTable().get(2).getValue() > table.getCardsOnTable().get(5).getValue()) {
                    higherCard(player1);
                    System.out.println("R");
                }

                /// SECOND PLAYER PLAYED HIGHER CARD
                else {
                    higherCard(player2);
                    System.out.println("S");
                }
                turnCount += 1;
            }

            /// BOTH PLAYERS HAVE MORE THAN 2 CARDS AND THEY PLAYED CARDS OF SAME VALUE
            /// AFTER SINGLE TURN AT THE BEGINNING OF WHILE LOOP
            else if (player1.cardCount() >= 2 && player2.cardCount() >= 2
                    && table.getCardsOnTable().get(0).getValue() == table.getCardsOnTable().get(1).getValue()) {
                table.moveCardsAside();
                doubleCard();
                System.out.println("T");

                if (table.getCardsOnTable().get(1).getValue() == table.getCardsOnTable().get(3).getValue()) {
                    table.moveCardsAside();
                    if (player1.cardCount() == 0 & player2.cardCount() == 0) {
                        table.distributeCardsFromSide(player1, player2);
                        System.out.println("U");
                    }
                    else if (player1.cardCount() > player2.cardCount()) {
                        table.giveSideCardsToPlayer(player1);
                        System.out.println("V");
                    }
                    else {
                        table.giveSideCardsToPlayer(player2);
                        System.out.println("W");
                    }
                }

                else if (table.getCardsOnTable().get(1).getValue() > table.getCardsOnTable().get(3).getValue()) {
                    higherCard(player1);
                    System.out.println("X");
                }

                else {
                    higherCard(player2);
                    System.out.println("Y");
                }
                turnCount += 1;
            }

            /// BOTH PLAYERS HAVE MORE THAN 1 CARD THEY PLAYED CARDS OF SAME VALUE
            /// AFTER SINGLE TURN AT THE BEGINNING OF WHILE LOOP
            else if (player1.cardCount() >= 1 && player2.cardCount() >= 1
                    && table.getCardsOnTable().get(0).getValue() == table.getCardsOnTable().get(1).getValue()) {
                table.moveCardsAside();
                singleCard();
                System.out.println("Z");
                if (table.getCardsOnTable().get(0).getValue() == table.getCardsOnTable().get(1).getValue()) {

                    if (player1.cardCount() == 0 && player2.cardCount() == 0) {
                        table.distributeCardsFromSide(player1, player2);
                        System.out.println("1");
                    }
                    else if (player1.cardCount() > player2.cardCount()) {
                        table.giveSideCardsToPlayer(player1);
                        System.out.println("2");
                    }
                    else {
                        table.giveSideCardsToPlayer(player2);
                        System.out.println("3");
                    }
                }

                else if (table.getCardsOnTable().get(0).getValue() > table.getCardsOnTable().get(1).getValue()) {
                    higherCard(player1);
                    System.out.println("4");
                }

                else {
                    higherCard(player2);
                    System.out.println("5");
                }
                turnCount += 1;
            }

            /// BOTH PLAYERS PLAYED CARDS OF SAME VALUE BUT AT LEAST ONE OF THEM HAS NO MORE CARDS
            /// SINGLE TURN AT THE BEGINNING OF WHILE LOOP
            else if (table.getCardsOnTable().get(0).getValue() == table.getCardsOnTable().get(1).getValue()) {
                table.moveCardsAside();
                if (player1.cardCount() == 0 & player2.cardCount() == 0) {
                    table.distributeCardsFromSide(player1, player2);
                    System.out.println("5");
                }
                else if (player1.cardCount() > player2.cardCount()) {
                    table.giveSideCardsToPlayer(player1);
                    System.out.println("6");
                }
                else {
                    table.giveSideCardsToPlayer(player2);
                    System.out.println("7");
                }
                turnCount += 1;
            }

            /// FIRST PLAYER PLAYED HIGHER CARD
            else if (table.getCardsOnTable().get(0).getValue() > table.getCardsOnTable().get(1).getValue()) {
                higherCard(player1);
                System.out.println("8");
            }

            /// SECOND PLAYER PLAYED HIGHER CARD
            else {
                higherCard(player2);
                System.out.println("9");
            }
            turnCount += 1;
        } /*end of main while loop*/

        if (player1.cardCount() > player2.cardCount()) {
            System.out.println("After " + turnCount + " turns You won the game of War! Let your little victory forever stay in the statistics of this device!");

        }
        else {
            System.out.println("After " + turnCount + " turns Android won the game of War! Let his little victory forever stay in the statistics of this device!");

        }
        System.out.println(player1.getName() + " " + player1.cardCount());
        System.out.println(player2.getName() + " " + player2.cardCount());
        String over = "Game over";
        return over;
    }




}