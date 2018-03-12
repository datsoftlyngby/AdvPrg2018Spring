/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.graphics;

import app2dapi.graphics.Color;



/**
 *
 * @author tog
 */
public class ColorImpl implements Color
{
    private final java.awt.Color col;
    
    ColorImpl(java.awt.Color col)
    {
        this.col = col;
    }
    
    
    ColorImpl(int c)
    {
        col = new java.awt.Color(c, true);
    }
    
    ColorImpl(float r, float g, float b, float a)
    {
        col = new java.awt.Color(r, g, b, a);
    }
    
    java.awt.Color getAWTColor()
    {
        return col;
    }

    @Override
    public float r()
    {
        return col.getRed();
    }

    @Override
    public float g()
    {
        return col.getGreen();
    }

    @Override
    public float b()
    {
        return col.getBlue();
    }

    @Override
    public float a()
    {
        return col.getAlpha();
    }

    @Override
    public int getRGBA()
    {
        return col.getRGB();
    }
    
}
