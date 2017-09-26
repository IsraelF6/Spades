==================
      SPADES
==================

Made By: Israel Felhandler

Written in JAVA, this Spades game is a basic card game that consists of 1 user and 3 computer players. The user is player1 and his teammate is player 3, while the other team consists of player's 2 and 4. The deck is represented as an ArrayList with values 0-51, for each card. The equivalence for each value is given with the following charts:

0  1  2  3  4  5  6  7  8  9  10  11  12
--------------SPADES---------------------
3  4  5  6  7  8  9  10  J  Q  K   A  2

13 14 15 16 17 18 19 20 21 22 23 24 25
--------------DIAMONDS-------------------
3  4  5  6  7  8  9  10  J  Q  K  A  2

26 27 28 29 30 31 32 33 34 35 36 37 38
---------------CLUBS---------------------
3  4  5  6  7  8  9  10  J  Q  K  A  2

39 40 41 42 43 44 45 46 47 48 49 50 51
---------------HEARTS--------------------
3  4  5  6  7  8  9  10  J  Q  K  A  2

The game always starts with the player that has the Ace of clubs. After the first player plays a card, the 3 other players must play the same suit if they have it, otherwise, they can play any card. The players with the highest valued card of that suit wins that "book". Since there are 13 cards in each hand, the first team to 7 books wins the game. Spades trump any other suit, and each of the 2's is considered a Spade. The most powerful card in the game is the 2 of Hearts, followed by the 2 of Clubs, then the 2 of Diamonds, and the 2 of Spades. Illegal moves that lead to renigs (when you dont play the suit even though you have a card of that suit) are not allowed in this game which makes cheating not an issue. 
After extensive testing, the logic works fine, but there was an issue getting some of the data into the GUI. The game can be run in the terminal with plenty of additional print statements to assist the user know what is going on. 
