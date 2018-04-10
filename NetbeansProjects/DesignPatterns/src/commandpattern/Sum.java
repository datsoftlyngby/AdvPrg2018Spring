/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

/**
 *
 * @author Tobias
 */
public interface Sum
{
    public double getValue();
    public void add(double value);
    public void subtract(double value);
    public void multiply(double value);
    public void divide(double value);
}
