/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.input;

/**
 *
 * @author tog
 */
public enum InputEventType
{
    //Keyboard events
    KEY_PRESSED_EVENT,
    KEY_RELEASED_EVENT,
    
    //Type event
    CHAR_TYPED_EVENT,
    
    //Mouse events
    MOUSE_PRESSED_EVENT,
    MOUSE_RELEASED_EVENT,
    MOUSE_MOVED_EVENT,
    MOUSE_WHEEL_EVENT
    
    //More to come... (Touch, tilt, accelerometer, gps, etc...)
}
