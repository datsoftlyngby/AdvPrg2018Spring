/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import persistence.DataIn;
import persistence.DataOut;
import persistence.Persistence;

/**
 *
 * @author Tobias
 */
public class FilePersistence implements Persistence
{
    private final Path storageDir;

    public FilePersistence(Path directory)
    {
        storageDir = directory;
    }
    
    
    @Override
    public DataOut put(long id) throws IOException
    {
        String name = "L"+id+".data";
        Path p = storageDir.resolve(name);
        return new DataOutImpl(new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(p, CREATE))));
    }

    @Override
    public DataIn get(long id) throws IOException
    {
        String name = "L"+id+".data";
        Path p = storageDir.resolve(name);
        if(Files.notExists(p))
        {
            return null;
        }
        return new DataInImpl(new DataInputStream(new BufferedInputStream(Files.newInputStream(p, CREATE))));
    }
    
    private static class DataInImpl implements DataIn
    {
        private final DataInputStream in;

        public DataInImpl(DataInputStream in)
        {
            this.in = in;
        }


        @Override
        public final void readFully(byte[] b) throws IOException
        {
            in.readFully(b);
        }

        @Override
        public final void readFully(byte[] b, int off, int len) throws IOException
        {
            in.readFully(b, off, len);
        }

        @Override
        public final int skipBytes(int n) throws IOException
        {
            return in.skipBytes(n);
        }

        @Override
        public final boolean readBoolean() throws IOException
        {
            return in.readBoolean();
        }

        @Override
        public final byte readByte() throws IOException
        {
            return in.readByte();
        }

        @Override
        public final int readUnsignedByte() throws IOException
        {
            return in.readUnsignedByte();
        }

        @Override
        public final short readShort() throws IOException
        {
            return in.readShort();
        }

        @Override
        public final int readUnsignedShort() throws IOException
        {
            return in.readUnsignedShort();
        }

        @Override
        public final char readChar() throws IOException
        {
            return in.readChar();
        }

        @Override
        public final int readInt() throws IOException
        {
            return in.readInt();
        }

        @Override
        public final long readLong() throws IOException
        {
            return in.readLong();
        }

        @Override
        public final float readFloat() throws IOException
        {
            return in.readFloat();
        }

        @Override
        public final double readDouble() throws IOException
        {
            return in.readDouble();
        }

        @Override
        public final String readLine() throws IOException
        {
            return in.readLine();
        }

        @Override
        public final String readUTF() throws IOException
        {
            return in.readUTF();
        }

        @Override
        public void close() throws IOException
        {
            in.close();
        }
        
    }
    
    private static class DataOutImpl implements DataOut
    {
        private final DataOutputStream out;

        public DataOutImpl(DataOutputStream out)
        {
            this.out = out;
        }

        @Override
        public synchronized void write(int b) throws IOException
        {
            out.write(b);
        }

        @Override
        public synchronized void write(byte[] b, int off, int len) throws IOException
        {
            out.write(b, off, len);
        }
        
        @Override
        public final void writeBoolean(boolean v) throws IOException
        {
            out.writeBoolean(v);
        }

        @Override
        public final void writeByte(int v) throws IOException
        {
            out.writeByte(v);
        }

        @Override
        public final void writeShort(int v) throws IOException
        {
            out.writeShort(v);
        }

        @Override
        public final void writeChar(int v) throws IOException
        {
            out.writeChar(v);
        }

        @Override
        public final void writeInt(int v) throws IOException
        {
            out.writeInt(v);
        }

        @Override
        public final void writeLong(long v) throws IOException
        {
            out.writeLong(v);
        }

        @Override
        public final void writeFloat(float v) throws IOException
        {
            out.writeFloat(v);
        }

        @Override
        public final void writeDouble(double v) throws IOException
        {
            out.writeDouble(v);
        }

        @Override
        public final void writeBytes(String s) throws IOException
        {
            out.writeBytes(s);
        }

        @Override
        public final void writeChars(String s) throws IOException
        {
            out.writeChars(s);
        }

        @Override
        public final void writeUTF(String str) throws IOException
        {
            out.writeUTF(str);
        }
        
        @Override
        public void write(byte[] b) throws IOException
        {
            out.write(b);
        }

        @Override
        public void close() throws IOException
        {
            out.close();
        }        
    }
    
}
