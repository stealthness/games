package core;
/**
 *
 * @author www.javadb.com
 */
public class Main {

    public void play() {

        TicTacToe game = new TicTacToe();

        this.setUp(game);
        this.getMarker(game, 0);
        this.getMarker(game, 1);
      
        boolean continuePlaying = true;

        while (continuePlaying) {

            game.init();
            
           
            System.out.println(game.getRules());       
            System.out.println(game.drawBoard());
            

            String player = null;
            while (!game.winner() && game.getPlays() < 9) {
                //player = game.getCurrentPlayer() == 1 ? game.getPlayer1() : game.getPlayer2();
                boolean validPick = false;
                while (!validPick) {
                    System.out.print(R.nextPlayerTurn(player));
                    String square = game.getPrompt();
                    if (square.length() == 1 && Character.isDigit(square.toCharArray()[0])) {
                        int pick = 0;
                        try {
                            pick = Integer.parseInt(square);
                        } catch (NumberFormatException e) {
                            //Do nothing here, it'll evaluate as an invalid pick on the next row.
                        }
                        validPick = game.placeMarker(pick);
                    }
                    if (!validPick) {
                        System.out.println(R.selectAnotherSquare);
                    }
                }
                game.switchPlayers();
                
                System.out.println(game.drawBoard());
                
            }
            continuePlaying = getResult(game, game.getCurrentPlayer() );

        }
    }
    
    private boolean getResult(TicTacToe game, int player) {
		
		if (game.winner()) {
                System.out.println(R.gameOver + " - " + player + " "+R.gameWin);
            } else {
                System.out.println(R.gameOver+" - " + R.gameDraw);
            }
            System.out.println();
            System.out.print(R.playAgain);
            String choice = game.getPrompt();
            return (choice.equalsIgnoreCase("y")) ;
	}

	private void setUp(TicTacToe game){
        System.out.println(R.welcome);
        System.out.print(R.enterPlayer1);
       // game.setPlayer1(game.getPrompt());
        System.out.print(R.enterPlayer2);
        //game.setPlayer2(game.getPrompt());
    }
    
    
    private void getMarker(TicTacToe game,int player){
        boolean markerOk = false;
        while (!markerOk) {
         //   System.out.print(R.selesctAnotherLetter(game.getPlayer(player)));
            String marker = game.getPrompt();
            if (marker.length() == 1 &&
                    Character.isLetter(marker.toCharArray()[0])) {
                markerOk = true;
                game.setMarker(player,marker.toCharArray()[0]);
            } else {
                System.out.println(R.invalidMarker);
            }
        }
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.play();
    }
}


