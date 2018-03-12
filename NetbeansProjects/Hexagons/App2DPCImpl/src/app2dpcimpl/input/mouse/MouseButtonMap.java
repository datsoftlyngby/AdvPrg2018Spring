/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.mouse;

import app2dapi.input.mouse.MouseButton;
import java.awt.event.MouseEvent;

/**
 *
 * @author tog
 */
public class MouseButtonMap
{
    public static MouseButton getMouseButton(int mb)
    {
        switch(mb)
        {
            case MouseEvent.BUTTON1:
                return MouseButton.LEFT;
            case MouseEvent.BUTTON2:
                return MouseButton.MIDDLE;
            case MouseEvent.BUTTON3:
                return MouseButton.RIGHT;
            default:
                return MouseButton.UNKNOWN;
        }
    }
}
