public class Player
{

	boolean isHard;
	int exposeCount;
	

	public Player()
	{
 		isHard=true;
 		exposeCount=0;             
	}

	public int bet(int bankroll, int minimum)
	{
 		if(bankroll < minimum)    
        	return -1;
 		else
     		return minimum;
	}
	
	

	public boolean draw(Card dealer, Hand player) // decides whether to draw a card or not
	{
     	// logic is implemented from the specs
     	
     	
     	isHard=player.getHard();
     	
     	if(isHard)
     	{
          if(player.curValue.count<=17)    
              return true;
          //else return false;
          
          if(player.curValue.count==18 && (dealer.Spot==0||dealer.Spot==5||dealer.Spot==6))
              return false;
          
          
          return false;                      
     	}
     
	    else
	    {
	         if(player.curValue.count<=16)                     
	            return true;
	         
	         if(player.curValue.count==17)
	         {   
	             if(dealer.Spot==2||dealer.Spot==3||dealer.Spot==4)
	                      return false;
	             else return true;
	         }        
	         
	         return false; 
	                                    
	     }
	         


	}
	public void expose(Card c)
	{
			
	}
	
	public void shuffled()
	{
		
	}

}		 

