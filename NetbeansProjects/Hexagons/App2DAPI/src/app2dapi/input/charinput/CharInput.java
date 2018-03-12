/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dapi.input.charinput;

/**
 *
 * @author tog
 */
public interface CharInput
{
    public void addCharInputListener(CharInputListener listener);
    public void removeCharInputListener(CharInputListener listener);
}
