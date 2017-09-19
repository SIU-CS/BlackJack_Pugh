import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class MakeConnection implements ActionListener
{
		BlackJackFrame mainFrame;
		JDialog dialog;
		
		public MakeConnection(BlackJackFrame f,JDialog dl)
		{
			mainFrame=f;
			dialog=dl;		
		}
		
		
		public void actionPerformed(ActionEvent e) 
		{
        	try{
				     	String ip="127.0.0.1";
			        	mainFrame.bankRoll=Integer.parseInt(mainFrame.bankField.getText());
			        	mainFrame.betAmount=Integer.parseInt(mainFrame.betField.getText());
			        	mainFrame.client=new Socket(InetAddress.getByName(ip),2002);
			        	
			        	mainFrame.updateBalance();
			        	
			        	mainFrame.startGame();
			        	dialog.dispose();
			        	
			        	
        		
        	}
        	catch(Exception ex)
        	{
        		JOptionPane.showMessageDialog(this.mainFrame,"Connection error! Try again.","Error!",JOptionPane.ERROR_MESSAGE);
        	}
        	
        	
        }
}
