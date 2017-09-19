
public class MediumPlayer extends Player{
	
	public void expose (Card c)
	{
	     if(c.Spot>=10)
    	     exposeCount--;
     	else
         	exposeCount++;
	}

	public void shuffled()
	{
    	 exposeCount=0; // if shuffled then exposeCount is re-initialized
	}
}
