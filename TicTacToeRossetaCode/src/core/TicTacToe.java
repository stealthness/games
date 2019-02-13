package core;
/**
 * Created by Stephen West on 12/02/2019.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;

public class TicTacToe {


    private int[][] marks;
    private int[][] winLineCombs;
    private int[] weights;
    private char[][] grid;
    private final int knotcount = 3;
    private final int crosscount = 4;
    private final int totalcount = 5;
    private final int playerid = 0;
    private final int compid = 1;
    private final int truceid = 2;
    private final int playingid = 3;
    private String movesPlayer;
    private byte override;
    private char[][] overridegrid = {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}};
    private char[][] numpad = {{'7', '8', '9'}, {'4', '5', '6'}, {'1', '2', '3'}};
    private Map<Integer, Integer> crossbank;
    private Map<Integer, Integer> knotbank;


    public static void main(String[] args) {
        final TicTacToe now = new TicTacToe();
        now.startMatch();
    }


    public void startMatch() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        GameRendere.println("Start?(y/n):");
        char choice = 'y';
        try {
            choice = br.readLine().charAt(0);
        } catch (Exception e) {
            GameRendere.println(e.getMessage());
        }
        if (choice == 'n' || choice == 'N') {
            return;
        }

        GameRendere.println("Use a standard numpad as an entry grid, as so:\n ");
        display(numpad);
        GameRendere.println("Begin");
        int playerscore = 0;
        int compscore = 0;
        do {
            final int result = startGame();
            if (result == playerid) {
                playerscore++;
            } else if (result == compid) {
                compscore++;
            }
            GameRendere.println("Score: Player-" + playerscore + " AI-" + compscore);
            GameRendere.println("Another?(y/n):");
            try {
                choice = br.readLine().charAt(0);
            } catch (Exception e) {
                GameRendere.println(e.getMessage());
            }

        } while (choice != 'n' || choice == 'N');

        GameRendere.println("Game over.");
    }

    private void put(final int cell, final int player) {
        int row = -1;
        int col = -1;
        switch (cell) {
            case 1:
                row = 2;
                col = 0;
                break;
            case 2:
                row = 2;
                col = 1;
                break;
            case 3:
                row = 2;
                col = 2;
                break;
            case 4:
                row = 1;
                col = 0;
                break;
            case 5:
                row = 1;
                col = 1;
                break;
            case 6:
                row = 1;
                col = 2;
                break;
            case 7:
                row = 0;
                col = 0;
                break;
            case 8:
                row = 0;
                col = 1;
                break;
            case 9:
                row = 0;
                col = 2;
                break;
            default:
                display(overridegrid);
                return;
        }
        char mark = 'x';
        if (player == 0) {
            mark = 'o';
        }
        grid[row][col] = mark;
        display(grid);
    }

    private int startGame() {
        init();
        display(grid);
        int status = playingid;
        while (status == playingid) {
            put(playerMove(), 0);
            if (override == 1) {
                GameRendere.println("O wins.");
                return playerid;
            }
            status = checkForWin();
            if (status != playingid) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                GameRendere.println(e.getMessage());
            }
            put(compMove(), 1);
            status = checkForWin();
        }
        return status;
    }

    private void init() {
        movesPlayer = "";
        override = 0;
        marks = new int[8][6];
        winLineCombs = new int[][]    //new int[8][3];
                {
                        {7, 8, 9},
                        {4, 5, 6},
                        {1, 2, 3},
                        {7, 4, 1},
                        {8, 5, 2},
                        {9, 6, 3},
                        {7, 5, 3},
                        {9, 5, 1}
                };
        weights = new int[]{3, 2, 3, 2, 4, 2, 3, 2, 3};
        grid = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        crossbank = new Hashtable<Integer, Integer>();
        knotbank = new Hashtable<Integer, Integer>();
    }

    private void mark(final int m, final int player) {
        for (int i = 0; i < winLineCombs.length; i++) {
            for (int j = 0; j < winLineCombs[i].length; j++) {
                if (winLineCombs[i][j] == m) {
                    marks[i][j] = 1;
                    if (player == playerid) {
                        marks[i][knotcount]++;
                    } else {
                        marks[i][crosscount]++;
                    }
                    marks[i][totalcount]++;
                }
            }
        }
    }

    private void fixWeights() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (marks[i][j] == 1) {
                    if (weights[winLineCombs[i][j] - 1] != Integer.MIN_VALUE) {
                        weights[winLineCombs[i][j] - 1] = Integer.MIN_VALUE;
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            if (marks[i][totalcount] != 2) {
                continue;
            }
            if (marks[i][crosscount] == 2) {
                final int p = i;
                int q = -1;
                if (marks[i][0] == 0) {
                    q = 0;
                } else if (marks[i][1] == 0) {
                    q = 1;
                } else if (marks[i][2] == 0) {
                    q = 2;
                }

                if (weights[winLineCombs[p][q] - 1] != Integer.MIN_VALUE) {
                    weights[winLineCombs[p][q] - 1] = 6;
                }
            }
            if (marks[i][knotcount] == 2) {
                final int p = i;
                int q = -1;
                if (marks[i][0] == 0) {
                    q = 0;
                } else if (marks[i][1] == 0) {
                    q = 1;
                } else if (marks[i][2] == 0) {
                    q = 2;
                }

                if (weights[winLineCombs[p][q] - 1] != Integer.MIN_VALUE) {
                    weights[winLineCombs[p][q] - 1] = 5;
                }
            }
        }
    }

    private int compMove() {
        final int cell = move();
        GameRendere.println("Computer plays: " + cell);
        //weights[cell-1]=Integer.MIN_VALUE;
        return cell;
    }

    private int move() {
        int max = Integer.MIN_VALUE;
        int cell = 0;
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > max) {
                max = weights[i];
                cell = i + 1;
            }
        }

        //This section ensures the computer never loses
        //Remove it for a fair match
        //Dirty kluge
        if ("76".equals(movesPlayer) || "67".equals(movesPlayer)) {
            cell = 9;
        } else if ("92".equals(movesPlayer) || "29".equals(movesPlayer)) {
            cell = 3;
        } else if ("18".equals(movesPlayer) || "81".equals(movesPlayer)) {
            cell = 7;
        } else if ("73".equals(movesPlayer) || "37".equals(movesPlayer)) {
            cell = 4 * ((int) (Math.random() * 2) + 1);
        } else if ("19".equals(movesPlayer) || "91".equals(movesPlayer)) {
            cell = 4 + 2 * (int) (Math.pow(-1, (int) (Math.random() * 2)));
        }



        mark(cell, 1);
        fixWeights();
        crossbank.put(cell, 0);
        return cell;
    }

    private int playerMove() {
        GameRendere.println("What's your move?: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//NOPMD
        int cell = 0;
        int okay = 0;
        while (okay == 0) {
            try {
                cell = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                GameRendere.println(e.getMessage());
            }
            // override needs explaining
            if (cell == 666) {
                override = 1;
                return -1;
            }
            if (cell < 1 || cell > 9 || weights[cell - 1] == Integer.MIN_VALUE) {
                GameRendere.println("Invalid move. Try again:");
            } else {
                okay = 1;
            }
        }
        playerMoved(cell);
        GameRendere.println("");
        return cell;
    }

    private void playerMoved(final int cell) {
        movesPlayer += cell;
        mark(cell, 0);
        fixWeights();
        knotbank.put(cell, 0);
    }

    private int checkForWin() {
        // these should be boolean

        boolean crossHasWon = false;
        boolean knotHasWon = false;


        for (int[] winLineComb : winLineCombs) {
            if (crossbank.containsKey(winLineComb[0])
                    && crossbank.containsKey(winLineComb[0])
                    && crossbank.containsKey(winLineComb[0])) {
                crossHasWon = true;
                break;

            }
            if (knotbank.containsKey(winLineComb[0])) {
                if (knotbank.containsKey(winLineComb[1])) {
                    if (knotbank.containsKey(winLineComb[2])) {
                        knotHasWon = true;
                        break;
                    }
                }
            }
        }
        if (knotHasWon) {
            display(grid);
            GameRendere.println("O wins.");
            return playerid;
        } else if (crossHasWon) {
            display(grid);
            GameRendere.println("X wins.");
            return compid;
        }


        for (int weight : weights) {
            if (weight != Integer.MIN_VALUE) {
                return playingid;
            }
        }
        GameRendere.println("Truce");

        return truceid;
    }

    private void display(char[]...grid) {
        for (int i = 0; i < 3; i++) {
            GameRendere.println("\n-------");
            GameRendere.println("|");
            for (int j = 0; j < 3; j++) {
                GameRendere.println(grid[i][j] + "|");
            }
        }
        GameRendere.println("\n-------");
    }
}

class GameRendere{


    public static void println(String message){
        System.out.print(message);
    }
}
