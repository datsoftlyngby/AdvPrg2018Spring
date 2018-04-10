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
public class Interpreter
{
    private final Calculator cal;

    public Interpreter(Calculator cal)
    {
        this.cal = cal;
    }
    
    public void interpret(String s)
    {
        String[] words = s.split("\\s+");
        switch(words[0])
        {
            case "add":
                cal.execute(new AddCommand(cal.getSum(), Double.parseDouble(words[1])));
                break;
            case "sub":
                cal.execute(new SubtractCommand(cal.getSum(), Double.parseDouble(words[1])));
                break;
            case "mul":
                cal.execute(new MultiplyCommand(cal.getSum(), Double.parseDouble(words[1])));
                break;
            case "div":
                cal.execute(new DivideCommand(cal.getSum(), Double.parseDouble(words[1])));
                break;
            case "undo":
                cal.undo();
                break;
            case "redo":
                cal.redo();
                break;
                
            default:
                throw new RuntimeException("The person sitting at the keyboard is an idiot. Or maybe the programmer is.");    
        }
    }
}
