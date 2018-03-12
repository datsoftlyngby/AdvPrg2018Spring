/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.graphics;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Path;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dpcimpl.geometry.PolygonImpl;
import app2dpcimpl.geometry.Transformation2DImpl;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author tog
 */
public class CanvasImpl implements Canvas
{

    private static final AffineTransform ID = new AffineTransform(1, 0, 0, 1, 0, 0);
    private AffineTransform toScreen;
    private Font font;
    private float fontHeight;
    private float fontWidth;
    private float fontBase;
    private final Polygon unitCircle;
    private final Polygon kiloSquare;
    private Graphics2D g;
    private final Rectangle bounds;
    private final ColorFactory colorFactory;
    private final G2D g2d;
    private ColorImpl curColor;
    private Transformation2DImpl curTrans;

    public CanvasImpl(Rectangle bounds, ColorFactory colorFactory, G2D g2d)
    {
        this.bounds = bounds;
        this.colorFactory = colorFactory;
        this.g2d = g2d;
        unitCircle = g2d.createCircle(g2d.origo(), 1, 16);
        kiloSquare = g2d.createRectangle(g2d.origo(), 1000, 1000);
    }

    public void setToScreen(AffineTransform toScreen)
    {
        this.toScreen = toScreen;
    }

    public void setGraphics(Graphics2D g)
    {
        this.g = g;
        this.curColor = (ColorImpl) colorFactory.getBlack();
        this.g.setColor(curColor.getAWTColor());
        this.curTrans = (Transformation2DImpl) g2d.identity();
        g.setTransform(toScreen);

        font = new Font("Consolas", Font.PLAIN, 12);
        FontRenderContext frc = g.getFontRenderContext();
        Rectangle2D textBound = font.
          getStringBounds("0123456789ABCDEFGHIJ", frc);
        fontWidth = ((float) (textBound.getMaxX() - textBound.getMinX())) / 20.0f;
        fontHeight = (float) (textBound.getMaxY() - textBound.getMinY());
        fontBase = (float) -textBound.getMaxY();
        g.setFont(font);
    }

    @Override
    public void clear(Color c)
    {
        AffineTransform cur = g.getTransform();
        g.setTransform(ID);
        g.setColor(((ColorImpl) c).getAWTColor());
        g.fillRect(0, 0, bounds.width, bounds.height);
        g.setColor(curColor.getAWTColor());
        g.setTransform(cur);
    }

    @Override
    public void setColor(Color c)
    {
        curColor = (ColorImpl) c;
        g.setColor(curColor.getAWTColor());
    }

    @Override
    public Color getColor()
    {
        return curColor;
    }
    
     @Override
    public void setLineWidth(float width)
    {
        g.setStroke(new BasicStroke(width));
    }

    @Override
    public void setTransformation(Transformation2D t)
    {
        curTrans = (Transformation2DImpl) t;
        AffineTransform tmp = new AffineTransform(toScreen);
        tmp.concatenate(curTrans.getJavaTransform());
        g.setTransform(tmp);
    }

    @Override
    public Transformation2D getTransformation()
    {
        return curTrans;
    }

    @Override
    public void drawLine(Point2D p1, Point2D p2)
    {
        //Temporary implementation
        p1 = curTrans.transform(p1);
        p2 = curTrans.transform(p2);
        AffineTransform cur = g.getTransform();
        g.setTransform(toScreen);
        g.drawLine((int) p1.x(), (int) p1.y(), (int) p2.x(), (int) p2.y());
        g.setTransform(cur);
    }

    @Override
    public void drawFilledRectangle(Point2D boundA, Point2D boundB)
    {
        double width = Math.abs(boundB.x() - boundA.x());
        double height = Math.abs(boundB.y() - boundA.y());
        double mx = (boundA.x() + boundB.x()) * 0.5;
        double my = (boundA.y() + boundB.y()) * 0.5;

        drawFilledRectangle(g2d.newPoint2D(mx, my), width, height);
    }

    @Override
    public void drawOutlinedRectangle(Point2D boundA, Point2D boundB)
    {
        double width = Math.abs(boundB.x() - boundA.x());
        double height = Math.abs(boundB.y() - boundA.y());
        double mx = (boundA.x() + boundB.x()) * 0.5;
        double my = (boundA.y() + boundB.y()) * 0.5;

        drawOutlinedRectangle(g2d.newPoint2D(mx, my), width, height);
    }

    @Override
    public void drawFilledRectangle(Point2D center, double width, double height)
    {
        if(width > 0 && height > 0)
        {
            AffineTransform t = new AffineTransform(width * 0.001, 0, 0, height * 0.001, center.x(), center.y());
            AffineTransform cur = g.getTransform();
            AffineTransform res = new AffineTransform(cur);
            res.concatenate(t);
            g.setTransform(res);
            g.fill(((PolygonImpl) kiloSquare).getShape());
            g.setTransform(cur);
        }
    }

    @Override
    public void drawOutlinedRectangle(Point2D center, double width, double height)
    {
        if(width > 0 && height > 0)
        {
            AffineTransform t = new AffineTransform(width * 0.001, 0, 0, height * 0.001, center.x(), center.y());
            AffineTransform cur = g.getTransform();
            AffineTransform res = new AffineTransform(cur);
            res.concatenate(t);
            g.setTransform(res);
            g.draw(((PolygonImpl) kiloSquare).getShape());
            g.setTransform(cur);
        }
    }

    @Override
    public void drawFilledPolygon(Polygon polygon)
    {
        g.fill(((PolygonImpl) polygon).getShape());
    }

    @Override
    public void drawOutlinedPolygon(Polygon polygon)
    {
        g.draw(((PolygonImpl) polygon).getShape());
    }
    
    @Override
    public void drawPath(Path path)
    {
        g.draw(((PolygonImpl) path).getShape());
    }

    @Override
    public void drawPoint(G2D.Point2D p, double size)
    {
        Transformation2D cur = curTrans;
        Transformation2D scale = g2d.scale(size, size);
        Transformation2D trans = g2d.translate(p.x(), p.y());
        Transformation2D tmp = g2d.combine(g2d.combine(cur, trans), scale);
        setTransformation(tmp);
        drawFilledPolygon(unitCircle);
        setTransformation(cur);
    }

    @Override
    public void drawText(Point2D p, String text, double height)
    {
        double scale = height / fontHeight;
        AffineTransform cur = g.getTransform();
        AffineTransform s = new AffineTransform(scale, 0, 0, -scale, p.x(), p.
                                                y());
        AffineTransform tmp = new AffineTransform(cur);
        tmp.concatenate(s);
        g.setTransform(tmp);
        g.drawString(text, 0, fontBase);
        //g.drawLine(0, 0, 40, 0);
        //g.drawLine(0,(int) (-fontBase+fontHeight), 40, (int) (-fontBase+fontHeight));
        g.setTransform(cur);
    }

    @Override
    public void drawText(Point2D p, String text, double height, boolean centerX,
                         boolean centerY)
    {
        double scale = height / fontHeight;
        AffineTransform cur = g.getTransform();
        AffineTransform s = new AffineTransform(scale, 0, 0, -scale, p.x(), p.
                                                y());
        AffineTransform tmp = new AffineTransform(cur);
        tmp.concatenate(s);
        g.setTransform(tmp);
        float x = 0;
        float y = fontBase;
        if(centerX)
        {
            x -= text.length() * 0.5f * fontWidth;
        }
        if(centerY)
        {
            y += 0.5f * fontHeight;
        }
        g.drawString(text, x, y);
        g.setTransform(cur);
    }

    @Override
    public double getTextWidth(String text, double height)
    {
        double scale = height / fontHeight;
        return scale * fontWidth * text.length();
    }

}
