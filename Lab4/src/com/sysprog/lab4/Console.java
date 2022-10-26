package com.sysprog.lab4;
import java.io.PrintWriter;
/*public class Console {
    static public class TokenPrinter {
        static void print(Token token)
        {
            System.out.print(TokenClass.unrecognizedLexeme.color);
            System.out.print(token.forwardInput);
            System.out.print(ConsoleColor.RESET);
            System.out.print(token.tokenClass.color);
            System.out.print(token.lexeme);
            System.out.print(ConsoleColor.RESET);
        }
    }
}*/
public class Console {
    static public class TokenPrinter {
        static void print(Token token, PrintWriter out)
        {
            try{
            
            String s = token.lexeme.trim();//.replaceAll("\\s", "");
            System.out.print(TokenClass.unrecognizedLexeme.color);
            System.out.print(token.forwardInput); out.print(token.forwardInput);
            System.out.print(ConsoleColor.RESET);
            System.out.print(token.tokenClass.color);
            if (token.lexeme.contains("define") || token.lexeme.contains("include")) {
                System.out.print(""+s); out.print(""+s);
                System.out.print(" ("); out.print(" (");
                System.out.print("preprocessor directive"); out.print("preprocessor directive");
                System.out.print(")"); out.print(")");
            } else
                if  (!token.lexeme.contains("#"))
                {
                System.out.print(s); out.print(s);
                System.out.print(" ("); out.print(" (");
                System.out.print(token.tokenClass.name); out.print(token.tokenClass.name);
                System.out.print(")"); out.print(")");
                }
                System.out.print(ConsoleColor.RESET); 
                System.out.println(""); out.println("");
               
            }
            catch (Exception e)
            {
                System.out.print(0);
            }
            }
        

        static void print1(Token token)
        {
            System.out.print(TokenClass.unrecognizedLexeme.color);
            System.out.print(token.forwardInput);
            System.out.print(ConsoleColor.RESET);
            System.out.print(token.tokenClass.color);
            System.out.print(token.lexeme);
            System.out.print(ConsoleColor.RESET);
        }
    
    }
}
