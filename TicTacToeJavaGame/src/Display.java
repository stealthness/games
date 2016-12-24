import java.io.PrintStream;

public class Display{
	
	private PrintStream out;

	public Display(PrintStream out) {
		this.out =  out;
	}

	public void out(String message){
		out.println(message);
	}

	public void score(Player winningPlayer, Player player1, Player player2) {		
		out.println(String.format(R.score, player1.getName(),player1.getWins(),player2.getName(),player2.getWins()));		
	}

}
