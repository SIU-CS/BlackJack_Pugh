import java.awt.event.*;
import javax.swing.*;

public class DealAction implements ActionListener
{
		BlackJackFrame mainFrame;
		
		public DealAction(BlackJackFrame f)
		{
			mainFrame=f;		
		}
		
		
		public void actionPerformed(ActionEvent e) 
		{
        	try{
        	
        		for(int k=0;k<6;k++)
        		{
        			mainFrame.lblCardMy[k].setIcon(new ImageIcon("images\\b.gif"));
        			mainFrame.lblCardDealer[k].setIcon(new ImageIcon("images\\b.gif"));
        		}
        			
        		mainFrame.output.println("DEAL");
        		
        		String str=mainFrame.input.readLine();
        		
        		System.out.println(str);
        	
      
        		String []values=str.split(";");
        		
        		int []intVal=new int[values.length];
        		
        		for(int i=0;i<values.length;i++)
        		{
        			intVal[i]=Integer.parseInt(values[i]);
        		}
        		
        		mainFrame.lblMyCount.setText("Count:"+values[9]);
        		mainFrame.lblDealerCount.setText("Count:");
        		
        		int first=intVal[2]+intVal[3]*13;
        		int second=intVal[6]+intVal[7]*13;
        		
        		int third=intVal[0]+intVal[1]*13;
        		
        		mainFrame.holeCard=intVal[4]+intVal[5]*13;
        		
        		mainFrame.lblCardMy[0].setIcon(new ImageIcon("images\\"+mainFrame.images.allSource[first]));
        		mainFrame.lblCardMy[1].setIcon(new ImageIcon("images\\"+mainFrame.images.allSource[second]));
        		mainFrame.lblCardDealer[0].setIcon(new ImageIcon("images\\"+mainFrame.images.allSource[third]));
        		//mainFrame.lblCardDealer[1].setIcon(new ImageIcon("images\\"+mainFrame.images.allSource[fourth]));
        		mainFrame.btnDeal.setEnabled(false);
        		mainFrame.btnHit.setEnabled(true);
        		mainFrame.btnStand.setEnabled(true);
        		mainFrame.clientNumCard=2;	
        	}catch(Exception ie)
        	{
        		ie.printStackTrace();
        	}
        	
        }
}
