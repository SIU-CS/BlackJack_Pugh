
public class Hand
{
	public HandValue curValue;
	public int aceCount;
	public int softCount;
			
	public Hand()
	{
		curValue = new HandValue();
		aceCount=0;
		softCount=0;
	}
	
	public void discardAll()
	{
		curValue.count=0;
		curValue.soft=false;
		aceCount=0;
		softCount=0;
	}
	
	public boolean getHard()
	{
		return !curValue.soft;	
	}
	public void addCard(Card c)
	{
     	if(c.Spot < 9) // if card is in between TWO and TEN
     	{
     		if(curValue.count+c.Spot+2 > 21)
     		{
     			if(aceCount>0 && aceCount > softCount)
     			{
     				curValue.count += c.Spot+2 - 10;
     				curValue.soft=true;
     				softCount++;
     			}
     			else curValue.count += c.Spot+2;
     		}
        	else curValue.count += c.Spot+2;
        	
        }
	    else if(c.Spot>=9 && c.Spot <=11) // if card is in between JACK and KING
	    {
     		if(curValue.count+10 > 21)
     		{
     			if(aceCount>0 && aceCount > softCount)
     			{
     				//curValue.count = curValue.count+ - 10;
     				curValue.soft=true;
     				softCount++;
     			}
     			else curValue.count += 10;
     		}
        	else curValue.count += 10;

        }
        else// if an ACE
        {
        	aceCount++;
       		
       		if(curValue.soft)
       		{
       			curValue.count+=1;
       		}
       		
       		else if(curValue.count+11 > 21)
    		{
    			curValue.count +=1;
    			curValue.soft = true;
    			softCount++;
    		}
    		else curValue.count+=11;        		
        }
   	}
	
}

class HandValue
{
	public int count;
	public boolean soft;
	
	HandValue(){
		count=0;
		soft=false;
	}
}

