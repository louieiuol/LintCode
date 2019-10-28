import java.util.*;
public class Deck {
	private ArrayList<Card> deck;
	private int top;
	private int size;
	
	public Deck() {
		deck=new ArrayList<Card>(52);
		top=0;
		size=0;
		for(int suit= 0; suit<4 ; suit++) {
			for(int value=0; value<13;value++) {
				Card card=new Card(suit,value);
				deck.add(card);
				size++;
			}
		}
	}
	public void shuffle() {
		for(int i=0; i<1000 ; i++) {
			int one=(int) (Math.random()*52);
			int two=(int) (Math.random()*52);
			Card tmp=deck.get(one);
			deck.set(one, deck.get(two));
			deck.set(two, tmp);
		}
	}
	
	public Card getTop() {
		return deck.get(top++);
	}
	
	
}
