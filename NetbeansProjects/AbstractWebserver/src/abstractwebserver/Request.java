/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractwebserver;

/**
 *
 * @author Tobias
 */
public interface Request
{
    public int getNumberOfParameters();
    public Parameter getParameter(int i);
    public Parameter getParameter(String name);
    public String getParameterValue(String name);
}
