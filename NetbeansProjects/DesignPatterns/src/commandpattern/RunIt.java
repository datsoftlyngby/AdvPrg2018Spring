/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandpattern;

import java.util.Scanner;

/**
 *
 * @author Tobias
 */
public class RunIt
{
    public static void main(String[] args)
    {
        Calculator cal = new Calculator();
        Interpreter parser = new Interpreter(cal);
        Scanner scan = new Scanner(System.in);
        while(true)
        {
            String s = scan.nextLine();
            parser.interpret(s);
            System.out.println("Sum: " + cal.getSum().getValue());
        }
    }
}
