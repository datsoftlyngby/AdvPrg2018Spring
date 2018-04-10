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
public class SubtractCommand implements Command
{
    private final Sum sum;
    private final double value;
    
    public SubtractCommand(Sum sum, double value)
    {
        this.sum = sum;
        this.value = value;
    }

    @Override
    public void execute()
    {
        sum.subtract(value);
    }

    @Override
    public Command getInverse()
    {
        return new AddCommand(sum, value);
    }
    
}
