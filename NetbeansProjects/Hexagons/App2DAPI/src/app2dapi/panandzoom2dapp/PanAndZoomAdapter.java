/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.panandzoom2dapp;

import app2dapi.App2D;
import app2dapi.Device;
import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Dimension2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.charinput.CharInputEvent;
import app2dapi.input.charinput.CharInputListener;
import app2dapi.input.keyboard.KeyPressedEvent;
import app2dapi.input.keyboard.KeyReleasedEvent;
import app2dapi.input.keyboard.KeyboardListener;
import app2dapi.input.mouse.MouseButton;
import app2dapi.input.mouse.MouseEvent;
import app2dapi.input.mouse.MouseListener;
import app2dapi.input.mouse.MouseMovedEvent;
import app2dapi.input.mouse.MousePressedEvent;
import app2dapi.input.mouse.MouseReleasedEvent;
import app2dapi.input.mouse.MouseWheelEvent;
import app2dapi.viewwindow.ViewWindow;
import app2dapi.viewwindow.impl.ViewWindowImpl;



/**
 *
 * @author Tobias Grundtvig
 */
public class PanAndZoomAdapter implements App2D, PanAndZoomToolKit, MouseListener, KeyboardListener, CharInputListener
{
    private final PanAndZoom2DApp app;
    private PanAndZoomInit init;
    private G2D g2d;
    private ColorFactory cf;
    private ViewWindow view;
    private Transformation2D fromHUDToScreen;
    private Transformation2D fromScreenToHUD;
    private double zoom;
    private double zoomSmoothing;
    private Point2D mouseScreenPos;
    private Point2D mouseHUDPos;
    private Point2D mouseWorldPos;
    private boolean drag;
    

    public PanAndZoomAdapter(PanAndZoom2DApp app)
    {
        this.app = app;
        drag = false;
    }
    
    @Override
    public boolean initialize(Device device)
    {
        this.g2d = device.getGeometry2D();
        this.cf = device.getScreen().getColorFactory();
        double screenX = device.getScreen().getPixelWidth();
        double screenY = device.getScreen().getPixelHeight();
        double aspectRatio = screenX / screenY;
        init = app.initialize(this, aspectRatio);
        Dimension2D sizeHUD = g2d.newDimension2D(init.getHUDMax().x() - init.getHUDMin().x(),
                                                 init.getHUDMax().y() - init.getHUDMin().y());
        Point2D centerHUD = g2d.center(init.getHUDMin(), init.getHUDMax());
        double scale = screenX / sizeHUD.width();
        this.fromHUDToScreen = g2d.combine(g2d.scale(scale, scale), g2d.translatePointToOrigo(centerHUD));
        this.fromScreenToHUD = g2d.inverse(fromHUDToScreen);
        view = new ViewWindowImpl(g2d, init.getHUDMin(), init.getHUDMax(), init.getViewStartWidth(), init.getWorldStartPos());
        
        zoom = init.getViewStartWidth();
        zoomSmoothing = zoom;
        
        device.getMouse().addMouseListener(this);
        device.getKeyboard().addKeyboardListener(this);
        device.getCharInput().addCharInputListener(this);
        return true;
    }
      
    @Override
    public boolean showMouseCursor()
    {
        return app.showMouseCursor();
    }

    @Override
    public boolean update(double time)
    {
        double dist = zoom - zoomSmoothing;
        zoomSmoothing += dist * 0.1f;
        view.setWindowWidth(zoomSmoothing);
        return app.update(time);
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.clear(app.getBackgroundColor());
        canvas.setTransformation(g2d.combine(fromHUDToScreen, view.getWorldToHUD()));
        app.drawWorld(canvas);
        canvas.setTransformation(fromHUDToScreen);
        app.drawHUD(canvas);
    }

    @Override
    public void destroy()
    {
        app.destroy();
    }

    @Override
    public G2D g2d()
    {
        return g2d;
    }

    @Override
    public ColorFactory cf()
    {
        return cf;
    }

    @Override
    public void onMouseMoved(MouseMovedEvent e)
    {
        updateMousePositions(e);
        if(drag)
        {  
            view.setHUDMatchPoint(mouseHUDPos);
        }
        app.onMouseMoved(mouseHUDPos, mouseWorldPos);
    }

    @Override
    public void onMousePressed(MousePressedEvent e)
    {
        updateMousePositions(e);
        if(e.getButton() == MouseButton.RIGHT)
        {
            if(inWorld(mouseWorldPos))
            {
                drag = true;    
                view.setWorldMatchPoint(mouseWorldPos);
                view.setHUDMatchPoint(mouseHUDPos);
            }
        }
        else if(e.getButton() == MouseButton.LEFT)
        {
            app.onMousePressed(mouseHUDPos, mouseWorldPos);
        }
    }

    @Override
    public void onMouseReleased(MouseReleasedEvent e)
    {
        updateMousePositions(e);
        if(e.getButton() == MouseButton.RIGHT)
        {
            drag = false;
        }
        else if(e.getButton() == MouseButton.LEFT)
        {
            app.onMouseReleased(mouseHUDPos, mouseWorldPos);
        }
    }

    @Override
    public void onMouseWheel(MouseWheelEvent e)
    {
        updateMousePositions(e);
        view.setWorldMatchPoint(mouseWorldPos);
        view.setHUDMatchPoint(mouseHUDPos);
        int clicks = e.getNumberOfClicks();
        if(clicks > 0)
        {
            for(int i = 0; i < clicks; ++i)
            {
                zoom *= 1.1f;
                if(zoom > init.getViewMaxWidth())
                {
                    zoom = init.getViewMaxWidth();
                }
            }
        }
        else
        {
            clicks = -clicks;
            for(int i = 0; i < clicks; ++i)
            {
                zoom /= 1.1f;
                if(zoom < init.getViewMinWidth())
                {
                    zoom = init.getViewMinWidth();
                }
            }
        }
    }

    @Override
    public void onKeyPressed(KeyPressedEvent e)
    {
        app.onKeyPressed(e);
    }

    @Override
    public void onKeyReleased(KeyReleasedEvent e)
    {
        app.onKeyReleased(e);
    }

    @Override
    public void onCharInput(CharInputEvent event)
    {
        app.onCharInput(event);
    }
    
    private void updateMousePositions(MouseEvent e)
    {
        mouseScreenPos = g2d.newPoint2D(e.getX(), e.getY());
        mouseHUDPos = fromScreenToHUD.transform(mouseScreenPos);
        mouseWorldPos = view.fromHUDToWorld(mouseHUDPos);
    }

    private boolean inWorld(Point2D mouseWorldPos)
    {
        return mouseWorldPos.x() >= init.getWorldMin().x() && mouseWorldPos.y() >= init.getWorldMin().y() &&
               mouseWorldPos.x() <= init.getWorldMax().x() && mouseWorldPos.y() <= init.getWorldMax().y();
    }
    
}
