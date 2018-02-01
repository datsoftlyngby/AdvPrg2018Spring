/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import tictactoe.impl.RandomTicTacToePlayer;
import tictactoe.impl.TicTacToeGameCtrlImpl;

/**
 *
 * @author Tobias
 */
public class TicTacToe
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        TicTacToeGameCtrl game = new TicTacToeGameCtrlImpl();
        TicTacToePlayer p1 = new RandomTicTacToePlayer();
        TicTacToePlayer p2 = new RandomTicTacToePlayer();
        game.playGame(p1, p2); 
    }
    
}
