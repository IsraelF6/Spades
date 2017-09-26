import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    int yourBooks = 0;
    int enemyBooks = 0;
    ArrayList cardsPlayed = new ArrayList<>();

    public void startGame(Player p1, Player p2, Player p3, Player p4){
        int currentTurn;
        int opener;
        int lastWinner = 0;
        boolean turnTaken;
        boolean hasCard;
        int selection;
        Scanner user = new Scanner(System.in);
        int suit; //0= Spades, 1=Diamonds, 2=Clubs, 3=Hearts

        //Player with 37 (Ace of Clubs) goes first
        if (p1.getPlayerHand().contains(37)) {
            currentTurn = 0;
            System.out.print("\nYour Hand: " + p1.getPlayerHand() + "\nMake Selection: ");
            selection = user.nextInt();
            while (selection != p1.getPlayerHand().indexOf(37)) {
                System.err.println("\nNope, must select A of Clubs (37)");
                System.out.print("Make Selection: ");
                selection = user.nextInt();
                }

            //remove card from hand
            p1.getPlayerHand().remove(p1.getPlayerHand().indexOf(37));
        }
        else if (p2.getPlayerHand().contains(37)) {
            currentTurn = 1;
            p2.getPlayerHand().remove(p2.getPlayerHand().indexOf(37));
        }
        else if (p3.getPlayerHand().contains(37)) {
            currentTurn = 2;
            p3.getPlayerHand().remove(p3.getPlayerHand().indexOf(37));
        }
        else {
            currentTurn = 3;
            p4.getPlayerHand().remove(p4.getPlayerHand().indexOf(37));
        }


        //save the person to go first
        opener = currentTurn;

        //add card to list of cards played this book
        cardsPlayed.add(37);
        //suit = clubs
        suit = 2;
        currentTurn++;

        while (yourBooks < 7 && enemyBooks < 7) {
            if (yourBooks + enemyBooks > 0) {
                cardsPlayed = new ArrayList();
                //player1's turn
                if (lastWinner == 0) {
                    System.out.println("\n=========================================\n" +
                            "list of cards played is: " + cardsPlayed +
                            "\n=========================================");

                    System.out.print("\nyour hand: " + p1.getPlayerHand() + "\nMake Selection: ");
                    selection = user.nextInt();
                    while (selection > p1.getPlayerHand().size() - 1) {
                        System.err.println("Index chosen is out of bounds, try again");
                        System.out.print("Make Selection: ");
                        selection = user.nextInt();
                    }
                    cardsPlayed.add(p1.getPlayerHand().get(selection));
                    p1.getPlayerHand().remove(p1.getPlayerHand().indexOf(p1.getPlayerHand().get(selection)));

                }

                //if cpu goes first, plays the first card in their hand
                else if (lastWinner == 1) {
                    cardsPlayed.add(p2.getPlayerHand().get(0));
                    p2.getPlayerHand().remove(0);
                } else if (lastWinner == 2) {
                    cardsPlayed.add(p3.getPlayerHand().get(0));
                    p3.getPlayerHand().remove(0);

                } else if (lastWinner == 3) {
                    cardsPlayed.add(p4.getPlayerHand().get(0));
                    p4.getPlayerHand().remove(0);
                }

                //find suit played by the first player that the others must follow
                currentTurn = lastWinner + 1;
                int check;
                suit = 0;
                for (int i = 0; i < 51; i++) {
                    if (cardsPlayed.contains(i)) {
                        check = i;
                        while (check > 12) {
                            check -= 13;
                            suit++;
                        }
                    }
                }
            }

                while (cardsPlayed.size() != 4) {
                    turnTaken = false;
                    hasCard = false;
                    currentTurn = currentTurn % 4;

                    //player1's turn
                    if (currentTurn == 0) {
                        System.out.println("\n=========================================\n" +
                                "list of cards played is: " + cardsPlayed +
                                "\n=========================================");

                        System.out.print("\nyour hand: " + p1.getPlayerHand() + "\nMake Selection: ");
                        //play legal index
                        selection = user.nextInt();
                        while (selection > p1.getPlayerHand().size() - 1) {
                            System.err.println("Index chosen is out of bounds");
                            System.out.print("Make Selection: ");
                            selection = user.nextInt();
                        }

                        //play suit
                        for (int i = 0; i < 13; i++) {
                            if (p1.getPlayerHand().contains(suit * 13 + i)) {
                                hasCard = true;
                                if (p1.getPlayerHand().indexOf(suit * 13 + i) == p1.getPlayerHand().indexOf(p1.getPlayerHand().get(selection))) {
                                    //if player has card in suit
                                    //if index of that card = index of card at selected index
                                    cardsPlayed.add(suit * 13 + i);
                                    p1.getPlayerHand().remove(p1.getPlayerHand().indexOf(suit * 13 + i));
                                    turnTaken = true;
                                    break;
                                }
                            }
                        }

                        while (hasCard && !turnTaken) {
                            System.err.println("Must play the right suit and choose a valid index!");
                            System.out.print("Make Selection: ");
                            selection = user.nextInt();

                            //legal index is chosen
                            if (selection <= p1.getPlayerHand().size() - 1) {
                                for (int i = 0; i < 13; i++) {

                                    if (p1.getPlayerHand().contains(suit * 13 + i)) {
                                        if (p1.getPlayerHand().indexOf(suit * 13 + i) == p1.getPlayerHand().indexOf(p1.getPlayerHand().get(selection))) {
                                            //if player has card in suit
                                            //if index of that card = index of card at selected index
                                            cardsPlayed.add(suit * 13 + i);
                                            p1.getPlayerHand().remove(p1.getPlayerHand().indexOf(suit * 13 + i));
                                            turnTaken = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        //if hand does not contain that necessary suit, any card can be played
                        if (!turnTaken) {
                            cardsPlayed.add(p1.getPlayerHand().get(selection));
                            p1.getPlayerHand().remove(selection);
                        }
                        currentTurn++;
                    }

                    //player2's turn
                    else if (currentTurn == 1) {
                        for (int i = 0; i < 13; i++) {
                            if (p2.getPlayerHand().contains(suit * 13 + i)) {
                                cardsPlayed.add(suit * 13 + i);
                                p2.getPlayerHand().remove(p2.getPlayerHand().indexOf(suit * 13 + i));
                                turnTaken = true;
                                break;
                            }
                        }
                        if (!turnTaken) {
                            cardsPlayed.add(p2.getPlayerHand().get(0));
                            p2.getPlayerHand().remove(0);
                        }
                        currentTurn++;
                    }


                    //player3's turn
                    else if (currentTurn == 2) {
                        for (int i = 0; i < 13; i++) {
                            if (p3.getPlayerHand().contains(suit * 13 + i)) {
                                cardsPlayed.add(suit * 13 + i);
                                p3.getPlayerHand().remove(p3.getPlayerHand().indexOf(suit * 13 + i));
                                turnTaken = true;
                                break;
                            }
                        }
                        if (!turnTaken) {
                            cardsPlayed.add(p3.getPlayerHand().get(0));
                            p3.getPlayerHand().remove(0);
                        }
                        currentTurn++;
                    }

                    //player4's turn
                    else {
                        for (int i = 0; i < 13; i++) {
                            if (p4.getPlayerHand().contains(suit * 13 + i)) {
                                cardsPlayed.add(suit * 13 + i);
                                p4.getPlayerHand().remove(p4.getPlayerHand().indexOf(suit * 13 + i));
                                turnTaken = true;
                                break;
                            }
                        }

                        if (!turnTaken) {
                            cardsPlayed.add(p4.getPlayerHand().get(0));
                            p4.getPlayerHand().remove(0);
                        }
                        currentTurn++;
                    }
                }


            System.out.println("\n=========================================\n" +
                    "list of cards played so far is: " + cardsPlayed +
                    "\n=========================================");
            lastWinner = checkWinner (cardsPlayed, opener);
            opener = lastWinner;

            System.out.println("Your Hand = " + p1.getPlayerHand());
            System.out.println("Opponent Hand = " + p2.getPlayerHand());
            System.out.println("Teammate Hand = " + p3.getPlayerHand());
            System.out.print("Opponent Hand2 = " + p4.getPlayerHand());
        }

        if (yourBooks == 7)
            System.out.println("\n=========================================\n" +
                    "CONGRATS!\nYour team wins " + yourBooks + "-" + enemyBooks +
                    "\n=========================================");
        else if (enemyBooks == 7)
            System.out.println("\n=========================================\n" +
                    "Team 2 wins! " + enemyBooks +"-" + yourBooks +
                    "\n=========================================");

        System.exit(1);
    }

    public int checkWinner(ArrayList cardsPlayed, int opener) {

        //convert values of each card played from an ArrayList to ints
        String firstCard = cardsPlayed.subList(0,1).toString();
        StringBuilder sb1 = new StringBuilder();
        int[] temp1 = new int[2];
        for (int i=0; i<firstCard.length()-2; i++) {
            temp1[i] = firstCard.charAt(i + 1);
            temp1[i] -= 48;
            String item = String.valueOf(temp1[i]);
            sb1.append(item);
        }
        int val1 = Integer.parseInt(String.valueOf(sb1));


        String secondCard = cardsPlayed.subList(1,2).toString();
        StringBuilder sb2 = new StringBuilder();
        int[] temp2 = new int[2];
        for (int i=0; i<secondCard.length()-2; i++) {
            temp2[i] = secondCard.charAt(i + 1);
            temp2[i] -= 48;
            String item = String.valueOf(temp2[i]);
            sb2.append(item);
        }
        int val2 = Integer.parseInt(String.valueOf(sb2));


        String thirdCard = cardsPlayed.subList(2,3).toString();
        StringBuilder sb3 = new StringBuilder();
        int[] temp3 = new int[2];
        for (int i=0; i<thirdCard.length()-2; i++) {
            temp3[i] = thirdCard.charAt(i + 1);
            temp3[i] -= 48;
            String item = String.valueOf(temp3[i]);
            sb3.append(item);
        }
        int val3 = Integer.parseInt(String.valueOf(sb3));


        String fourthCard = cardsPlayed.subList(3,4).toString();
        StringBuilder sb4 = new StringBuilder();
        int[] temp4 = new int[2];
        for (int i=0; i<fourthCard.length()-2; i++) {
            temp4[i] = fourthCard.charAt(i + 1);
            temp4[i] -= 48;
            String item = String.valueOf(temp4[i]);
            sb4.append(item);
        }
        int val4 = Integer.parseInt(String.valueOf(sb4));
        int[] thisPlay = new int[4];
        thisPlay[0] = val1;
        thisPlay[1] = val2;
        thisPlay[2] = val3;
        thisPlay[3] = val4;

        int winner = 0;

        //first card played is a heart
        if (thisPlay[0] > 38) {
            winner = 0;
            for (int i=1; i<4; i++) {
                //check if a higher heart is played
                if (thisPlay[i] > thisPlay[0])
                    winner = i;
            }
        }

        //first card played is a club
        else if (thisPlay[0] > 25 && thisPlay[0] < 38) {
            winner = 0;
            for (int i = 1; i < 4; i++) {
                //check if a higher heart is played
                if (thisPlay[i] > thisPlay[0])
                    winner = i;
            }
        }

        //first card played is a diamond
        else if (thisPlay[0] > 12 && thisPlay[0] <25) {
            winner = 0;
            for (int i=1; i<4; i++) {
                //check if a higher heart is played
                if (thisPlay[i] > thisPlay[0])
                    winner = i;
            }
        }

        int max = 0;
        //check for spades
        for (int i=0; i<4; i++) {
            if (thisPlay[i] < 12 && thisPlay[i] > max) {
                max = thisPlay[i];
                winner = i;
            }
        }


        //if 2's are played
        for (int i=0; i<4; i++) {
            //2 of spades
            if (thisPlay[i] == 12) {
                winner = i;
            }
        }

        for (int i=0; i<4; i++) {
            //2 of diamonds
            if (thisPlay[i] == 25) {
                winner = i;
        }
        }

        for (int i=0; i<4; i++) {
            //2 of clubs
            if (thisPlay[i] == 38) {
                winner = i;
            }
        }

        for (int i=0; i<4; i++) {
            //2 of hearts (beats all other cards)
            if (thisPlay[i] == 51) {
                winner = i;
            }
        }

        winner = winner%4;

        if (winner == 0 || winner == 2)
            yourBooks++;

        else
            enemyBooks++;

        //add index of who played first with index of player that won the book
        //to get the index of who should go first next turn
        int end = winner+opener;
        if (end>=4)
            end = end-4;
        System.out.println("winning card is " + thisPlay[winner]);
        System.out.println("num books for your team: " + yourBooks);
        System.out.println("num books for the other team: " + enemyBooks);

        return end;
    }
}
