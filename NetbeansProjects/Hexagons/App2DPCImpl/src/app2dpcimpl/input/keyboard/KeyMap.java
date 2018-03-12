/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.input.keyboard;

import app2dapi.input.keyboard.Key;
import java.awt.event.KeyEvent;

/**
 *
 * @author tog
 */
public class KeyMap
{

    public static Key getKey(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            //Normal letters and numbers
            case KeyEvent.VK_A:
                return Key.VK_A;
            case KeyEvent.VK_B:
                return Key.VK_B;
            case KeyEvent.VK_C:
                return Key.VK_C;
            case KeyEvent.VK_D:
                return Key.VK_D;
            case KeyEvent.VK_E:
                return Key.VK_E;
            case KeyEvent.VK_F:
                return Key.VK_F;
            case KeyEvent.VK_G:
                return Key.VK_G;
            case KeyEvent.VK_H:
                return Key.VK_H;
            case KeyEvent.VK_I:
                return Key.VK_I;
            case KeyEvent.VK_J:
                return Key.VK_J;
            case KeyEvent.VK_K:
                return Key.VK_K;
            case KeyEvent.VK_L:
                return Key.VK_L;
            case KeyEvent.VK_M:
                return Key.VK_M;
            case KeyEvent.VK_N:
                return Key.VK_N;
            case KeyEvent.VK_O:
                return Key.VK_O;
            case KeyEvent.VK_P:
                return Key.VK_P;
            case KeyEvent.VK_Q:
                return Key.VK_Q;
            case KeyEvent.VK_R:
                return Key.VK_R;
            case KeyEvent.VK_S:
                return Key.VK_S;
            case KeyEvent.VK_T:
                return Key.VK_T;
            case KeyEvent.VK_U:
                return Key.VK_U;
            case KeyEvent.VK_V:
                return Key.VK_V;
            case KeyEvent.VK_W:
                return Key.VK_W;
            case KeyEvent.VK_X:
                return Key.VK_X;
            case KeyEvent.VK_Y:
                return Key.VK_Y;
            case KeyEvent.VK_Z:
                return Key.VK_Z;
            case KeyEvent.VK_0:
                return Key.VK_0;
            case KeyEvent.VK_1:
                return Key.VK_1;              
            case KeyEvent.VK_2:
                return Key.VK_2;               
            case KeyEvent.VK_3:
                return Key.VK_3;               
            case KeyEvent.VK_4:
                return Key.VK_4;              
            case KeyEvent.VK_5:
                return Key.VK_5;             
            case KeyEvent.VK_6:
                return Key.VK_6;               
            case KeyEvent.VK_7:
                return Key.VK_7;               
            case KeyEvent.VK_8:
                return Key.VK_8;              
            case KeyEvent.VK_9:
                return Key.VK_9;
                

            //Arrow keys
            case KeyEvent.VK_UP:
                return Key.VK_UP;
                
            case KeyEvent.VK_DOWN:
                return Key.VK_DOWN;
                
            case KeyEvent.VK_LEFT:
                return Key.VK_LEFT;
                
            case KeyEvent.VK_RIGHT:
                return Key.VK_RIGHT;
                

            //Special keys    
            case KeyEvent.VK_SPACE:
                return Key.VK_SPACE;
                
            case KeyEvent.VK_ENTER:
                if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_NUMPAD)
                {
                    return Key.VK_NUM_ENTER;
                } else
                {
                    return Key.VK_ENTER;
                }
                
            case KeyEvent.VK_ESCAPE:
                return Key.VK_ESC;
                
            case KeyEvent.VK_SHIFT:
                if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
                {
                    return Key.VK_LSHIFT;
                } else
                {
                    return Key.VK_RSHIFT;
                }
                
            case KeyEvent.VK_CONTROL:
                if (e.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
                {
                    return Key.VK_LCTRL;
                } else
                {
                    return Key.VK_RCTRL;
                }
                
            case KeyEvent.VK_TAB:
                return Key.VK_TAB;
                
            case KeyEvent.VK_ALT:
                return Key.VK_ALT;
                
            case KeyEvent.VK_ALT_GRAPH:
                return Key.VK_ALT_GRAPH;
                

            //Numpad numbers
            case KeyEvent.VK_NUMPAD0:
                return Key.VK_NUM_0;
                
            case KeyEvent.VK_NUMPAD1:
                return Key.VK_NUM_1;
                
            case KeyEvent.VK_NUMPAD2:
                return Key.VK_NUM_2;
                
            case KeyEvent.VK_NUMPAD3:
                return Key.VK_NUM_3;
                
            case KeyEvent.VK_NUMPAD4:
                return Key.VK_NUM_4;
                
            case KeyEvent.VK_NUMPAD5:
                return Key.VK_NUM_5;
                
            case KeyEvent.VK_NUMPAD6:
                return Key.VK_NUM_6;
                
            case KeyEvent.VK_NUMPAD7:
                return Key.VK_NUM_7;
                
            case KeyEvent.VK_NUMPAD8:
                return Key.VK_NUM_8;
                
            case KeyEvent.VK_NUMPAD9:
                return Key.VK_NUM_9;
                

            default:
                return Key.UNKNOWN;

        }
    }
}
