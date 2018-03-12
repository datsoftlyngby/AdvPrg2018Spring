/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation;

/**
 *
 * @author Tobias Grundtvig
 * @param <CANVAS>
 */
public interface Visual<CANVAS> extends Updatable, Drawable<CANVAS>
{
    public void start(double time);
    public boolean isFinished();
}
