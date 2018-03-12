/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuildervisualize;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.charinput.CharInputEvent;
import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomInit;
import app2dapi.panandzoom2dapp.PanAndZoomToolKit;
import hexcore.HexCoordinate;
import hexcore.HexCoordinateSystem;
import hexcore.HexCoordinateSystem.ColumnRowType;
import hexcore.impl.HexCoordinateSystemImpl;
import trackbuilder.TrackTile;
import trackbuilder.TrackTileMap;
import trackbuilder.TrackTileMap.TileType;
import static trackbuilder.TrackTileMap.TileType.CROSS;
import static trackbuilder.TrackTileMap.TileType.CURVE;
import static trackbuilder.TrackTileMap.TileType.ENDSTOP;
import static trackbuilder.TrackTileMap.TileType.STRAIGHT;
import static trackbuilder.TrackTileMap.TileType.SWITCH_LEFT;
import static trackbuilder.TrackTileMap.TileType.SWITCH_RIGHT;
import trackbuilder.impl.TrackTileMapImpl;

/**
 *
 * @author Tobias
 */
public class TrackBuilderVisualize implements PanAndZoom2DApp
{

    private final int sizeX;
    private final int sizeY;
    private final HexCoordinateSystem hexMap;
    private final TrackTileMap tileMap;
    private final float trackWidth;
    private int lastDir;
    private TileDrawer tileDrawer;
    private G2D g2d;
    private ColorFactory cf;
    private Polygon hexPolygon;
    private Polygon hooverPolygon;
    private Polygon selectPolygon;
    private HexCoordinate hoover;
    private HexCoordinate selected;
    private HexCoordinate pressed;

    public TrackBuilderVisualize(int sizeX, int sizeY, ColumnRowType crtype, float radius, float trackWidth)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.trackWidth = trackWidth;
        hexMap = new HexCoordinateSystemImpl(crtype, radius);
        tileMap = new TrackTileMapImpl(hexMap);
        this.hoover = hexMap.getFromAxial(0, 0, 0);
        lastDir = 0;
    }

    @Override
    public PanAndZoomInit initialize(PanAndZoomToolKit tk, double aspectRatio)
    {
        g2d = tk.g2d();
        cf = tk.cf();

        HexCoordinate origin = hexMap.getFromAxial(0, 0, 0);
        Point2D[] hexPoints = new Point2D[6];
        Point2D[] hooverPoints = new Point2D[6];
        Point2D[] selectPoints = new Point2D[6];
        for (int i = 0; i < 6; ++i)
        {
            float x = origin.getCornerXPos(i);
            float y = origin.getCornerYPos(i);
            hexPoints[i] = g2d.newPoint2D(x * 0.95, y * 0.95);
            selectPoints[i] = g2d.newPoint2D(x * 0.9, y * 0.9);
            hooverPoints[i] = g2d.newPoint2D(x, y);
        }
        hexPolygon = g2d.createPolygon(hexPoints);
        selectPolygon = g2d.createPolygon(selectPoints);
        hooverPolygon = g2d.createPolygon(hooverPoints);

        //Test...
        tileDrawer = new TileDrawer(g2d, cf, hexMap, trackWidth);
        TrackTile straightTile = tileMap.createTrackTile(STRAIGHT);
        TrackTile curveTile = tileMap.createTrackTile(CURVE);
        TrackTile switchLeft = tileMap.createTrackTile(SWITCH_LEFT);
        TrackTile switchRight = tileMap.createTrackTile(SWITCH_RIGHT);
        TrackTile cross = tileMap.createTrackTile(CROSS);
        TrackTile endStop = tileMap.createTrackTile(ENDSTOP);
        straightTile.setHexPosition(hexMap.getFromColumnAndRow(3, 5));
        curveTile.setHexPosition(hexMap.getFromColumnAndRow(6, 5));
        switchLeft.setHexPosition(hexMap.getFromColumnAndRow(9, 5));
        switchRight.setHexPosition(hexMap.getFromColumnAndRow(12, 5));
        cross.setHexPosition(hexMap.getFromColumnAndRow(15, 5));
        endStop.setHexPosition(hexMap.getFromColumnAndRow(18, 5));
        //curveTile.turnClockwise();

        Point2D hudMin = g2d.newPoint2D(0, 0);
        Point2D hudMax = g2d.newPoint2D(100, 100 / aspectRatio);
        Point2D worldMin = g2d.newPoint2D(0, 0);
        Point2D worldMax = g2d.newPoint2D(sizeX * hexMap.getHexRadius() * 2, sizeY * hexMap.getHexRadius() * 2);
        Point2D worldStartPos = g2d.newPoint2D(sizeX * hexMap.getHexRadius(), sizeY * hexMap.getHexRadius());
        double maxWidth = Math.max(hexMap.getHexRadius() * 2 * sizeX, hexMap.getHexRadius() * 2 * sizeY * aspectRatio);
        return new PanAndZoomInit(hudMin, hudMax, worldMin, worldMax, worldStartPos, hexMap.getHexRadius() * 10, hexMap.getHexRadius() * 2, maxWidth);

        /*
        return new PanAndZoomInit(  g2d.origo(),
                                    g2d.newPoint2D(1000.0, 1000.0 / aspectRatio),
                                    g2d.origo(),
                                    g2d.newPoint2D(sizeX*100, sizeY*100),
                                    g2d.newPoint2D(sizeX*50, sizeY*50),
                                    Math.max(sizeX*100, sizeY*100*aspectRatio),
                                    100.0,
                                    Math.max(sizeX*100, sizeY*100*aspectRatio));
         */
    }

    @Override
    public boolean showMouseCursor()
    {
        return true;
    }

    @Override
    public void onMouseMoved(Point2D mouseHUDPos, Point2D mouseWorldPos)
    {
        hoover = hexMap.getFromPosition((float) mouseWorldPos.x(), (float) mouseWorldPos.y());
        int col = hoover.getColumn();
        int row = hoover.getRow();
        if (col < 0 || col >= sizeX || row < 0 || row >= sizeY)
        {
            hoover = null;
        }
    }

    @Override
    public void onMousePressed(Point2D mouseHUDPos, Point2D mouseWorldPos)
    {
        pressed = hexMap.getFromPosition((float) mouseWorldPos.x(), (float) mouseWorldPos.y());
        int col = pressed.getColumn();
        int row = pressed.getRow();
        if (col < 0 || col >= sizeX || row < 0 || row >= sizeY)
        {
            pressed = null;
        }
    }

    @Override
    public void onMouseReleased(Point2D mouseHUDPos, Point2D mouseWorldPos)
    {
        if (pressed != null)
        {
            HexCoordinate hex = hexMap.getFromPosition((float) mouseWorldPos.x(), (float) mouseWorldPos.y());
            if (hex.equals(pressed))
            {
                onHexClicked(hex);
            }
        }
    }

    private void onHexClicked(HexCoordinate hex)
    {
        if (hex.equals(selected))
        {
            TrackTile tile = tileMap.getTrackTile(selected);
            if (tile != null)
            {
                tile.turnClockwise();
            }
        } else
        {
            selected = hex;
        }
    }

    @Override
    public void onKeyPressed(KeyPressedEvent e)
    {
        switch (e.getKey())
        {
            case VK_D:
                tileDrawer.toggleDebugView();
                break;
            case VK_0:
                if (selected != null)
                {
                    TrackTile oldTile = tileMap.getTrackTile(selected);
                    if (oldTile != null)
                    {
                        oldTile.setHexPosition(null);
                    }
                }
                break;
            case VK_1:
                setNewTile(STRAIGHT);
                break;
            case VK_2:
                setNewTile(CURVE);
                break;
            case VK_3:
                setNewTile(SWITCH_LEFT);
                break;
            case VK_4:
                setNewTile(SWITCH_RIGHT);
                break;
            case VK_5:
                setNewTile(CROSS);
                break;
            case VK_6:
                setNewTile(ENDSTOP);
                break;
            case VK_Q:
                turnTileCCW();
                break;
            case VK_W:
                turnTileCW();
                break;

        }

    }

    private void setNewTile(TileType tileType)
    {
        if (hoover != null)
        {
            TrackTile oldTile = tileMap.getTrackTile(hoover);
            int dir = lastDir;
            if (oldTile != null)
            {
                dir = oldTile.getDirection();
            }
            TrackTile newTile = tileMap.createTrackTile(tileType);
            newTile.setDirection(dir);
            newTile.setHexPosition(hoover);
        }
    }
    
    private void turnTileCW()
    {
        if (hoover != null)
        {
            TrackTile tile = tileMap.getTrackTile(hoover);
            if (tile != null)
            {
                tile.turnClockwise();
                lastDir = tile.getDirection();
            }
        }
    }
    
    private void turnTileCCW()
    {
        if (hoover != null)
        {
            TrackTile tile = tileMap.getTrackTile(hoover);
            if (tile != null)
            {
                tile.turnCounterClockwise();
                lastDir = tile.getDirection();
            }
        }
    }

    @Override
    public void onKeyReleased(KeyReleasedEvent e)
    {

    }

    @Override
    public void onCharInput(CharInputEvent event)
    {

    }

    @Override
    public boolean update(double time)
    {
        return true;
    }

    @Override
    public Color getBackgroundColor()
    {
        return cf.getWhite();
    }

    @Override
    public void drawWorld(Canvas canvas)
    {
        canvas.setLineWidth(1);
        canvas.setColor(cf.newColor(0.9f, 0.9f, 0.9f));
        for (int col = 0; col < sizeX; ++col)
        {
            for (int row = 0; row < sizeY; ++row)
            {
                HexCoordinate hex = hexMap.getFromColumnAndRow(col, row);
                Transformation2D orig = canvas.getTransformation();
                Transformation2D res = g2d.combine(orig, g2d.translate(hex.getCenterXPos(), hex.getCenterYPos()));
                canvas.setTransformation(res);
                canvas.drawOutlinedPolygon(hexPolygon);
                canvas.setTransformation(orig);
            }
        }
        if (hoover != null)
        {
            canvas.setLineWidth(2);
            canvas.setColor(cf.getGreen());
            Transformation2D orig = canvas.getTransformation();
            Transformation2D res = g2d.combine(orig, g2d.translate(hoover.getCenterXPos(), hoover.getCenterYPos()));
            canvas.setTransformation(res);
            canvas.drawOutlinedPolygon(hooverPolygon);
            if (selected != null)
            {
                int dist = hexMap.getDist(selected, hoover);
                canvas.drawText(g2d.origo(), "Distance: " + dist, 10, true, true);
            }
            canvas.setTransformation(orig);
        }
        if (selected != null)
        {
            canvas.setLineWidth(2);
            canvas.setColor(cf.getBlue());
            Transformation2D orig = canvas.getTransformation();
            Transformation2D res = g2d.combine(orig, g2d.translate(selected.getCenterXPos(), selected.getCenterYPos()));
            canvas.setTransformation(res);
            canvas.drawOutlinedPolygon(selectPolygon);
            canvas.setTransformation(orig);
        }
        for (TrackTile tile : tileMap)
        {
            tileDrawer.drawTile(canvas, tile, tile.getHexPosition().getCenterXPos(), tile.getHexPosition().getCenterYPos());
        }
    }

    @Override
    public void drawHUD(Canvas canvas)
    {

    }

    @Override
    public void destroy()
    {

    }

}
