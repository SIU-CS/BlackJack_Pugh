import java.awt.event.*;
import javax.swing.*;

public class HitAction implements ActionListener
{
		BlackJackFrame mainFrame;
		
		public HitAction(BlackJackFrame f)
		{
			mainFrame=f;		
		}
		
		public void actionPerformed(ActionEvent e) 
		{
        	try{
        	
        		mainFrame.output.println(new String("HIT"));
        		
        		String str=mainFrame.input.readLine();
        		
        		System.out.println(str);
        	
      
        		String []values=str.split(";");
        		
        		int []intVal=new int[values.length];
        		
        		for(int i=0;i<values.length;i++)
        		{
        			intVal[i]=Integer.parseInt(values[i]);
        		}
        		
        		int first=intVal[0]+intVal[1]*13;
        			
        		mainFrame.lblCardMy[mainFrame.clientNumCard++].setIcon(new ImageIcon("images\\"+mainFrame.images.allSource[first]));
        		
        			
        		
        		if(intVal[2]>21)
        		{
        			mainFrame.lblMyCount.setText("Count:"+values[2]+" (Busted)");
        			mainFrame.bankRoll-=mainFrame.betAmount;
        			
        			mainFrame.updateBalance();
        			mainFrame.btnHit.setEnabled(false);	
        			mainFrame.btnStand.setEnabled(false);
        			mainFrame.btnDeal.setEnabled(true);
        		}
        		else
        		{
    				mainFrame.lblMyCount.setText("Count:"+values[2]);  
	       			mainFrame.btnDeal.setEnabled(false);
        		}
        		
        		
        			
        	}catch(Exception ie)
        	{
        		ie.printStackTrace();
        	}
        	
        }
}
