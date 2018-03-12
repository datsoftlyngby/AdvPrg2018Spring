/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dapi.input.keyboard;

import app2dapi.input.InputEvent;

/**
 *
 * @author tog
 */
public interface KeyEvent extends InputEvent
{
    public Key getKey();
}
