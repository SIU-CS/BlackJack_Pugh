
public class BlackJack {
    
    public static void main(String[] args) {
        // Start game server
    	BlackJackServer b=new BlackJackServer();
		Thread serverThread=new Thread(b);
		serverThread.start();
    	
		//Start game client
    	BlackJackFrame frame = new BlackJackFrame();
        
        // Show frame
        frame.setVisible(true);
        frame.welcome();
    }
}
