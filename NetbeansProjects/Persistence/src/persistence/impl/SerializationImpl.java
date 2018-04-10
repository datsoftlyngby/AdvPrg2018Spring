/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import persistence.Serialization;
import persistence.Serializer;


/**
 *
 * @author Tobias
 */
public class SerializationImpl implements Serialization
{

    @Override
    public <T> void serialize(T[] array, Serializer<T> serializer, DataOutput out) throws IOException
    {
        out.writeInt(array.length);
        for(T t : array)
        {
            serializer.serialize(t, out);
        }
    }

    @Override
    public <T> T[] deserialize(Serializer<T> serializer, DataInput in) throws IOException
    {
        int length = in.readInt();
        T[] res = (T[]) new Object[length];
        for(int i = 0; i < length; ++i)
        {
            res[i] = serializer.deserialize(in);
        }
        return res;
    }

    
    
}
