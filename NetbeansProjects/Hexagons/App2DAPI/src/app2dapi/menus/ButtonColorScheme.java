/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.menus;

import app2dapi.graphics.Color;

/**
 *
 * @author Tobias Grundtvig
 */
public interface ButtonColorScheme
{
    public Color getBorderColor();
    public Color getBackgroundColor();
    public Color getTextColor();
    public Color getHoverBorderColor();
    public Color getHoverBackgroundColor();
    public Color getHoverTextColor();
    public Color getClickBorderColor();
    public Color getClickBackgroundColor();
    public Color getClickTextColor();
}
