import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;
    private Rectangle button;
    private Deck deck;
    private Rectangle replaceButton;

    public DrawPanel() {
        replaceButton = new Rectangle(162, 280, 160, 26);
        button = new Rectangle(162, 240, 160, 26);
        this.addMouseListener(this);
        deck = new Deck();
        hand = deck.buildHand();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 120;
        int y = 10;
        int cardHeight =0;
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            cardHeight = c.getImage().getHeight();
            if((i)%3==0 && i != 0)
            {
                y += c.getImage().getHeight()+10;
                x = 120;
            }
            if (c.getHighlight()) {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            x = x + c.getImage().getWidth() + 30;
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("REPLACE CARDS", 165, cardHeight*3+40+30);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        g.drawString("RESTART GAME", 165, cardHeight*3+40+30+40);
        g.drawRect((int)replaceButton.getX(), (int)replaceButton.getY(), (int)replaceButton.getWidth(), (int)replaceButton.getHeight());
        if (!deck.isPossible(hand))
        {
            g.drawString("GAME OVER! THERE ARE NO OPTIONS LEFT!",10,350);
        }
        if(deck.getDeck().size() == 0)
        {
            g.drawString("YOU WIN! There are no cards left in the deck",10,350);
        }
        g.drawString("Number of Cards Remaining: " + deck.getDeck().size(),10,400);
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (replaceButton.contains(clicked)) {
                deck = new Deck();
                hand = deck.buildHand();
            }
            else if(button.contains(clicked))
            {
                ArrayList<Card> selectedCards = new ArrayList<>();
                for (int i = 0; i < hand.size(); i++) {
                   if(hand.get(i).getHighlight())
                   {
                       selectedCards.add(hand.get(i));
                   }
                }
                if(deck.replaceable(selectedCards))
                {
                    for (int i = 0; i < hand.size(); i++) {
                        if(hand.get(i).getHighlight())
                        {
                            deck.replacedCard(hand,i);
                        }
                    }
                }
            }
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        if (e.getButton() == 3) {
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipHighlight();
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}