/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl.remote;

import java.io.IOException;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToePlayer;
import tictactoe.remote.TicTacToeConnection;

/**
 *
 * @author Tobias
 */
public class TicTacToePlayerCallSide implements TicTacToePlayer
{

    private TicTacToeConnection conn;

    public TicTacToePlayerCallSide(TicTacToeConnection conn)
    {
        this.conn = conn;
    }
   
    @Override
    public void startNewGame(int playerID)
    {
        try
        {
            conn.writeString("startNewGame");
            conn.writeInt(playerID);
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int takeTurn(TicTacToeBoard board, int[] validPositions)
    {
        try
        {
            conn.writeString("takeTurn");
            conn.writeBoard(board);
            conn.writeIntArray(validPositions);
            return conn.readInt();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void endGame(int result)
    {
        try
        {
            conn.writeString("endGame");
            conn.writeInt(result);
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
}
