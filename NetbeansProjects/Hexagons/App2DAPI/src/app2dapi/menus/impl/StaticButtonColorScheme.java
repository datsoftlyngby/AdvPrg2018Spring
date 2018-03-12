/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.menus.impl;

import app2dapi.graphics.Color;
import app2dapi.menus.ButtonColorScheme;

/**
 *
 * @author Tobias Grundtvig
 */
public class StaticButtonColorScheme implements ButtonColorScheme
{

    private final Color borderColor;
    private final Color backgroundColor;
    private final Color textColor;
    private final Color hoverBorderColor;
    private final Color hoverBackgroundColor;
    private final Color hoverTextColor;
    private final Color clickBorderColor;
    private final Color clickBackgroundColor;
    private final Color clickTextColor;

    public StaticButtonColorScheme(Color borderColor, Color backgroundColor,
                                   Color textColor, Color hoverBorderColor,
                                   Color hoverBackgroundColor,
                                   Color hoverTextColor, Color clickBorderColor,
                                   Color clickBackgroundColor,
                                   Color clickTextColor)
    {
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.hoverBorderColor = hoverBorderColor;
        this.hoverBackgroundColor = hoverBackgroundColor;
        this.hoverTextColor = hoverTextColor;
        this.clickBorderColor = clickBorderColor;
        this.clickBackgroundColor = clickBackgroundColor;
        this.clickTextColor = clickTextColor;
    }

    @Override
    public Color getBorderColor()
    {
        return borderColor;
    }

    @Override
    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    @Override
    public Color getTextColor()
    {
        return textColor;
    }

    @Override
    public Color getHoverBorderColor()
    {
        return hoverBorderColor;
    }

    @Override
    public Color getHoverBackgroundColor()
    {
        return hoverBackgroundColor;
    }

    @Override
    public Color getHoverTextColor()
    {
        return hoverTextColor;
    }

    @Override
    public Color getClickBorderColor()
    {
        return clickBorderColor;
    }

    @Override
    public Color getClickBackgroundColor()
    {
        return clickBackgroundColor;
    }

    @Override
    public Color getClickTextColor()
    {
        return clickTextColor;
    }  
}
