/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input;

import app2dapi.input.InputEvent;
import app2dapi.input.charinput.CharInputEvent;
import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.input.mouse.MouseMovedEvent;
import app2dapi.input.mouse.MousePressedEvent;
import app2dapi.input.mouse.MouseReleasedEvent;
import app2dapi.input.mouse.MouseWheelEvent;

/**
 *
 * @author tog
 */
public abstract class AbstractInputEvent implements InputEvent
{
    private final double when;

    public AbstractInputEvent(double when)
    {
        this.when = when;
    }
    
    @Override
    public final int compareTo(InputEvent other)
    {
        double time = other.getWhen();
        if(when < time) return -1;
        if(when > time) return 1;
        return 0;
    }

    @Override
    public final double getWhen()
    {
        return when;
    }

    @Override
    public KeyPressedEvent asKeyPressedEvent()
    {
        throw new UnsupportedOperationException("This is not a KeyPressedEvent, this is a " + getType().toString());
    }

    @Override
    public KeyReleasedEvent asKeyReleasedEvent()
    {
        throw new UnsupportedOperationException("This is not a KeyReleasedEvent, this is a " + getType().toString());
    }
    
    @Override
    public CharInputEvent asCharInputEvent()
    {
        throw new UnsupportedOperationException("This is not a KeyReleasedEvent, this is a " + getType().toString());
    }

    @Override
    public MousePressedEvent asMousePressedEvent()
    {
        throw new UnsupportedOperationException("This is not a MousePressedEvent, this is a " + getType().toString());
    }

    @Override
    public MouseReleasedEvent asMouseReleasedEvent()
    {
        throw new UnsupportedOperationException("This is not a MouseReleasedEvent, this is a " + getType().toString());
    }

    @Override
    public MouseMovedEvent asMouseMovedEvent()
    {
        throw new UnsupportedOperationException("This is not a MouseMovedEvent, this is a " + getType().toString());
    }

    @Override
    public MouseWheelEvent asMouseWheelEvent()
    {
        throw new UnsupportedOperationException("This is not a MouseWheelEvent, this is a " + getType().toString());
    }    
}
