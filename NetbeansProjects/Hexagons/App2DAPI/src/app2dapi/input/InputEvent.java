/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dapi.input;

import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.input.mouse.MouseMovedEvent;
import app2dapi.input.mouse.MousePressedEvent;
import app2dapi.input.mouse.MouseReleasedEvent;
import app2dapi.input.mouse.MouseWheelEvent;
import app2dapi.input.charinput.CharInputEvent;

/**
 *
 * @author tog
 */
public interface InputEvent extends Comparable<InputEvent>
{
    public InputEventType getType();
    public double getWhen();
    
    //Downcasts
    public KeyPressedEvent asKeyPressedEvent();
    public KeyReleasedEvent asKeyReleasedEvent();
    public CharInputEvent asCharInputEvent();
    public MousePressedEvent asMousePressedEvent();
    public MouseReleasedEvent asMouseReleasedEvent();
    public MouseMovedEvent asMouseMovedEvent();
    public MouseWheelEvent asMouseWheelEvent();
}
