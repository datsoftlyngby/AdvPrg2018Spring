/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuilder;

/**
 *
 * @author Tobias
 */
public interface SwitchTile extends TrackTile
{
    public enum SwitchState {STRAIGHT, TURN}
    public SwitchState getState();
    public void setState(SwitchState state);
    public void toggleState();
}
