/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typehierarchy.impl;

import typehierarchy.ParsedType;
import typehierarchy.TypeParser;

/**
 *
 * @author Tobias
 */
public class TypeParserImpl implements TypeParser
{

    @Override
    public ParsedType parse(String s)
    {
        String[] words = s.split("\\s");
        String name = words[0].trim();
        if(words.length > 1)
        {
            if(!words[1].trim().equals("extends"))
            {
                throw new RuntimeException("extends");
            }
        }
        for(String word : words)
        {
            word = word.trim();
            if(!word.equals(""))
            {
                System.out.println("  Word: " + word);
            }
        }
        return null;
    }
    
}
