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
public interface ParsedType
{
    public String getName();
    public List<String> getSuperTypes();
}
