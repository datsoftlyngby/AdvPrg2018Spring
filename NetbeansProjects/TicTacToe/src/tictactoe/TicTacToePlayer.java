/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Tobias
 */
public interface TicTacToePlayer
{
    public void startNewGame(int playerID);
    public int takeTurn(TicTacToeBoard board, int[] validPositions);
    public void endGame(int result);
}
