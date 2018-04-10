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
public class SumImpl implements Sum
{
    private double value;
    
    @Override
    public double getValue()
    {
        return value;
    }

    @Override
    public void add(double value)
    {
        this.value += value;
    }

    @Override
    public void subtract(double value)
    {
        this.value -= value;
    }

    @Override
    public void multiply(double value)
    {
        this.value *= value;
    }

    @Override
    public void divide(double value)
    {
        this.value /= value;
    }
    
}
