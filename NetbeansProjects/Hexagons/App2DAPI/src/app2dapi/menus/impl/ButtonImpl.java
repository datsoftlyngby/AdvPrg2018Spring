/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.menus.impl;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.menus.Button;
import app2dapi.menus.ButtonColorScheme;

/**
 *
 * @author Tobias Grundtvig
 */
public class ButtonImpl implements Button
{

    private final G2D g2d;
    private double width;
    private double height;
    private Polygon outer;
    private Polygon inner;
    private String text;
    private ButtonColorScheme colors;

    public ButtonImpl(G2D g2d, double width, double height, String text,
                      ButtonColorScheme colors)
    {
        this.g2d = g2d;
        this.width = width;
        this.height = height;
        this.text = text;
        this.colors = colors;
        buildPolygons();
    }

    private void buildPolygons()
    {
        
    }

}
