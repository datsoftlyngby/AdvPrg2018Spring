/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import persistence.DataOut;
import persistence.Persistence;
import persistence.Serializer;
import persistence.examples.Address;
import persistence.examples.Person;
import persistence.examples.PersonFactory;
import persistence.examples.impl.AddressFactoryImpl;
import persistence.examples.impl.PersonSerializer;
import persistence.examples.impl.PersonFactoryImpl;
import persistence.impl.FilePersistence;

/**
 *
 * @author Tobias
 */
public class DemoPut
{
    public static void main(String[] args) throws IOException
    {
       
        PersonFactory factory = PersonFactoryImpl.getInstance();
        Address address = AddressFactoryImpl.getInstance().newAddress("Torvegade", 27, 1400);
        Person tobias = factory.newPerson("Tobias", "Grundtvig", new Date(0), address);
        
        
        Serializer<Person> serializer = PersonSerializer.getInstance();
        Persistence storage =  new FilePersistence(Paths.get("C:\\tmp\\storage"));
        
        try (DataOut out = storage.put(42))
        {
            serializer.serialize(tobias, out);
        }
    }
}
