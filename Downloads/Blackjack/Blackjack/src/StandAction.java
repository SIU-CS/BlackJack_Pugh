import java.awt.event.*;
import javax.swing.*;

public class StandAction implements ActionListener
{
		BlackJackFrame mainFrame;
		
		public StandAction(BlackJackFrame f)
		{
			mainFrame=f;		
		}
		
		public void actionPerformed(ActionEvent e) 
		{
        	try{
        	
        		int i;
        		
        		System.out.println("Stand pressed");
        		mainFrame.output.println(new String("STAND"));	
        		
        		mainFrame.lblCardDealer[1].setIcon(new ImageIcon("images\\"+mainFrame.images.allSource[mainFrame.holeCard]));
        		
        		String str=mainFrame.input.readLine();
        		
        		System.out.println(str);
        	
      
        		String []values=str.split(";");
        		
        		int []intVal=new int[values.length];
        		
        		for(i=0;i<values.length;i++)
        		{
        			intVal[i]=Integer.parseInt(values[i]);
        		}
        		
        		
        		int numCard=2;
        		
        		for(i=0;i<values.length-2;i+=2)
        		{
        			mainFrame.lblCardDealer[numCard++].setIcon(new ImageIcon("images\\"+mainFrame.images.allSource[numCard]));
        		}
        		
        		
        		if(intVal[values.length-1]==2) //dealer busted
        		{
        			mainFrame.lblDealerCount.setText("Count: "+intVal[values.length-2]+"(Busted");
        			str=mainFrame.lblMyCount.getText()+"(Won)";
        			mainFrame.lblMyCount.setText(str);
        			mainFrame.bankRoll+=mainFrame.betAmount;
        			mainFrame.updateBalance();
        		}
        		else if(intVal[values.length-1]==1)
        		{
        			mainFrame.lblDealerCount.setText("Count: "+intVal[values.length-2]+"(Won)");
        			mainFrame.bankRoll -=mainFrame.betAmount;
        			
        			mainFrame.updateBalance();
        			
        		}
        		else
        		{
        			mainFrame.lblDealerCount.setText("Count: "+intVal[values.length-2]+"(Tie)");
        		}
        		
        		mainFrame.btnDeal.setEnabled(true);
        		mainFrame.btnHit.setEnabled(false);
        		mainFrame.btnStand.setEnabled(false);
        		
        		
        	}catch(Exception ie)
        	{
        		ie.printStackTrace();
        	}
        	
        }
}
