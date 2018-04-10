/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples.impl;

import java.util.Date;
import persistence.examples.Person;
import persistence.examples.PersonFactory;

/**
 *
 * @author Tobias
 */
public class PersonFactoryImpl implements PersonFactory
{

    @Override
    public Person newPerson(String firstName, String lastName, Date birthdate)
    {
        return new PersonImpl(firstName, lastName, birthdate);
    }
    
    private static class PersonImpl implements Person
    {
        private final String firstName;
        private final String lastName;
        private final Date birthDate;

        public PersonImpl(String firstName, String lastName, Date birthDate)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
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
        public String toString()
        {
            return firstName + " " + lastName + " " + birthDate;
        }
    }
}
