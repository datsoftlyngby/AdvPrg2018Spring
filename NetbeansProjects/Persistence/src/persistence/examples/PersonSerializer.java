/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Date;
import persistence.Serializer;

/**
 *
 * @author Tobias
 */
public class PersonSerializer implements Serializer<Person>
{
    private final PersonFactory factory;

    public PersonSerializer(PersonFactory factory)
    {
        this.factory = factory;
    }
    
    
    @Override
    public void serialize(Person dataObj, DataOutput out) throws IOException
    {
        out.writeUTF(dataObj.getFirstName());
        out.writeUTF(dataObj.getLastName());
        out.writeLong(dataObj.getBirthDate().getTime());
    }

    @Override
    public Person deserialize(DataInput in) throws IOException
    {
        String firstName = in.readUTF();
        String lastName = in.readUTF();
        Date birthDate = new Date(in.readLong());
        return factory.newPerson(firstName, lastName, birthDate);
    }
    
}
