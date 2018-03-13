package app2dapitests;

import app2dapi.App2D;
import app2dapi.Device;
import app2dapi.Platform;
import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.geometry.G2D.PolygonBuilder;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.mouse.MouseListener;
import app2dapi.input.mouse.MouseMovedEvent;
import app2dapi.input.mouse.MousePressedEvent;
import app2dapi.input.mouse.MouseReleasedEvent;
import app2dapi.input.mouse.MouseWheelEvent;
import app2dpcimpl.PCPlatformImpl;

/**
 *
 * @author tog
 */
public class App1 implements App2D, MouseListener
{
    private Polygon p;
    private ColorFactory cf;
    private Polygon arrow;
    private G2D geo;
    private double startTime;
    private double test;
    private double test2;
    
    @Override
    public boolean initialize(Device device)
    {
        device.getMouse().addMouseListener(this);
        this.geo = device.getGeometry2D();
        PolygonBuilder bld = geo.getPolygonBuilder();
        bld.addPoint(geo.newPoint2D(10, 10));
        bld.addPoint(geo.newPoint2D(110, 10));
        bld.addPoint(geo.newPoint2D(60, 60));
        p = bld.buildPolygon();
        arrow = null;//geo.createArrow(geo.newPoint2D(60, 60), geo.newPoint2D(400, 200), 10);
        cf = device.getScreen().getColorFactory();
        startTime = 0.0;
        return true;
    }

    @Override
    public boolean update(double time)
    {
        double period = 4.0;
        double periodProgress = periodProgress(time, period);
        if(periodProgress < 0.5*period)
        {
            double f = (periodProgress / period) * 2.0;
            test = f; //acc(f);
            test2 = acc(f);
        }
        else
        {
            double f = ((period - periodProgress) / period) * 2.0;
            test = f; //acc(f);
            test2 = acc(f);
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas)
    {
        //Transformation2D t = geo.translate(100, 50);
        //canvas.setTransformation(t);
        canvas.clear(cf.getWhite());
        canvas.setColor(cf.getBlack());
        
        //Linear motion test
        canvas.drawPoint(geo.newPoint2D((float)(test*200+100), 100), 10);
        canvas.drawPoint(geo.newPoint2D(100, 120), 5);
        canvas.drawPoint(geo.newPoint2D(200, 120), 5);
        canvas.drawPoint(geo.newPoint2D(300, 120), 5);
        
        //Constant acceleration motion test
        canvas.drawPoint(geo.newPoint2D((float)(test2*200+100), 150), 10);
        canvas.drawPoint(geo.newPoint2D(100, 170), 5);
        canvas.drawPoint(geo.newPoint2D(200, 170), 5);
        canvas.drawPoint(geo.newPoint2D(300, 170), 5);
        
        canvas.drawFilledPolygon(p);
        if(arrow != null)
        {
            canvas.setColor(cf.getGreen());
            canvas.drawFilledPolygon(arrow);
        }
        canvas.setColor(cf.newColor(1, 0, 0, 0.5f));
        canvas.drawPoint(geo.newPoint2D(400,400), 20);
    }

    @Override
    public void destroy()
    {
        //Do nothing
    }

    @Override
    public void onMouseMoved(MouseMovedEvent e)
    {
        arrow = geo.createArrow(geo.newPoint2D(60, 60), geo.newPoint2D(e.getX(), e.getY()), 10);
    }

    @Override
    public void onMousePressed(MousePressedEvent e)
    {
        
    }

    @Override
    public void onMouseReleased(MouseReleasedEvent e)
    {
        
    }

    @Override
    public void onMouseWheel(MouseWheelEvent e)
    {
        
    }

    @Override
    public boolean showMouseCursor()
    {
        return false;
    }
    
    private static double acc(double t)
    {
        if(t < 0.5)
        {
            return 2.0 * t * t;
        }
        t = t - 0.5;
        return 0.5 + 2 * t *( 1 - t );
    }
    
    private static double periodProgress(double time, double period)
    {
        double progress = time / period;
        double holePeriods =  Math.floor(progress);
        return time - holePeriods * period;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Platform p = new PCPlatformImpl();
        App2D app = new App1();
        p.runApplication(app);
    }
    
}
