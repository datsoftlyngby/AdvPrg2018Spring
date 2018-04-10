/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typehierarchy.impl;

/**
 *
 * @author Tobias
 */
public class Test
{
    public static void main(String[] args)
    {
        String testString =
                "Item\n\n" +
                "Armour  extends  Item, Graphics\n" +
                "Potion   extends    Item\n" +
                "Weapon   extends    Item\n";
        
        TypeHierarchyGeneratorImpl test = new TypeHierarchyGeneratorImpl(new TypeParserImpl());
        test.generateTypeHierarchy(testString, null);
    }
}
