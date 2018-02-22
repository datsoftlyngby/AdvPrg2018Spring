/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractwebserver;

/**
 *
 * @author Tobias
 */
public interface Webserver
{
    public void init(WebTool wt);
    public Response handleRequest(Request request);
}
