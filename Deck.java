import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList deck;
    private ArrayList hand1;
    private ArrayList hand2;
    private ArrayList hand3;
    private ArrayList hand4;

    public Deck() {
        deck = new ArrayList<>();
        //create a deck with integers 0-51
        for(int i=0; i<52; i++) {
            deck.add(i);
        }
    }

    public void dealDeck(){
        hand1 = new ArrayList<>();
        hand2 = new ArrayList<>();
        hand3 = new ArrayList<>();
        hand4 = new ArrayList<>();

        System.out.println("Dealing deck...");
        Random rand = new Random();
        int i = 52;
        int x,y,u,v;
        while (deck.size()!=0) {
            //get random card from deck and place it in each deck
            x = rand.nextInt(i);
            i--;
            hand1.add(deck.get(x));
            deck.remove(x);

            y = rand.nextInt(i);
            i--;
            hand2.add(deck.get(y));
            deck.remove(y);

            u = rand.nextInt(i);
            i--;
            hand3.add(deck.get(u));
            deck.remove(u);

            v = rand.nextInt(i);
            i--;
            hand4.add(deck.get(v));
            deck.remove(v);
        }

        //print out every hand
        System.out.println("***All hands displayed for testing purposes***");
        System.out.println("Your Hand: " + hand1);
        System.out.println("Opponent Hand: " + hand2);
        System.out.println("Teammate Hand: " + hand3);
        System.out.print("Opponent Hand2: " + hand4);

    }

    public ArrayList getHand1() {
        return hand1;
    }

    public ArrayList getHand2() {
        return hand2;
    }

    public ArrayList getHand3() {
        return hand3;
    }

    public ArrayList getHand4() {
        return hand4;
    }
}
