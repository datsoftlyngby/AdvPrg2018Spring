/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typehierarchy.impl;

import java.util.List;
import typehierarchy.TypeFile;
import typehierarchy.TypeFileGenerator;
import typehierarchy.TypeHierarchyGenerator;
import typehierarchy.TypeParser;

/**
 *
 * @author Tobias
 */
public class TypeHierarchyGeneratorImpl implements TypeHierarchyGenerator
{
    private final TypeParser typeParser;

    public TypeHierarchyGeneratorImpl(TypeParser typeParser)
    {
        this.typeParser = typeParser;
    }
    
    @Override
    public List<TypeFile> generateTypeHierarchy(String typeHierarchyDefinition, TypeFileGenerator fileGenerator)
    {
        
        String[] lines = typeHierarchyDefinition.split("\\r?\\n");
        int i = 0;
        for(String line : lines)
        {
            line = line.trim();
            if(!line.equals("") && !line.startsWith("//"))
            {
                System.out.println("Line " + (++i) + ": " + line);
                typeParser.parse(line);
            }
            //ParsedType pt = typeParser.parse(line);
            
        }
        return null;
    }
    
}
