/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * @author Tobias
 * @param <T>
 */
public interface Serializer<T>
{
    public void serialize(T dataObj, DataOutput out) throws IOException;
    public T deserialize(DataInput in) throws IOException;
}
