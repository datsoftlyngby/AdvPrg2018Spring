/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl;

import tictactoe.TicTacToeGameCtrl;
import tictactoe.TicTacToePlayer;

/**
 *
 * @author Tobias
 */
public class TicTacToeGameCtrlImpl implements TicTacToeGameCtrl
{

    @Override
    public int playGame(TicTacToePlayer p1, TicTacToePlayer p2)
    {
        p1.startNewGame(1);
        p2.startNewGame(2);
        TicTacToeBoardImpl board = new TicTacToeBoardImpl();
        int check;
        while(true)
        {
            takeTurn(board, p1, 1);
            check = checkForGameOver(board);
            if(check >= 0)
            {
                break;
            }
            takeTurn(board, p2, 2);
            check = checkForGameOver(board);
            if(check >= 0)
            {
                break;
            }
        }
        p1.endGame(check);
        p2.endGame(check);
        return check;
    }

    private int[] getValidPositions(TicTacToeBoardImpl board)
    {
        int count = 0;
        for(int i = 0; i < 9; i++)
        {
            if(board.get(i) == 0)
            {
                ++count;
            }
        }
        int[] res = new int[count];
        int index = 0;
        for(int i = 0; i < 9; ++i)
        {
            if(board.get(i) == 0)
            {
                res[index++] = i;
            }
        }
        return res;
    }
    
    private void takeTurn(TicTacToeBoardImpl board, TicTacToePlayer p, int playerID)
    {
        int[] validPos = getValidPositions(board);
        int pos = p.takeTurn(board, validPos);
        if(board.get(pos) != 0)
        {
            throw new RuntimeException("Invalid position!");
        }
        board.set(pos, playerID);
    }

    private int checkForGameOver(TicTacToeBoardImpl board)
    {
        //First row
        int p = board.get(0);
        if(p != 0)
        {
            if(board.get(1) == p && board.get(2) == p)
            {
                return p;
            }
        }
        //Second row
        p = board.get(3);
        if(p != 0)
        {
            if(board.get(4) == p && board.get(5) == p)
            {
                return p;
            }
        }
        
        //Third row
        p = board.get(6);
        if(p != 0)
        {
            if(board.get(7) == p && board.get(8) == p)
            {
                return p;
            }
        }
        
        //First Column
        p = board.get(0);
        if(p != 0)
        {
            if(board.get(3) == p && board.get(6) == p)
            {
                return p;
            }
        }
        
        //Second Column
        p = board.get(1);
        if(p != 0)
        {
            if(board.get(4) == p && board.get(7) == p)
            {
                return p;
            }
        }
        
        //Third Column
        p = board.get(2);
        if(p != 0)
        {
            if(board.get(5) == p && board.get(8) == p)
            {
                return p;
            }
        }
        
        //First Diagonal
        p = board.get(0);
        if(p != 0)
        {
            if(board.get(4) == p && board.get(8) == p)
            {
                return p;
            }
        }
          
        //Second Diagonal
        p = board.get(2);
        if(p != 0)
        {
            if(board.get(4) == p && board.get(6) == p)
            {
                return p;
            }
        }
        
        //Test for draw
        for(int i = 0; i < 9; ++i)
        {
            if(board.get(i) == 0) return -1;
        }
        
        return 0;
    }
}
