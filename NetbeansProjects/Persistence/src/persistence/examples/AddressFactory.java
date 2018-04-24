/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples;

/**
 *
 * @author Tobias
 */
public interface AddressFactory
{
    public Address newAddress(String street, int number, int zipcode);
}
