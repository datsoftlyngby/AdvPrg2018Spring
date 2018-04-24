/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;
import persistence.Serializer;
import persistence.examples.Address;
import persistence.examples.Person;
import persistence.examples.PersonFactory;

/**
 *
 * @author Tobias
 */
public class PersonSerializer implements Serializer<Person>
{
    private static Serializer<Person> instance;
    private final PersonFactory factory;

    private PersonSerializer(PersonFactory factory)
    {
        this.factory = factory;
    }
    
    
    @Override
    public void serialize(Person dataObj, DataOutput out) throws IOException
    {
        out.writeUTF(dataObj.getFirstName());
        out.writeUTF(dataObj.getLastName());
        out.writeLong(dataObj.getBirthDate().getTime());
        AddressSerializer.getInstance().serialize(dataObj.getAddress(), out);
    }

    @Override
    public Person deserialize(DataInput in) throws IOException
    {
        String firstName = in.readUTF();
        String lastName = in.readUTF();
        Date birthDate = new Date(in.readLong());
        Address address = AddressSerializer.getInstance().deserialize(in);
        return factory.newPerson(firstName, lastName, birthDate, address);
    }
    
    public static Serializer<Person> getInstance()
    {
        if(instance == null)
        {
            instance = new PersonSerializer(PersonFactoryImpl.getInstance());
        }
        return instance;
    }
    
}
