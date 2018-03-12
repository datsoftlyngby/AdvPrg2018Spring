/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dapi.graphics;

/**
 *
 * @author tog
 */
public interface Color
{
    /**
     * 
     * @return The red amount in the interval 0 to 1. 
     */
    public float r();
    /**
     * 
     * @return The green amount in the interval 0 to 1. 
     */
    public float g();
    /**
     * 
     * @return The blue amount in the interval 0 to 1. 
     */
    public float b();
    /**
     * 
     * @return The alpha amount in the interval 0 to 1. 
     */
    public float a();
    
    public int getRGBA();
}
