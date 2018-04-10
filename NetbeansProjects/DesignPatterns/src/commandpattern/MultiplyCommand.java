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
public class MultiplyCommand implements Command
{
    private Sum sum;
    private double value;

    public MultiplyCommand(Sum sum, double value)
    {
        this.sum = sum;
        this.value = value;
    }
    
    
    @Override
    public void execute()
    {
        sum.multiply(value);
    }

    @Override
    public Command getInverse()
    {
        return new DivideCommand(sum, value);
    }
    
}
