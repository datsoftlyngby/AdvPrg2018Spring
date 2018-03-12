/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.keyboard;

import app2dapi.input.keyboard.Key;
import app2dapi.input.keyboard.KeyEvent;
import app2dpcimpl.input.AbstractInputEvent;


/**
 *
 * @author tog
 */
public abstract class AbstractKeyEvent extends AbstractInputEvent implements KeyEvent
{
    private final Key key;

    public AbstractKeyEvent(double when, Key key)
    {
        super(when);
        this.key = key;
    }
   
    @Override
    public Key getKey()
    {
        return key;
    }   
}
