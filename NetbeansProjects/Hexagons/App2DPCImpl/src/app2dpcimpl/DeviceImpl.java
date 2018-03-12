/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl;

import app2dapi.Device;
import app2dapi.geometry.G2D;
import app2dapi.graphics.Screen;
import app2dapi.input.charinput.CharInput;
import app2dapi.input.keyboard.Keyboard;
import app2dapi.input.mouse.Mouse;

/**
 *
 * @author tog
 */
public class DeviceImpl implements Device
{
    private final Screen screen;
    private final Keyboard kb;
    private final Mouse mouse;
    private final CharInput charInput;
    private final G2D g2d;

    public DeviceImpl(Screen screen, Keyboard kb, Mouse mouse, CharInput charInput, G2D g2d)
    {
        this.screen = screen;
        this.kb = kb;
        this.mouse = mouse;
        this.charInput = charInput;
        this.g2d = g2d;
    }
    
    
    @Override
    public Screen getScreen()
    {
        return screen;
    }

    @Override
    public Keyboard getKeyboard()
    {
        return kb;
    }

    @Override
    public Mouse getMouse()
    {
        return mouse;
    }

    @Override
    public CharInput getCharInput()
    {
        return charInput;
    }

    @Override
    public G2D getGeometry2D()
    {
        return g2d;
    }
    
}
