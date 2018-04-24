/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import java.io.IOException;
import java.nio.file.Paths;
import persistence.DataIn;
import persistence.Persistence;
import persistence.Serializer;
import persistence.examples.Person;
import persistence.examples.PersonFactory;
import persistence.examples.impl.PersonSerializer;
import persistence.examples.impl.PersonFactoryImpl;
import persistence.impl.FilePersistence;

/**
 *
 * @author Tobias
 */
public class DemoGet
{

    public static void main(String[] args) throws IOException
    {
        PersonFactory factory = PersonFactoryImpl.getInstance();
        Persistence storage = new FilePersistence(Paths.get("C:\\tmp\\storage"));
        Serializer<Person> serializer = PersonSerializer.getInstance();
        Person p;
        try (DataIn in = storage.get(42))
        {
            if(in == null)
            {
                p = null;
            }
            else
            {
                p = serializer.deserialize(in);
            }
        }
        System.out.println(p);
    }
}
