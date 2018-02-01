/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl;

import tictactoe.TicTacToeBoard;

/**
 *
 * @author Tobias
 */
public class TicTacToeBoardImpl implements TicTacToeBoard
{
    private int[] array;

    public TicTacToeBoardImpl()
    {
        array = new int[9];
    }
    
    @Override
    public int get(int index)
    {
        return array[index];
    }
    
    public void set(int index, int value)
    {
        array[index] = value;
    }  
}
