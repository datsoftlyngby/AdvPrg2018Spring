/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.keyboard;

import app2dapi.input.InputEventType;
import app2dapi.input.keyboard.Key;
import app2dapi.input.keyboard.KeyReleasedEvent;

/**
 *
 * @author tog
 */
public class KeyReleasedEventImpl extends AbstractKeyEvent implements KeyReleasedEvent
{
    public KeyReleasedEventImpl(double when, Key key)
    {
        super(when, key);
    }

    @Override
    public InputEventType getType()
    {
        return InputEventType.KEY_RELEASED_EVENT;
    }

    @Override
    public KeyReleasedEvent asKeyReleasedEvent()
    {
        return this;
    }  
}
