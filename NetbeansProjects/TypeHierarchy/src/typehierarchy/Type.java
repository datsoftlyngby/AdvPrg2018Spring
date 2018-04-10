/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typehierarchy;

import java.util.List;

/**
 *
 * @author Tobias
 */
public interface Type
{
    public String getName();
    public List<Type> getSuperTypes();
    public List<Type> getSubTypes();
}
