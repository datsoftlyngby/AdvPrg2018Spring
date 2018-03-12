/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.panandzoom2dapp;

import app2dapi.geometry.G2D.Point2D;

/**
 *
 * @author Tobias Grundtvig
 */
public class PanAndZoomInit
{
    private final Point2D hudMin;
    private final Point2D hudMax;
    private final Point2D worldMin;
    private final Point2D worldMax;
    private final Point2D worldStartPos;
    private final double viewStartWidth;
    private final double viewMinWidth;
    private final double viewMaxWidth;

    public PanAndZoomInit(Point2D hudMin,
                          Point2D hudMax,
                          Point2D worldMin,
                          Point2D worldMax,
                          Point2D worldStartPos,
                          double viewStartWidth,
                          double viewMinWidth,
                          double viewMaxWidth)
    {
        this.hudMin = hudMin;
        this.hudMax = hudMax;
        this.worldMin = worldMin;
        this.worldMax = worldMax;
        this.worldStartPos = worldStartPos;
        this.viewStartWidth = viewStartWidth;
        this.viewMinWidth = viewMinWidth;
        this.viewMaxWidth = viewMaxWidth;
    }

    public Point2D getHUDMin()
    {
        return hudMin;
    }

    public Point2D getHUDMax()
    {
        return hudMax;
    }

    public Point2D getWorldMin()
    {
        return worldMin;
    }

    public Point2D getWorldMax()
    {
        return worldMax;
    }

    public Point2D getWorldStartPos()
    {
        return worldStartPos;
    }

    public double getViewStartWidth()
    {
        return viewStartWidth;
    }

    public double getViewMinWidth()
    {
        return viewMinWidth;
    }

    public double getViewMaxWidth()
    {
        return viewMaxWidth;
    }
}
