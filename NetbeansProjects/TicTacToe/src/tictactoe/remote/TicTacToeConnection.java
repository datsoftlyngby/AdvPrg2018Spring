/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.remote;

import java.io.Closeable;
import java.io.IOException;
import tictactoe.TicTacToeBoard;

/**
 *
 * @author Tobias
 */
public interface TicTacToeConnection extends Closeable
{
    public void writeInt(int i) throws IOException;
    public void writeIntArray(int[] array) throws IOException;
    public void writeString(String str) throws IOException;
    public void writeBoard(TicTacToeBoard board) throws IOException;
    
    public int readInt() throws IOException;
    public int[] readIntArray() throws IOException;
    public String readString() throws IOException;
    public TicTacToeBoard readBoard() throws IOException;
}
