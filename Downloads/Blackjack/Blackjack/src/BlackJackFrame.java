import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;

public class BlackJackFrame extends JFrame {
    
	
	public GridBagLayout layout;
	public GridBagConstraints con;
	public Container container;
	public JLabel []lblCardMy=new JLabel[6];
	public JLabel []lblCardDealer=new JLabel[6];
	public JLabel lblBalance,lblMyCount,lblDealerCount;
	public JButton btnDeal,btnHit,btnStand;
	public JTextField ipaddress,bankField,betField;
	public boolean connected;
	public	PrintWriter output;		
	public	BufferedReader input;
	public ImageSource images;
	
	public Socket client;
	
	public int bankRoll;
	public int betAmount;
	public int clientNumCard;
	public int holeCard;



     public BlackJackFrame() {
                
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu();
        MenuItem menuFileExit = new MenuItem();
        
        menuFile.setLabel("File");
        menuFileExit.setLabel("Exit");
        
        // Add action listener.for the menu button
        menuFileExit.addActionListener
        (
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    BlackJackFrame.this.windowClosed();
                }
            }
        ); 
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        
        setTitle("BlackJack");
        setMenuBar(menuBar);
        
        
        
        layout=new GridBagLayout();

		container=getContentPane();
	  	container.setLayout(layout);
		
    	con=new GridBagConstraints();
    	
    	con.weightx=1;
		con.weighty=1;
		
		new JLabel("  ");
		lblBalance=new JLabel("Your balance is: $100");
		
		con.fill=GridBagConstraints.SOUTH;	
		addcomponent(lblBalance,1,1,2,1);
		

		
        
        JLabel lblMyCard=new JLabel("=============My Cards ===================");
        JLabel lblDealerCard=new JLabel("==============Dealers Card=============");
        con.fill=GridBagConstraints.BOTH;	
        
        
        lblMyCount=new JLabel("Count: ");
        lblMyCount.setBackground(Color.WHITE);
        lblMyCount.setOpaque(true);
        lblDealerCount=new JLabel("Count: ");
        lblDealerCount.setBackground(Color.white);
        
        lblDealerCount.setOpaque(true);
        
        
        addcomponent(lblMyCard,2,1,4,1);
        addcomponent(lblMyCount,2,5,2,1);
        addcomponent(lblDealerCard,4,1,4,1);
        addcomponent(lblDealerCount,4,5,2,1);
        
        
        
        con.fill=GridBagConstraints.BOTH;	
        
        for(int k=0;k<6;k++)
        {
        	lblCardMy[k]=new JLabel(new ImageIcon("images\\b.gif"));
        	lblCardDealer[k]=new JLabel(new ImageIcon("images\\b.gif"));
        	addcomponent(lblCardMy[k],3,k+1,1,1);
        	addcomponent(lblCardDealer[k],5,k+1,1,1);
        }
        
        
        btnDeal=new JButton("Deal");
        btnDeal.addActionListener(new DealAction(this));
        btnHit=new JButton("Hit");
        btnHit.addActionListener(new HitAction(this));
        btnHit.setEnabled(false);
        btnStand=new JButton("Stand");
        btnStand.addActionListener(new StandAction(this));
        btnStand.setEnabled(false);
        
        con.fill=GridBagConstraints.CENTER;	
        
        addcomponent(btnDeal,6,2,1,1);
        addcomponent(btnHit,6,4,1,1);
        addcomponent(btnStand,6,6,1,1);
        
        setSize(new Dimension(500, 400));
        
        // Add window listener.
        this.addWindowListener
        (
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    BlackJackFrame.this.windowClosed();
                }
            }
        );
        
        
    }
    
    
    private void addcomponent(Component com,int r,int c,int w,int h)
	{
	
		con.gridx=c;
		con.gridy=r;
		con.gridwidth=w;
		con.gridheight=h;
		layout.setConstraints(com,con);
		container.add(com);
	}
    
    
    /**
     * Shutdown procedure when run as an application.
     */
    protected void windowClosed() {
    	
    	// TODO: Check if it is save to close the application
    	
        // Exit application.
        System.exit(0);
    }
    
    public void updateBalance()
    {
    	this.lblBalance.setText("Your balance is: $"+bankRoll);	
    }
    
    public void startGame()
    {
    	try{
	    	System.out.print("Game started...");
    		images=new ImageSource();
    		input=new BufferedReader(new InputStreamReader(
                                        client.getInputStream()));
                                        
			output=new PrintWriter(client.getOutputStream(),true);
    		System.out.print("Game started...");
    	}catch(IOException ie)
    	{
    		
    	}
    }
    
	public void welcome()
	{
		JDialog information=new JDialog(BlackJackFrame.this,"Game info",true);
		information.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(4,2));
		
		
		
		
		
		JLabel lblBankRoll=new JLabel ("Enter your Bankroll:");
		JLabel lblBet=new JLabel ("Enter your bet amount:");
		
		
		 
		 bankField=new JTextField("100");
		 betField=new JTextField("5");
		JButton ok=new JButton("OK");
		
		ok.addActionListener(new MakeConnection(this,information));
		
		
		
		panel.add(lblBankRoll);
		panel.add(bankField);
		panel.add(lblBet);
		panel.add(betField);
		panel.add(new JLabel(""));
		panel.add(ok);
		Container c=information.getContentPane();
		
		c.add(panel);
		


			
		information.setSize(300,150);
		information.setLocation(50,50);
		information.setResizable(false);
		information.show();

	}

    
}
