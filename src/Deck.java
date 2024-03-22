import java.util.ArrayList;

public class Deck {
    final private ArrayList<Card> deck;

    public Deck()
    {
        deck = buildDeck();
    }
    public ArrayList<Card> buildDeck() {
        ArrayList<Card> deck = new ArrayList<>();
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

    public ArrayList<Card> buildHand() {
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            int r = (int)(Math.random()*deck.size());
            Card c = deck.remove(r);
            hand.add(c);
        }
        return hand;
    }
    public void replacedCard(ArrayList<Card> hand, int index)
    {
        boolean status = false;
        while(!status)
        {
            int r = (int)(Math.random()* deck.size());
            if(!checkContains(deck.get(r),hand))
            {
                status = true;
                hand.remove(index);
                hand.add(index, deck.get(r));
                deck.remove(r);
            }
        }
    }

    public boolean replaceable(ArrayList<Card> selected)
    {
        int size = selected.size();
        if(size>3) return false;
        else if (size == 3)
        {
            String selectedString = "";
            for(Card selectedCards : selected)
            {
                selectedString+=selectedCards.getValue();
            }
            if(selectedString.contains("Q") && selectedString.contains("J") && selectedString.contains("K")) return true;
        }
        else{
            int count = 0;
            for(Card selectedCards : selected)
            {
                int value = parse(selectedCards.getValue());
                if(value<0) return false;
                count +=value;
            }
            if(count == 11) return true;
        }
        return false;
    }

    public static boolean checkContains(Card generated, ArrayList<Card> hand)
    {
        for(Card checking : hand)
        {
            if(generated.equals(checking)) return true;
        }
        return false;
    }

    private int parse(String value)
    {
        if(value.equals("A")) value = "1";
        try{
            return Integer.parseInt(value);
        }
        catch(NumberFormatException e)
        {
            return -1;
        }
    }

    public boolean isPossible(ArrayList<Card> hand)
    {
        String irregular = "";
        for(int i = 0; i < hand.size(); i++)
        {
            int value = parse(hand.get(i).getValue());
            if(value<0)
            {
                irregular+=value;
                continue;
            }
            for(int a = 0; a < hand.size(); a++)
            {
                if(a == i) continue;
                int newValue = parse(hand.get(a).getValue());
                if(newValue<0) continue;
                int total = value+newValue;
                if(total==11) return true;
            }
        }
        if(irregular.contains("Q") && irregular.contains("K") && irregular.contains("J")) return true;
        return false;
    }

    public ArrayList<Card> getDeck()
    {
        return  deck;
    }
}
