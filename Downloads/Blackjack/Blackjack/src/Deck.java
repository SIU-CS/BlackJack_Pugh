
public class Deck
{
	private final int deckSize = 52;
	private Card deck[];
	private static Cards C = new Cards();
	private static Card forException = new Card();
	private int next;
	
	public Deck()
	{
		reset();	 	
	}
	
	public void reset()
	{
		int i;
		next=0;
		deck = new Card[deckSize];
		
		for(i=0;i<52;i++)            
		{
			// initialization
			deck[i]=new Card();
			deck[i].Spot= i % 13;
			
			if(i<13)// spades
				deck[i].Suit=C.SPADES;
			else if(i<26)// hearts
				deck[i].Suit=C.HEARTS;
			else if(i<39)//clubs
				deck[i].Suit=C.CLUBS;
			else//diamonds
				deck[i].Suit=C.DIAMONDS;
		}		
	}	
	
	public void shuffle(int n)
	{
		next=0;
		
		if(n==0 || n==52) // if n=0 or n=52,then one side is empty
		     return;
		
		int i,j;
		int left = n;
		int right = 52-n;
		
		Card Left[] = new Card[left];
		Card Right[] = new Card[right]; 
		
		for(i=0;i<left;i++) // Left deck
		{
			Left[i]=deck[i];
		}
		
		for(i=left;i<52;i++) // Right deck
		{
			Right[i-left]=deck[i];
		}		
		
		int select_min ;
		
		if(left>right)
			select_min=right;
		else 
			select_min=left;
		
		
		for(i=0,j=0;i<select_min;i++)
		{
		// shuffling is done here with selecting min of left,right
			
			deck[j++]=Left[i];
			deck[j++]=Right[i];
		}
		
		if(select_min==left) // insert extra right in deck
		{
			for(i=0;j<52;j++,i++)			
			{
				deck[j]=Right[left+i];
			}
		}
		else // insert extra left in deck
		{
			for(i=0;j<52;j++,i++)			
			{
				deck[j]=Left[right+i];
			}			
		}
		
	}
	
	public Card deal()
	{
		if(cardsLeft()>0)     
		{
			next++;
			return deck[next-1];
		}
		else
		{
			/*cout<<"No card remains for dealing\n";
			for(i=0;i<52;i++)
			{
			CardDeck.deck[i].spot = CardDeck.saveDeck[i].spot;
			CardDeck.deck[i].suit = CardDeck.saveDeck[i].suit;
			}*/
			forException.Spot = -1; // used for creating DeckEmpty Exception
			forException.Suit = -1;
			return forException;             
			
			//Permutation.randomize(CARDS_IN_DECK);
		}
	}
	
	public int cardsLeft()
	{
	        return (deckSize-next);
	}
}
;