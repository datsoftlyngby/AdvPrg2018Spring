/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.IOException;

/**
 *
 * @author Tobias
 */
public class TextualInterfaceConnectionImpl implements TextualInterfaceConnection
{
    private final SocketConnection conn;

    public TextualInterfaceConnectionImpl(SocketConnection conn)
    {
        this.conn = conn;
    }

    @Override
    public void writeInt(int i) throws IOException
    {
        conn.writeInt(i);
    }

    @Override
    public void writeString(String str) throws IOException
    {
        conn.writeUTF(str);
    }

    @Override
    public void writeStringArray(String[] array) throws IOException
    {
        conn.writeInt(array.length);
        for(String str : array)
        {
            conn.writeUTF(str);
        }
    }

    @Override
    public int readInt() throws IOException
    {
        return conn.readInt();
    }

    @Override
    public String readString() throws IOException
    {
        return conn.readUTF();
    }

    @Override
    public String[] readStringArray() throws IOException
    {
        int length = conn.readInt();
        String[] res = new String[length];
        for(int i = 0; i < length; ++i)
        {
            res[i] = conn.readUTF();
        }
        return res;
    }

    @Override
    public void close() throws IOException
    {
        conn.close();
    }
    
}
