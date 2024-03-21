import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    public static ArrayList<Card> buildDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] values = {"02", "03", "04", "05", "06", "07", "08", "09", "10", "A", "J", "K", "Q"};
        for (String s : suits) {
            for (String v : values) {
                Card c = new Card(s, v);
                deck.add(c);
            }
        }
        return deck;
    }

    public static ArrayList<Card> buildHand() {
        ArrayList<Card> deck = Deck.buildDeck();
        ArrayList<Card> hand = new ArrayList<Card>();
        for (int i = 0; i < 9; i++) {
            int r = (int)(Math.random()*deck.size());
            Card c = deck.remove(r);
            hand.add(c);
        }
        return hand;
    }
    public static void replacedCard(ArrayList<Card> hand, int index)
    {
        ArrayList<Card> deck = Deck.buildDeck();
        boolean status = false;
        while(!status)
        {
            int r = (int)(Math.random()* deck.size());
            if(!checkContains(deck.get(r),hand))
            {
                status = true;
                hand.remove(index);
                hand.add(index, deck.get(r));
            }
        }
    }

    public static boolean checkContains(Card generated, ArrayList<Card> hand)
    {
        for(Card checking : hand)
        {
            if(generated.equals(checking)) return true;
        }
        return false;
    }
}
