package base;

import base.tile.GoalTile;
import base.tile.SolidTile;
import base.tile.Tile;
import res.R;

import java.awt.*;

public class Game extends Application{

	private static final double SQUARE_SIZE = 30;	
    Button newGameButton = new Button(R.newGame);
    Button endGameButton = new Button(R.newGame);
    Button showAllButton = new Button(R.showAll);
    Button upButton = new Button(R.upButton);
    Button downButton = new Button(R.downButton);
    Button leftButton = new Button(R.leftButton);
    Button rightButton = new Button(R.rightButton);
    boolean revealAll = false;

	public static void main(String[] args) {
		launch(args);

	}

	private GameMap gameMap;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		gameMap = new GameMap();
		initGameMap();
		
        primaryStage.setTitle(R.title);
       
        
        GridPane grid = new GridPane();
        Canvas canvas = new Canvas(SQUARE_SIZE*16, SQUARE_SIZE*16);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawGameMap(gc);
        grid.add(addUI(gc),0, 0);
        grid.add(canvas, 0, 1);
  
        Scene scene = new Scene(grid);
        
        String css = Game.class.getResource("/style.css").toExternalForm();
        scene.getStylesheets().clear();
        scene.getStylesheets().add(css);
        
        primaryStage.setScene(scene);
        primaryStage.show();
                    
	}
	
	
	
	private void initGameMap() {
		gameMap.createBug(2);
		gameMap.setBlockAT(0, 0);
		gameMap.setBlockAT(1, 0);
		gameMap.setBlockAT(2, 0);
		gameMap.setBlockAT(3, 5);
		gameMap.setBlockAT(4, 6);
		gameMap.setBlockAT(5, 7);
		gameMap.setGoalAT(1, 1);
		//gameMap.setBugAt(3, 3);
		
	}


	private void drawGameMap(GraphicsContext gc) {
		System.out.println(gameMap.toString());
		for (int i =0;i<gameMap.getLength();i++){
			for (int j=0;j<gameMap.getWidth();j++){
				
				char mark = gameMap.getMarkAT(i,j);
				Color color;
				if (mark==SolidTile.MARK ){	
					color = Color.BLUEVIOLET;
				}else if (mark== BugCharacter.MARK){
					color = Color.RED;
				}else if (mark==GoalTile.MARK){
					color = Color.VIOLET;
				}else if (mark==Tile.MARK){
					color = Color.BLACK;
				}else{
					color = Color.BEIGE;
				}
				drawSquareAt(gc, color, i, j);
				
			}
		}		
	}

	private GridPane addUI(GraphicsContext gc){
	    GridPane grid = new GridPane();
	    grid.setVgap(10);
        grid.add(newGameButton,0,0);
        grid.add(endGameButton, 0, 0);
        endGameButton.setVisible(false);
        grid.add(showAllButton,1,0);
        endGameButton.setVisible(false);       
        newGameButton.getOnAction();
        
        grid.add(upButton, 1, 1);
        grid.add(downButton, 1, 3); 
        grid.add(leftButton, 0, 2);
        grid.add(rightButton, 2, 2);
		
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
        		System.out.println("new");
                newGameButton.setVisible(true);
                endGameButton.setVisible(false);
                gameMap.setBugAt(3, 3);
                System.out.println(gameMap.toString());
            }
        });
		return grid;	
	}
	
	private void drawSquareAt(GraphicsContext gc, Color color,int i, int j){
		gc.setFill(color);
		gc.fillRoundRect((SQUARE_SIZE+10)*(j+1), (SQUARE_SIZE+10)*(i+1), SQUARE_SIZE, SQUARE_SIZE,SQUARE_SIZE/5, SQUARE_SIZE/5);
		
	}

}
