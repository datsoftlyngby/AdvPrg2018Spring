/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.keyboard;

import app2dapi.input.InputEventType;
import app2dapi.input.keyboard.Key;
import app2dapi.input.keyboard.KeyPressedEvent;

/**
 *
 * @author tog
 */
public class KeyPressedEventImpl extends AbstractKeyEvent implements KeyPressedEvent
{
    public KeyPressedEventImpl(double when, Key key)
    {
        super(when, key);
    }
    
    @Override
    public InputEventType getType()
    {
        return InputEventType.KEY_PRESSED_EVENT;
    }

    @Override
    public KeyPressedEvent asKeyPressedEvent()
    {
        return this;
    }
}
