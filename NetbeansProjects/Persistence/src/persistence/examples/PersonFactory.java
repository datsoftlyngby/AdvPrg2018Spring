/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.examples;

import java.util.Date;

/**
 *
 * @author Tobias
 */
public interface PersonFactory
{
    public Person newPerson(String firstName, String lastName, Date birthdate);
}
