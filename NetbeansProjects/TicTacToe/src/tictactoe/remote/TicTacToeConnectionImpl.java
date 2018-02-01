/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.IOException;
import tictactoe.TicTacToeBoard;
import tictactoe.impl.TicTacToeBoardImpl;

/**
 *
 * @author Tobias
 */
public class TicTacToeConnectionImpl implements TicTacToeConnection
{
    private SocketConnection conn;

    public TicTacToeConnectionImpl(SocketConnection conn)
    {
        this.conn = conn;
    }

    @Override
    public void writeInt(int i) throws IOException
    {
        conn.writeInt(i);
    }
    
    @Override
    public int readInt() throws IOException
    {
        return conn.readInt();
    }

    @Override
    public void writeIntArray(int[] array) throws IOException
    {
        conn.writeInt(array.length);
        for(int i = 0; i < array.length; ++i)
        {
            conn.writeInt(array[i]);
        }
    }
    
    @Override
    public int[] readIntArray() throws IOException
    {
        int length = conn.readInt();
        int[] res = new int[length];
        for(int i = 0; i < length; ++i)
        {
            res[i] = conn.readInt();
        }
        return res;
    }
    
    @Override
    public void writeString(String str) throws IOException
    {
        conn.writeUTF(str);
    }

    @Override
    public String readString() throws IOException
    {
        return conn.readUTF();
    }

    @Override
    public void writeBoard(TicTacToeBoard board) throws IOException
    {
        for(int i = 0; i < 9; ++i)
        {
            conn.write(board.get(i));
        }
    }  

    @Override
    public TicTacToeBoard readBoard() throws IOException
    {
        TicTacToeBoardImpl board = new TicTacToeBoardImpl();
        for(int i = 0; i < 9; ++i)
        {
            board.set(i, conn.readByte());
        }
        return board;
    }

    @Override
    public void close() throws IOException
    {
        conn.close();
    }

    
    
    
    
}
