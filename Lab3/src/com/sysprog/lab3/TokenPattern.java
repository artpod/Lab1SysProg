package com.sysprog.lab3;

import java.util.regex.Pattern;

public class TokenPattern {
    final String name;
    final Pattern pattern;
    final ConsoleColor color;

    // hard code
    final public static TokenPattern keyWord = new TokenPattern("Key word", "", ConsoleColor.YELLOW);
    final public static TokenPattern unrecognizedLexeme = new TokenPattern("Unrecognized lexeme", "", ConsoleColor.RED);

    TokenPattern(String aName, String aPatternString, ConsoleColor aColor)
    {
        name = aName;
        pattern = Pattern.compile(aPatternString);
        color = aColor;
    }
}
