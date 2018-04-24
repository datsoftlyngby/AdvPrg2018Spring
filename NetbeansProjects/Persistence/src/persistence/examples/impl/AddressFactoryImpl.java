/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples.impl;

import persistence.examples.Address;
import persistence.examples.AddressFactory;

/**
 *
 * @author Tobias
 */
public class AddressFactoryImpl implements AddressFactory
{
    private static AddressFactory instance;

    private AddressFactoryImpl()
    {
    }
    
    @Override
    public Address newAddress(String street, int number, int zipcode)
    {
        return new AddressImpl(street, number, zipcode);
    }
    
    public static AddressFactory getInstance()
    {
        if(instance == null)
        {
            instance = new AddressFactoryImpl();
        }
        return instance;
    }
    
    private static class AddressImpl implements Address
    {
        private final String street;
        private final int number;
        private final int zipCode;

        public AddressImpl(String street, int number, int zipCode)
        {
            this.street = street;
            this.number = number;
            this.zipCode = zipCode;
        }

        @Override
        public String getStreet()
        {
            return street;
        }

        @Override
        public int getNumber()
        {
            return number;
        }

        @Override
        public int getZipCode()
        {
            return zipCode;
        }
        
        @Override
        public String toString()
        {
            return street + " " + number + ", " + zipCode;
        }
    }
}
