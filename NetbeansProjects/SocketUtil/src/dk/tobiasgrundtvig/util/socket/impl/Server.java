/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.tobiasgrundtvig.util.socket.impl;

import dk.tobiasgrundtvig.util.socket.ConnectionHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Tobias
 */
public class Server implements Runnable
{
    private final int port;
    private ServerSocket serverSocket;
    private volatile boolean stop;
    private final ConnectionHandler handler;
    
    public Server(ConnectionHandler handler, int port)
    {
        this.handler = handler;
        this.port = port;
    }
    
    @Override
    public void run()
    {
        try
        {
            String adr = java.net.InetAddress.getLocalHost().getHostAddress();
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on " + adr + ":" + port);
            stop = false;
            while (!stop)
            {
                try
                {
                    //Throws a SocketException when closed();
                    Socket socket = serverSocket.accept();
                    handler.handleConnection(new SocketConnectionImpl(socket));
                } catch (SocketException e)
                {
                    // Nothing to do, this is actually not an error, 
                    // but the only way we can break out of the accept method.
                }                
            }
        } catch (IOException e)
        {
            System.out.println("Server crashed!");
            return;
        }
        System.out.println("Server stopped!");
    }
    
    public void stop()
    {
        stop = true;
        try
        {
            serverSocket.close();
        } catch (IOException ex)
        {
            //Nothing to do...
        }
    }    
}
