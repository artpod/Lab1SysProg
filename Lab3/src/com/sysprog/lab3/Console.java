package com.sysprog.lab3;
import java.io.*;

public class Console {
    static public class TokenPrinter {
        static void print(Token token)
        {
            String s = token.lexeme.replaceAll("\\s", "");
            System.out.print(TokenPattern.unrecognizedLexeme.color);
            System.out.print(token.forwardInput);
            System.out.print(ConsoleColor.RESET);
            System.out.print(token.pattern.color);
            if (token.lexeme.contains("define") || token.lexeme.contains("include")) {
            System.out.print("#"+s);
            System.out.print(" (");
            System.out.print("preprocessor directive");
            System.out.print(")");
            } else
            if  (!token.lexeme.contains("#"))
            {
            System.out.print(s);
            System.out.print(" (");
            System.out.print(token.pattern.name);
            System.out.print(")");
            }
            System.out.print(ConsoleColor.RESET);
            System.out.println("");
        }

        static void print1(Token token)
        {
            System.out.print(TokenPattern.unrecognizedLexeme.color);
            System.out.print(token.forwardInput);
            System.out.print(ConsoleColor.RESET);
            System.out.print(token.pattern.color);
            System.out.print(token.lexeme);
            System.out.print(ConsoleColor.RESET);
        }

        static void printToFile(Token token)
        {
            try{
           /* String s = token.lexeme.replaceAll("\\s", "");
          //  FileWriter writer = new FileWriter("output.txt", false);
            FileOutputStream writer = new FileOutputStream("output.txt");
            writer.write(token.forwardInput);
            writer.append('\n');
           // writer.write(ConsoleColor.RESET);
           // writer.write(token.pattern.color);
            if (token.lexeme.contains("define") || token.lexeme.contains("include")) {
            writer.write("#"+s); writer.append('\n');
            writer.write(" ("); writer.append('\n');
            writer.write("preprocessor directive"); writer.append('\n');
            writer.write(")"); writer.append('\n');
            } else
            if  (!token.lexeme.contains("#"))
            {
            writer.write(s); writer.append('\n');
            writer.write(" ("); writer.append('\n');
            writer.write(token.pattern.name); writer.append('\n');
            writer.write(")"); writer.append('\n');
            }
           // writer.write(ConsoleColor.RESET);
           // writer.writeln("");


            writer.close();*/
            }
            catch (Exception ex)
            {

            }

        }
    }
}
