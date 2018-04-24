/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples.impl;

import java.util.Date;
import persistence.examples.Address;
import persistence.examples.Person;
import persistence.examples.PersonFactory;

/**
 *
 * @author Tobias
 */
public class PersonFactoryImpl implements PersonFactory
{
    private static PersonFactory instance;

    private PersonFactoryImpl()
    {
    }
    
    
    @Override
    public Person newPerson(String firstName, String lastName, Date birthdate, Address address)
    {
        return new PersonImpl(firstName, lastName, birthdate, address);
    }
    
    public static PersonFactory getInstance()
    {
        if(instance == null)
        {
            instance = new PersonFactoryImpl();
        }
        return instance;
    }
    
    private static class PersonImpl implements Person
    {
        private final String firstName;
        private final String lastName;
        private final Date birthDate;
        private Address address;

        public PersonImpl(String firstName, String lastName, Date birthDate, Address address)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.address = address;
        }
        
        
        @Override
        public String getFirstName()
        {
            return firstName;
        }

        @Override
        public String getLastName()
        {
            return lastName;
        }

        @Override
        public Date getBirthDate()
        {
            return birthDate;
        }

        @Override
        public Address getAddress()
        {
            return address;
        }

        @Override
        public void setAddress(Address address)
        {
            this.address = address;
        }
        
        @Override
        public String toString()
        {
            return firstName + " " + lastName + " " + birthDate + " " + address;
        }
    }
}
