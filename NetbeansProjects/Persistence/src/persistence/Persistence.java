/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.io.IOException;

/**
 *
 * @author Tobias
 */
public interface Persistence
{
    public DataOut put(long id) throws IOException;
    public DataIn get(long id) throws IOException;
}
