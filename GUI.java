
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JPanel {
    static ArrayList userHand = new ArrayList();
    static ArrayList opponentHand1 = new ArrayList();
    static ArrayList teammateHand = new ArrayList();
    static ArrayList opponentHand2 = new ArrayList();
    static ArrayList restOfCards = new ArrayList();
    static Player p1;
    static Player p2;
    static Player p3;
    static Player p4;
    CardPanel board;
    JPanel buttonPanel;
    JLabel cardSelect;
    JTextField text;
    JButton playButton;


    public static void main(String[] args) {
        JFrame window = new JFrame("South FL Spades");
        GUI content = new GUI();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(120, 70);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        Game game = new Game();

        p1 = new Player(userHand);
        p2 = new Player(opponentHand1);
        p3 = new Player(teammateHand);
        p4 = new Player(opponentHand2);
        game.startGame(p1, p2, p3, p4);
    }

    public GUI() {
        Spades spades = new Spades();
        spades.shuffleDeck();
        Deck deck = new Deck();
        deck.dealDeck();
        userHand = deck.getHand1();
        opponentHand1 = deck.getHand2();
        teammateHand = deck.getHand3();
        opponentHand2 = deck.getHand4();

        setBackground(new Color(130, 50, 40));
        setLayout(new BorderLayout(3, 3));

        board = new CardPanel();
        add(board, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 200, 180));
        add(buttonPanel, BorderLayout.SOUTH);

        cardSelect = new JLabel("Select index of card to play: ");
        text = new JTextField(5);
        playButton = new JButton("OK");
        playButton.setActionCommand("Click");
        playButton.addActionListener(board);

        buttonPanel.add(cardSelect);
        buttonPanel.add(text);
        buttonPanel.add(playButton);

        setBorder(BorderFactory.createLineBorder(new Color(130, 50, 40), 3));

    }

    private class CardPanel extends JPanel implements ActionListener {
        String message;  // A message drawn on the canvas
        Font bigFont;      // Font that will be used to display the message.

        CardPanel() {
            setBackground(new Color(0, 120, 0));
            setForeground(Color.GREEN);
            bigFont = new Font("Serif", Font.BOLD, 14);
            setPreferredSize(new Dimension(1200, 400));
            message = "Your hand:";
            repaint();
        }

        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
            int index;
            if (command.equals("Click")) {
                index = Integer.parseInt(text.getText());
                System.out.println("\n***FROM GUI*** you played card at index " + index);
            }
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(bigFont);
            g.drawString(message, 190, 335);

            for (int i = 0; i < userHand.size(); i++) {
                drawCard(g, (Integer) userHand.get(i), 280 + i * 50, 280);
            }

            //could not access the array of cards played each play and display
            //that in the middle of the table
            for (int i = 0; i < restOfCards.size(); i++) {
                drawCard(g, (Integer) restOfCards.get(i), 280 + i * 50, 80);
            }
        }

        //transforms the integer value given to cards into
        //the cards values and suits that we are accustomed to
        void drawCard(Graphics g, int card, int x, int y) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 80, 100);
            g.setColor(Color.GRAY);
            g.drawRect(x, y, 79, 99);
            g.drawRect(x + 1, y + 1, 77, 97);

            String hearts = "\u2665";
            String diamonds = "\u2666";
            String spades = "\u2660";
            String clubs = "\u2663";

            //if card is hearts or diamonds
            if (card > 12 && card < 26 || card > 38)
                g.setColor(Color.RED);
            else
                g.setColor(Color.BLACK);

            String val;

            if (card == 0 || card == 13 || card == 26 || card == 39)
                val = String.valueOf(3);
            else if (card == 1 || card == 14 || card == 27 || card == 40)
                val = String.valueOf(4);
            else if (card == 2 || card == 15 || card == 28 || card == 41)
                val = String.valueOf(5);
            else if (card == 3 || card == 16 || card == 29 || card == 42)
                val = String.valueOf(6);
            else if (card == 4 || card == 17 || card == 30 || card == 43)
                val = String.valueOf(7);
            else if (card == 5 || card == 18 || card == 31 || card == 44)
                val = String.valueOf(8);
            else if (card == 6 || card == 19 || card == 32 || card == 45)
                val = String.valueOf(9);
            else if (card == 7 || card == 20 || card == 33 || card == 46)
                val = String.valueOf(10);
            else if (card == 8 || card == 21 || card == 34 || card == 47)
                val = "J";
            else if (card == 9 || card == 22 || card == 35 || card == 48)
                val = "Q";
            else if (card == 10 || card == 23 || card == 36 || card == 49)
                val = "K";
            else if (card == 11 || card == 24 || card == 37 || card == 50)
                val = "A";
            else
                val = String.valueOf(2);

            g.drawString(val, x + 10, y + 30);
            if (card < 13)
                g.drawString(spades, x + 25, y + 30);
            else if (card < 26)
                g.drawString(diamonds, x + 25, y + 30);
            else if (card < 39)
                g.drawString(clubs, x + 25, y + 30);
            else
                g.drawString(hearts, x + 25, y + 30);
        }
    }
}