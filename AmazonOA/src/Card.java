
public class Card implements Comparable<Card>{
	final String[] suites=new String[]{
		"clubs", "diamonds", "hearts", "spades"
		};

	final String[] values=new String[]{
		"Ace", "2", "3", "4", "5", "6", "7", "8",
		"9", "10", "Jack", "Queen", "King"	
	};
	
	private int suit;
	private int value;
	
	public Card(int suit, int value){
		this.suit=suit;
		this.value=value;
	}
	public int getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}
	
	public String toString() {
		String res="";
		res="suite is "+ suites[suit]+", value is "+values[value];
		return res;
	}
	@Override
	public int compareTo(Card other) {
		// TODO Auto-generated method stub
		int p = -99;
		if (this.getValue() < other.getValue())
			p = -1;
		if (this.getValue() > other.getValue())
			p = 1;
		if (this.getValue() == other.getValue()) {
			if (this.getSuit() < other.getSuit())
				p = -1;
			if (this.getSuit() > other.getSuit())
				p = 1;

		}

		return p;

	}
}
