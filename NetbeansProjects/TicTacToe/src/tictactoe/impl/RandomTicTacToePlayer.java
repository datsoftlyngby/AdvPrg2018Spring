/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl;

import java.util.Random;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToePlayer;

/**
 *
 * @author Tobias
 */
public class RandomTicTacToePlayer implements TicTacToePlayer
{
    private final Random rnd;
    private int id;

    public RandomTicTacToePlayer()
    {
        rnd = new Random();
    }
    
    
    
    @Override
    public void startNewGame(int playerID)
    {
        id = playerID;
        System.out.println("I am player: " + id); 
    }

    @Override
    public int takeTurn(TicTacToeBoard board, int[] validPositions)
    {
        System.out.println("Player " + id + " Taking turn:");
        //Show board
        System.out.print(board.get(0));
        System.out.print(board.get(1));
        System.out.println(board.get(2));
        System.out.print(board.get(3));
        System.out.print(board.get(4));
        System.out.println(board.get(5));
        System.out.print(board.get(6));
        System.out.print(board.get(7));
        System.out.println(board.get(8));
        int pos = validPositions[rnd.nextInt(validPositions.length)];
        System.out.println("Play at: " + pos);
        System.out.println("Turn ended.\n\n");
        return pos;
    }

    @Override
    public void endGame(int result)
    {
        System.out.println(id + " Result: " + result);
    }
    
}
