/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.tobiasgrundtvig.util.socket;

/**
 *
 * @author Tobias
 */
public interface ConnectionHandler
{
    //This method must return fast, if work is done, use seperate thread.
    public void handleConnection(SocketConnection conn);
}
