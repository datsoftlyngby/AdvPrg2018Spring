/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input.mouse;

import app2dapi.input.mouse.MouseEvent;
import app2dpcimpl.input.AbstractInputEvent;


/**
 *
 * @author tog
 */
public abstract class AbstractMouseEvent extends AbstractInputEvent implements MouseEvent
{
    private final int x;
    private final int y;

    public AbstractMouseEvent(double when, int x, int y)
    {
        super(when);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }
}
