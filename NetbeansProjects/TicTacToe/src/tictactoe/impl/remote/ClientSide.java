/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.impl.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.IOException;
import tictactoe.TicTacToePlayer;
import tictactoe.impl.TicTacToeBoardImpl;

/**
 *
 * @author tgrun
 */
public class ClientSide implements Runnable
{

    private final SocketConnection conn;
    private final TicTacToePlayer player;

    public ClientSide(SocketConnection conn, TicTacToePlayer player)
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
                int command = conn.readInt();
                switch (command)
                {
                    case 0:
                        String msg = conn.readUTF();
                        System.out.println(msg);
                        break;
                    case 1:
                        int playerID = conn.readInt();
                        player.startNewGame(playerID);
                        break;
                    case 2:
                        TicTacToeBoardImpl board = new TicTacToeBoardImpl();
                        for (int i = 0; i < 9; ++i)
                        {
                            board.set(i, conn.readInt());
                        }
                        int size = conn.readInt();
                        int[] validPositions = new int[size];
                        for (int i = 0; i < size; ++i)
                        {
                            validPositions[i] = conn.readInt();
                        }
                        int choice = player.takeTurn(board, validPositions);
                        conn.writeInt(choice);
                        break;
                    case 3:
                        int result = conn.readInt();
                        player.endGame(result);
                        conn.close();
                        return;
                    default:
                        throw new RuntimeException("Invalid command: " + command);      
                }
            }   
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
