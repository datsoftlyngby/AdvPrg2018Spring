/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.input;

import app2dapi.input.InputEvent;
import app2dapi.input.charinput.CharInput;
import app2dapi.input.charinput.CharInputListener;
import app2dapi.input.keyboard.Key;
import app2dapi.input.keyboard.Keyboard;
import app2dapi.input.keyboard.KeyboardListener;
import app2dapi.input.mouse.Mouse;
import app2dapi.input.mouse.MouseButton;
import app2dapi.input.mouse.MouseListener;
import app2dpcimpl.input.charinput.CharInputEventImpl;
import app2dpcimpl.input.keyboard.KeyMap;
import app2dpcimpl.input.keyboard.KeyPressedEventImpl;
import app2dpcimpl.input.keyboard.KeyReleasedEventImpl;
import app2dpcimpl.input.mouse.MouseButtonMap;
import app2dpcimpl.input.mouse.MouseMovedEventImpl;
import app2dpcimpl.input.mouse.MousePressedEventImpl;
import app2dpcimpl.input.mouse.MouseReleasedEventImpl;
import app2dpcimpl.input.mouse.MouseWheelEventImpl;
import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tog
 */
public class EventHandlerImpl implements AWTEventListener, Keyboard, Mouse, CharInput
{
    private final List<KeyboardListener> keyboardListeners;
    private final List<MouseListener> mouseListeners;
    private final List<CharInputListener> charListeners;
    private final List<InputEvent> inputEvents;
    private long startTime;
    private boolean quit;
    private int halfScreenSizeX;
    private int halfScreenSizeY;

    public EventHandlerImpl()
    {
        keyboardListeners = new ArrayList<>();
        mouseListeners = new ArrayList<>();
        charListeners = new ArrayList<>();
        inputEvents = new ArrayList<>();
        this.startTime = 0;
        this.quit = false;
    }
    
    public void setScreenSize(int screenSizeX, int screenSizeY)
    {
        this.halfScreenSizeX = screenSizeX / 2;
        this.halfScreenSizeY = screenSizeY / 2;
    }
    
    public long getMask()
    {
        return  AWTEvent.KEY_EVENT_MASK +
                AWTEvent.MOUSE_EVENT_MASK +
                AWTEvent.MOUSE_MOTION_EVENT_MASK +
                AWTEvent.MOUSE_WHEEL_EVENT_MASK;
    }
    
    public synchronized void start(long startTime)
    {
        this.startTime = startTime;
        inputEvents.clear();
    }
    
    public synchronized boolean dispatch()
    {
        for(InputEvent e : inputEvents)
        {
            switch(e.getType())
            {
                case KEY_PRESSED_EVENT:
                    for(KeyboardListener l : keyboardListeners)
                    {
                        l.onKeyPressed(e.asKeyPressedEvent());
                    }
                    break;
                case KEY_RELEASED_EVENT:
                    for(KeyboardListener l : keyboardListeners)
                    {
                        l.onKeyReleased(e.asKeyReleasedEvent());
                    }
                    break;
                case CHAR_TYPED_EVENT:
                    for(CharInputListener l : charListeners)
                    {
                        l.onCharInput(e.asCharInputEvent());
                    }
                    break;
                case MOUSE_PRESSED_EVENT:
                    for(MouseListener l : mouseListeners)
                    {
                        l.onMousePressed(e.asMousePressedEvent());
                    }
                    break;
                case MOUSE_RELEASED_EVENT:
                    for(MouseListener l : mouseListeners)
                    {
                        l.onMouseReleased(e.asMouseReleasedEvent());
                    }
                    break;
                case MOUSE_MOVED_EVENT:
                    for(MouseListener l : mouseListeners)
                    {
                        l.onMouseMoved(e.asMouseMovedEvent());
                    }
                    break;
                case MOUSE_WHEEL_EVENT:
                    for(MouseListener l : mouseListeners)
                    {
                        l.onMouseWheel(e.asMouseWheelEvent());
                    }
                    break;
                default:
                    throw new RuntimeException("Unknown event type: " + e.getType().toString());
            }
        }
        inputEvents.clear();
        return quit;
    }
    
    @Override
    public synchronized void eventDispatched(AWTEvent event)
    {
        switch(event.getID())
        {
            case MouseEvent.MOUSE_PRESSED:
            {
                MouseEvent me = (MouseEvent) event;
                MouseButton b = MouseButtonMap.getMouseButton(me.getButton());
                MousePressedEventImpl e =
                    new MousePressedEventImpl(  localTime(me.getWhen()),
                                                me.getX()-halfScreenSizeX,
                                                -me.getY()+halfScreenSizeY,
                                                b );
                inputEvents.add(e);
                break;
            }
            case MouseEvent.MOUSE_RELEASED:
            {
                MouseEvent me = (MouseEvent) event;
                MouseButton b = MouseButtonMap.getMouseButton(me.getButton());
                MouseReleasedEventImpl e =
                    new MouseReleasedEventImpl( localTime(me.getWhen()),
                                                me.getX()-halfScreenSizeX,
                                                -me.getY()+halfScreenSizeY,
                                                b );
                inputEvents.add(e);
                break;
            }
            case MouseEvent.MOUSE_DRAGGED:
            case MouseEvent.MOUSE_MOVED:
            {
                MouseEvent me = (MouseEvent) event;
                MouseMovedEventImpl e =
                    new MouseMovedEventImpl( localTime(me.getWhen()),
                                             me.getX()-halfScreenSizeX,
                                             -me.getY()+halfScreenSizeY);
                inputEvents.add(e);
                break;
            }
            case MouseEvent.MOUSE_WHEEL:
            {
                MouseWheelEvent mwe = (MouseWheelEvent) event;
                MouseWheelEventImpl e = 
                        new MouseWheelEventImpl(localTime(mwe.getWhen()),
                                                mwe.getX()-halfScreenSizeX,
                                                -mwe.getY()+halfScreenSizeY,
                                                mwe.getWheelRotation());
                inputEvents.add(e);
                break;
            }
            case KeyEvent.KEY_PRESSED:
            {
                KeyEvent ke = (KeyEvent) event;
                Key k = KeyMap.getKey(ke);
                if(k == Key.VK_ESC) quit = true;
                KeyPressedEventImpl e = new KeyPressedEventImpl(localTime(ke.getWhen()), k);
                inputEvents.add(e);
                break;
            }
            case KeyEvent.KEY_RELEASED:
            {
                KeyEvent ke = (KeyEvent) event;
                Key k = KeyMap.getKey(ke);
                KeyReleasedEventImpl e = new KeyReleasedEventImpl(localTime(ke.getWhen()), k);
                inputEvents.add(e);
                break;
            }
            case KeyEvent.KEY_TYPED:
            {
                KeyEvent ke = (KeyEvent) event;
                char ch = ke.getKeyChar();
                CharInputEventImpl e = new CharInputEventImpl(localTime(ke.getWhen()), ch);
                inputEvents.add(e);
                break;
            }
            default:
                //throw new RuntimeException("Unknown event type: " + event.getID());
        }
    }

    @Override
    public void addKeyboardListener(KeyboardListener keyListener)
    {
        if(keyListener == null) throw new NullPointerException();
        keyboardListeners.add(keyListener);
    }

    @Override
    public void removeKeyboardListener(KeyboardListener keyListener)
    {
        keyboardListeners.remove(keyListener);
    }

    @Override
    public void addMouseListener(MouseListener listener)
    {
        if(listener == null) throw new NullPointerException();
        mouseListeners.add(listener);
    }

    @Override
    public void removeMouseListener(MouseListener listener)
    {
       mouseListeners.remove(listener);
    }

    @Override
    public void addCharInputListener(CharInputListener listener)
    {
        if(listener == null) throw new NullPointerException();
        charListeners.add(listener);
    }

    @Override
    public void removeCharInputListener(CharInputListener listener)
    {
        charListeners.remove(listener);
    }
    
    private double localTime(long time)
    {
        return (time-startTime) * 0.001;
    }
    
}
