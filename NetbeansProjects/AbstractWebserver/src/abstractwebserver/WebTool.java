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
public interface WebTool
{
    public Response createResponse(String str);
    public Parameter createParameter(String name, String value);
    public Request createRequest(String url, Parameter... ps);
}
