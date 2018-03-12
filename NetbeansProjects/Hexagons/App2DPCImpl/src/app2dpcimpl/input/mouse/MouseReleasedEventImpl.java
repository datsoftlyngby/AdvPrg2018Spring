/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.mouse;

import app2dapi.input.InputEventType;
import app2dapi.input.mouse.MouseButton;
import app2dapi.input.mouse.MouseReleasedEvent;

/**
 *
 * @author tog
 */
public class MouseReleasedEventImpl extends AbstractMouseEvent implements MouseReleasedEvent
{
    private final MouseButton button;

    public MouseReleasedEventImpl(double when, int x, int y, MouseButton button)
    {
        super(when, x, y);
        this.button = button;
    }

    @Override
    public MouseButton getButton()
    {
        return button;
    }

    @Override
    public InputEventType getType()
    {
        return InputEventType.MOUSE_RELEASED_EVENT;
    }

    @Override
    public MouseReleasedEvent asMouseReleasedEvent()
    {
        return this;
    }   
}
