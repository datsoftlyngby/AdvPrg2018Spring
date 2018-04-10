/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import java.io.IOException;
import java.util.Date;
import persistence.DataOut;
import persistence.Persistence;
import persistence.Serializer;
import persistence.examples.Person;
import persistence.examples.PersonFactory;
import persistence.examples.PersonSerializer;
import persistence.examples.impl.PersonFactoryImpl;

/**
 *
 * @author Tobias
 */
public class DemoPut
{
    public static void main(String[] args) throws IOException
    {
        PersonFactory factory = new PersonFactoryImpl();
        Persistence storage = null;
        Serializer<Person> serializer = new PersonSerializer(factory);
        Person tobias = factory.newPerson("Tobias", "Grundtvig", new Date(0));
        try (DataOut out = storage.put(42))
        {
            serializer.serialize(tobias, out);
        }
    }
}
