/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.tobiasgrundtvig.util.socket.impl;

import dk.tobiasgrundtvig.util.socket.SocketConnection;
import java.io.BufferedInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Tobias
 */
public class SocketConnectionImpl implements SocketConnection
{
    private Socket socket;
    private DataInput in;
    private DataOutput out;

    public SocketConnectionImpl(Socket socket) throws IOException
    {
        this.socket = socket;
        this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.out = new DataOutputStream(socket.getOutputStream());
    }
    
    public SocketConnectionImpl(String host, int port) throws IOException
    {
        this(new Socket(host, port));
    }

    @Override
    public void readFully(byte[] b) throws IOException
    {
        in.readFully(b);
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException
    {
        in.readFully(b, off, len);
    }

    @Override
    public int skipBytes(int n) throws IOException
    {
        return in.skipBytes(n);
    }

    @Override
    public boolean readBoolean() throws IOException
    {
        return in.readBoolean();
    }

    @Override
    public byte readByte() throws IOException
    {
        return in.readByte();
    }

    @Override
    public int readUnsignedByte() throws IOException
    {
        return in.readUnsignedByte();
    }

    @Override
    public short readShort() throws IOException
    {
        return in.readShort();
    }

    @Override
    public int readUnsignedShort() throws IOException
    {
        return in.readUnsignedShort();
    }

    @Override
    public char readChar() throws IOException
    {
        return in.readChar();
    }

    @Override
    public int readInt() throws IOException
    {
        return in.readInt();
    }

    @Override
    public long readLong() throws IOException
    {
        return in.readLong();
    }

    @Override
    public float readFloat() throws IOException
    {
        return in.readFloat();
    }

    @Override
    public double readDouble() throws IOException
    {
        return in.readDouble();
    }

    @Override
    public String readLine() throws IOException
    {
        return in.readLine();
    }

    @Override
    public String readUTF() throws IOException
    {
        return in.readUTF();
    }

    @Override
    public void write(int b) throws IOException
    {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException
    {
        out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        out.write(b, off, len);
    }

    @Override
    public void writeBoolean(boolean v) throws IOException
    {
        out.writeBoolean(v);
    }

    @Override
    public void writeByte(int v) throws IOException
    {
        out.writeByte(v);
    }

    @Override
    public void writeShort(int v) throws IOException
    {
        out.writeShort(v);
    }

    @Override
    public void writeChar(int v) throws IOException
    {
        out.writeChar(v);
    }

    @Override
    public void writeInt(int v) throws IOException
    {
        out.writeInt(v);
    }

    @Override
    public void writeLong(long v) throws IOException
    {
        out.writeLong(v);
    }

    @Override
    public void writeFloat(float v) throws IOException
    {
        out.writeFloat(v);
    }

    @Override
    public void writeDouble(double v) throws IOException
    {
        out.writeDouble(v);
    }

    @Override
    public void writeBytes(String s) throws IOException
    {
        out.writeBytes(s);
    }

    @Override
    public void writeChars(String s) throws IOException
    {
        out.writeChars(s);
    }

    @Override
    public void writeUTF(String s) throws IOException
    {
        out.writeUTF(s);
    }

    @Override
    public void close() throws IOException
    {
        socket.close();
    }
    
}
