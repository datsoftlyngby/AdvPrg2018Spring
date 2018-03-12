/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dapi.input.keyboard;

/**
 *
 * @author tog
 */
public interface KeyboardListener
{
    public void onKeyPressed(KeyPressedEvent e);
    public void onKeyReleased(KeyReleasedEvent e);
}
