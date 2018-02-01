/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.IOException;
import tictactoe.TicTacToeBoard;
import tictactoe.TicTacToePlayer;

/**
 *
 * @author tgrun
 */
public class ServerSide implements TicTacToePlayer
{
    private final SocketConnection conn;

    public ServerSide(SocketConnection conn)
    {
        this.conn = conn;
    }
    
    public void sendMessage(String message)
    {
        try
        {
            conn.writeInt(0);
            conn.writeUTF(message);
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public void startNewGame(int playerID)
    {
        try
        {
            conn.writeInt(1);
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
            conn.writeInt(2);
            for(int i = 0; i < 9; ++i)
            {
                conn.writeInt(board.get(i));
            }
            conn.writeInt(validPositions.length);
            for(int i = 0; i < validPositions.length; ++i)
            {
                conn.writeInt(validPositions[i]);
            }
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
            conn.writeInt(3);
            conn.writeInt(result);
            conn.close();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

}
