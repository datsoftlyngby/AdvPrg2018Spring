/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dapi.input.mouse;

/**
 *
 * @author tog
 */
public interface MouseListener
{
    public void onMouseMoved(MouseMovedEvent e);
    public void onMousePressed(MousePressedEvent e);
    public void onMouseReleased(MouseReleasedEvent e);
    public void onMouseWheel(MouseWheelEvent e);
}
