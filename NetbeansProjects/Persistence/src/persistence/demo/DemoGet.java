/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.demo;

import java.io.IOException;
import persistence.DataIn;
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
public class DemoGet
{

    public static void main(String[] args) throws IOException
    {
        PersonFactory factory = new PersonFactoryImpl();
        Persistence storage = null;
        Serializer<Person> serializer = new PersonSerializer(factory);
        DataIn in = storage.get(42);
        Person p = serializer.deserialize(in);
        in.close();
        System.out.println(p);
    }
}
