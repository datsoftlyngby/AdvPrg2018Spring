/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import persistence.Serializer;
import persistence.examples.Address;
import persistence.examples.AddressFactory;

/**
 *
 * @author Tobias
 */
public class AddressSerializer implements Serializer<Address>
{
    private static Serializer<Address> instance;
    private final AddressFactory factory;

    private AddressSerializer(AddressFactory factory)
    {
        this.factory = factory;
    }
    
    
    @Override
    public void serialize(Address dataObj, DataOutput out) throws IOException
    {
        out.writeUTF(dataObj.getStreet());
        out.writeInt(dataObj.getNumber());
        out.writeInt(dataObj.getZipCode());
    }

    @Override
    public Address deserialize(DataInput in) throws IOException
    {
        String street = in.readUTF();
        int number = in.readInt();
        int zipCode = in.readInt();
        return factory.newAddress(street, number, zipCode);
    }
    
    public static Serializer<Address> getInstance()
    {
        if(instance == null)
        {
            instance = new AddressSerializer(AddressFactoryImpl.getInstance());
        }
        return instance;
    }
    
}
