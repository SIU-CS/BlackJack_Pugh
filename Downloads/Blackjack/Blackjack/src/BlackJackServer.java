import java.util.*;
import java.io.*;
import java.net.*;

public class BlackJackServer implements Runnable
{
	ServerSocket server;
	Random random;
	
	public BlackJackServer()
	{
		
		random=new Random();
		try
            {
    		      server=new ServerSocket(2002);
    		      System.out.println("BlackJack Server Started");
    		      System.out.println("Server Is Waiting For Connections...");
         	}

         	catch(IOException e)
            {
	             System.out.println("COULD NOT CREATE SERVERSOCKET");
	             System.out.println(e);
          		 System.exit(1);
         	}
		
	}
	
	public void run()
	{
		int j=0;
		while(true)
		{	
			
			try{
				Socket client=server.accept();
				j++;
				
				System.out.println(client.getInetAddress().getHostName()+" is connnected.."+j);
				
				Thread th=new Thread(new clientGame(client,j));
				th.start();
				
				
			}catch(IOException ex)
			{
				System.out.print(ex.getMessage());		
			}
		}	
	}
	
	
	class clientGame implements Runnable
	{
		Socket client;
		PrintWriter output;		
		BufferedReader input;
		int number;
		
		Deck deck;
		
		Hand dealerHand;
		Hand playerHand;
		
		
		
		
		public clientGame(Socket c,int i)
		{
			client=c;
			number=i;
			try{
				
				input=new BufferedReader(new InputStreamReader(
                                        client.getInputStream()));
                                        
				output=new PrintWriter(client.getOutputStream(),true);
				System.out.print("Here");
				
			}catch(IOException ie)
			{
				
			}	
			
			deck=new Deck();
			reshuffle();
			dealerHand=new Hand();
			playerHand=new Hand();
		}
		
		void reshuffle()
		{
			int i;
			for( i=0;i<25;i++)
				deck.shuffle(random.nextInt(49)+1);	
		
				
		}
		
		
		public void run()
		{
		
			try{
				
				//	reshuffle();
					
					while(true)
					{
						
						String message=input.readLine();	
											
					
						if(message.compareTo("DEAL")==0)
							startNewGame();
						else if(message.compareTo("HIT")==0)
							clientHit();		
						else if(message.compareTo("STAND")==0)
							clientStand();
						else 
							System.out.println("Unknown Request");
					}			
				
				
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}	
		}
		
		public void startNewGame()
		{
			System.out.println("Client "+number+": DEAL");
			dealerHand.discardAll();
			playerHand.discardAll();
			
			if(deck.cardsLeft()<4)
					reshuffle();
			
			String str="";
			
			for(int i=0;i<4;i++)
			{
				Card c=deck.deal();
				
				if(i%2==0)
					dealerHand.addCard(c);
				else
					playerHand.addCard(c);
				
				str+=c.Spot+";"+c.Suit+";";								
				
			}
			
			str+=dealerHand.curValue.count+";"+playerHand.curValue.count;
			
			output.println(str);
			
				
		}
			

		public void clientHit()
		{
			System.out.println("Client "+number+": HIT");
			if(deck.cardsLeft()<2)
				reshuffle();
			Card c=deck.deal();
			
			playerHand.addCard(c);
			
			String str=c.Spot+";"+c.Suit+";"+playerHand.curValue.count;
			
							
			output.println(str);
		}

		public void clientStand() //here is the dealer playing strategy
		{
			System.out.println("Client "+number+": STAND");	
			
						
			String str="";
			
			while(true)
			{
				if(!dealerWon()) //go until busted or won
				{
					
					if((dealerHand.curValue.count>16)&&dealerTie()) //same and count is greater than 17 then accept tie
					{
						str+=dealerHand.curValue.count+";3"; //game tie
						output.println(str);
						return;
					}
					
					if(deck.cardsLeft()<2)
						reshuffle();

					Card c=deck.deal();	 			
					dealerHand.addCard(c); //take a new card
					
					str+=c.Spot+";"+c.Suit+";";
					
					if(dealerHand.curValue.count>21) //dealer busted
					{
						str+=dealerHand.curValue.count+";2";
						output.println(str);
						return;
					}
							
				}
				else
				{
					str+=dealerHand.curValue.count+";1"; //dealer won the game
					output.println(str);
					return;	
				}
					
				
			}
			
			
			
			
		}
		
		public boolean dealerWon()
		{
			if(dealerHand.curValue.count>playerHand.curValue.count) //Dealer handvalue greater than player hand value so won
				return true;
			else return false;
	
		}
		

		public boolean dealerTie()
		{
			if(dealerHand.curValue.count==playerHand.curValue.count) 
				return true;
			else return false;
	
		}
		
		
		public boolean draw() // decides whether to draw a card or not
		{
	     	
	     	
	     	if(dealerHand.curValue.count<17)    //less than 17 always draw
	              return true;
	     	
	     	     	
	     	if(!dealerHand.curValue.soft&&(dealerHand.aceCount<dealerHand.softCount)) //Has ace and hard count     	
			{
			   	  return true;                      
	     	}
	     
		    else		                  
		            return false;
		         
	
	
		}
		



	}
	
}