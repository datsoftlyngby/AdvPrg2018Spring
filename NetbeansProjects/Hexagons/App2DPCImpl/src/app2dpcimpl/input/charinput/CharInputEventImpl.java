/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.charinput;

import app2dapi.input.InputEventType;
import app2dapi.input.charinput.CharInputEvent;
import app2dpcimpl.input.AbstractInputEvent;

/**
 *
 * @author tog
 */
public class CharInputEventImpl extends AbstractInputEvent implements CharInputEvent
{
    private final char ch;

    public CharInputEventImpl(double when, char ch)
    {
        super(when);
        this.ch = ch;
    }
    
    @Override
    public InputEventType getType()
    {
        return InputEventType.CHAR_TYPED_EVENT;
    }

    @Override
    public char getChar()
    {
        return ch;
    }
    
    @Override
    public CharInputEvent asCharInputEvent()
    {
        return this;
    }
    
}
