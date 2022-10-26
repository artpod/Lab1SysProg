package com.sysprog.lab3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONString;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

// build - javac -cp json-20220320.jar Main.java RegexTokenizer.java Token.java TokenPattern.java ConsoleColor.java Console.java && cd ../../.. && java com.sysprog.lab3.main

public class Main {

    private static Path getFilePath(String[] args, int idx) throws IllegalArgumentException
    {
        String arg;
        if (!(args.length == 0 || args.length == 2) || idx >= 2) {
            throw new IllegalArgumentException("");
        }

        if (args.length != 0) {
            arg = args[idx];
        } else {
            Scanner scanner = new Scanner(System.in);
            arg = scanner.nextLine();
        }

        return FileSystems.getDefault().getPath(arg).toAbsolutePath();
    }
    
    private static RegexTokenizer readRegexTokenizer(Path filePath) throws FileNotFoundException, JSONException
    {
        final RegexTokenizer tokenizer;
        FileReader fr = new FileReader(filePath.toString());   
        JSONTokener tokener = new JSONTokener(fr);
        JSONObject root = new JSONObject(tokener); //creating json object
        
        final var keyWords = new HashSet<String>();
        var ep = (JSONArray)root.get("key_words");
        for (int i=0; i<ep.length(); i++) { // adding keyWords from json to set
            var ob = ep.get(i);
            keyWords.add(ob.toString());
        }
        tokenizer = new RegexTokenizer(keyWords); 
        var ex = (JSONArray)root.get("lexemes"); 

            for (int i=0; i<ex.length(); i++) { // going through lexemes and separate properties into strings

            final var lexemeInfo = ex.getJSONObject(i);
            final String name = lexemeInfo.getString("name");
            final String regex = lexemeInfo.getString("regex");
            final ConsoleColor color = ConsoleColor.fromString(lexemeInfo.getString("color"));
            tokenizer.addPattern(new TokenPattern(name, regex, color));
        }
        return tokenizer;
    }

    public static void main(String[] args)
    {
        Path lexemesPath = null;
        Path filePath = null;
        try {
            lexemesPath = FileSystems.getDefault().getPath("lexemes.json").toAbsolutePath(); // lexemes path giving
            filePath = FileSystems.getDefault().getPath("test.c").toAbsolutePath(); // test file path giving
//            lexemesPath = getFilePath(args, 0);
//            filePath = getFilePath(args, 1);
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getLocalizedMessage());
            System.exit(1);
            return;
        }

        try {
            final RegexTokenizer tokenizer = readRegexTokenizer(lexemesPath);  // giving lexeme path to analyzer
            final String code = Files.readString(Paths.get(filePath.toString())); // parsing C code to string
            for (var token : tokenizer.getTokens(code)) { // going through the tokens and 
                Console.TokenPrinter.print(token); 
                  // printing them to the console
              
            }
            for (var token : tokenizer.getTokens(code)) {
            Console.TokenPrinter.print1(token);
            }
            for (var token : tokenizer.getTokens(code)) {
                Console.TokenPrinter.printToFile(token);
            }
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
