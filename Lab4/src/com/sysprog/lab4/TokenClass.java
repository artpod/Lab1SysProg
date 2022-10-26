package com.sysprog.lab4;

public class TokenClass {
    final String name;
    final ConsoleColor color;

    final public static TokenClass keyWord = new TokenClass("Key word", ConsoleColor.GREEN);
    final public static TokenClass unrecognizedLexeme = new TokenClass("Unrecognized lexeme", ConsoleColor.RED);

    TokenClass(String aName, ConsoleColor aColor)
    {
        name = aName;
        color = aColor;
    }
}
