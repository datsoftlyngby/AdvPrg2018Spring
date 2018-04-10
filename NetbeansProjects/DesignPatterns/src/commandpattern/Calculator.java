/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author Tobias
 */
public class Calculator
{
    private final Sum sum;
    private final Deque<Command> undos = new LinkedList<>();
    private final Deque<Command> redos = new LinkedList<>();

    public Calculator()
    {
        this.sum = new SumImpl();
    }
    
    public void execute(Command c)
    {
        c.execute();
        undos.addFirst(c.getInverse());
    }
    
    public void undo()
    {
        Command c = undos.pollFirst();
        c.execute();
        redos.addFirst(c.getInverse());
    }
    
    public void redo()
    {
        Command c = redos.pollFirst();
        c.execute();
        undos.addFirst(c.getInverse());
    }
    
    public Sum getSum()
    {
        return sum;
    }
}
