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
public interface Person
{
    public String getFirstName();
    public String getLastName();
    public Date getBirthDate();
    public Address getAddress();
    public void setAddress(Address address);
}
