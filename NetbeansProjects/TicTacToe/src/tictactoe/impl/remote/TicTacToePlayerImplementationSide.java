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
public class TicTacToePlayerImplementationSide implements Runnable
{

    private TicTacToeConnection conn;
    private TicTacToePlayer player;

    public TicTacToePlayerImplementationSide(TicTacToeConnection conn, TicTacToePlayer player)
    {
        this.conn = conn;
        this.player = player;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                String methodName = conn.readString();
                switch(methodName)
                {
                    case "startNewGame":
                        int playerID = conn.readInt();
                        player.startNewGame(playerID);
                        break;
                    case "takeTurn":
                        TicTacToeBoard board = conn.readBoard();
                        int[] validPositions = conn.readIntArray();
                        int res = player.takeTurn(board, validPositions);
                        conn.writeInt(res);
                        break;
                    case "endGame":
                        int result = conn.readInt();
                        conn.close();
                        player.endGame(result);
                        return;
                    default:
                        throw new RuntimeException("Unknown method: " + methodName);
                }
                    
            }
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

}
