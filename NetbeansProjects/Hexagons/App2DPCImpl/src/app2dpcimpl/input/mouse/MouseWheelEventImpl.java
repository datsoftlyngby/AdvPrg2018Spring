/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.mouse;

import app2dapi.input.InputEventType;
import app2dapi.input.mouse.MouseWheelEvent;

/**
 *
 * @author tog
 */
public class MouseWheelEventImpl extends AbstractMouseEvent implements MouseWheelEvent
{
    private final int numberOfClicks;

    public MouseWheelEventImpl(double when, int x, int y, int numberOfClicks)
    {
        super(when, x, y);
        this.numberOfClicks = numberOfClicks;
    }
    
    @Override
    public int getNumberOfClicks()
    {
        return numberOfClicks;
    }
    
    @Override
    public InputEventType getType()
    {
        return InputEventType.MOUSE_WHEEL_EVENT;
    }

    @Override
    public MouseWheelEvent asMouseWheelEvent()
    {
        return this;
    }
}
