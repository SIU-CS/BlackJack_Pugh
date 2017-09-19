
public class Card
{
/*	public static String spotNames[] = {"Two", "Three", "Four", "Five", "Six", 
			   "Seven", "Eight", "Nine", "Ten", "Jack", 
			   "Queen", "King", "Ace"};
			   
	public static String suitNames[] = {"Spades", "Hearts", "Clubs", "Diamonds"};
*/
	public int Suit; // 0-3 spades,hearts,clubs,diamonds
	public int Spot; // 0-12 two-ten,jack,queen,king,ace
	
	public Card(int suit,int spot)
	{
		Suit=suit;
		Spot=spot;	
	}
	public Card(){}
}
