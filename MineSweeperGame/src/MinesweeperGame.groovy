/**
 * code by stealthness 2012
 */

import java.util.Scanner;

/**
 * class MinesweeperGame
 */
class MinesweeperGame {
    static Scanner sc
    def choice
    boolean testWin
    Minesweeper m

    def MinesweeperGame() {
        m = new Minesweeper(4, 6)
        m.intialise()
        m.setMinesNearValues()
        m.hidden = true
        testWin = false

    }


    static main(args) {
        sc = new Scanner(System.in)
        MinesweeperGame game = new MinesweeperGame()
        //game.m.hidden = false
        println game.m
        while (!game.m.gameOver) {
            // select choice
            game.makeChoice()
            // print out new grid
            println game.m
            // check status
            if (game.testWin) {
                game.m.checkWin()
            }

        }
        println "Game Over!"
    }

    def makeChoice() {
        println "Make a selection Guess(?), Select(x), Clear(c) or Test(T) |  Quit(Q)"

        choice = sc.next()
        println "you choose $choice"
        switch (choice.toLowerCase()) {
            case '?':
            case 'x':
            case 'c':
                this.makeMove(choice)
                break
            case 't':
                this.testWin = true
                break
            case 'q':
                m.gameOver = true
                break
        }
    }

    def getPos() {
        def pos = []
        // pos[0]
        print "Select row : "
        pos.add(sc.nextInt())
        //  pos[1]
        print "Select column : "
        pos.add(sc.nextInt())
        println "you have selected the tile at (${pos[0]},${pos[1]})"
        return pos
    }

    def makeMove(choice) {
        def pos = getPos()
        if (!(this.m.addMove(choice, pos))) {
            if (this.m.gameOver) {
                println "KaBoom!!!"
            } else {
                println "Invalid tile"
            }
        } else {
            m.numberOfTiles--
        }
    }


}