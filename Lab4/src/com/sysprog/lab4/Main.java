package com.sysprog.lab4;

import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    private static VirtualTokenizer readAutomatonTokenizer(Path automatonPath, Path codePath) throws FileNotFoundException, JSONException
    {
        final FileReader fr = new FileReader(automatonPath.toString());
        final JSONTokener tokener = new JSONTokener(fr);
        final JSONObject root = new JSONObject(tokener);
        final var keyWords = new HashSet<String>();

        var ep = ((JSONArray)root.get("key_words"));
        for (int i=0; i<ep.length(); i++) { // adding keyWords from json to set
            var ob = ep.get(i);
            keyWords.add(ob.toString());
        }
        
        final String startState = (String)root.get("start_sate");
        final FileCharSource codeFileSource = new FileCharSource(new FileReader(codePath.toString()));
        final AutomatonTokenizer tokenizer = new AutomatonTokenizer(codeFileSource, keyWords, startState);
            var ed = ((JSONArray)root.get("states"));
            for (int i=0; i<ed.length(); i++) {
                var ob = ed.get(i);
            final var transitionInfo = (JSONObject)ob;
            final String stateName = transitionInfo.getString("state");
            final boolean isFinalState = transitionInfo.getBoolean("is_final_state");
            if (isFinalState) {
                final String tokenName = transitionInfo.getString("name");
                final ConsoleColor color = ConsoleColor.fromString(transitionInfo.getString("color"));
                tokenizer.addState(stateName, new TokenClass(tokenName, color));
            } else {
                tokenizer.addState(stateName);
            }                                                                                               // програмування автомату та
        }                                                                                                   // додавання станів

        var ef = ((JSONArray)root.get("transitions"));                                                  //програмування переходів
        for (int i=0; i<ef.length(); i++) {
            var ob = ef.get(i);
            final var transitionInfo = (JSONObject)ob;
            final String state = transitionInfo.getString("state");
            final CharChecker checker;
            final var input = transitionInfo.get("input");
            if (input instanceof JSONArray) {
                final Set<Integer> codePointSet = new HashSet<>();
                var eg = ((JSONArray) input);
                for (int j=0; j<eg.length(); j++) {
                var codeOb = eg.get(j);
                    codePointSet.add(codeOb.toString().codePointAt(0));
                }
                checker = new SetCharChecker(codePointSet);
            }  else {
                checker = new RegexCharChecker(Pattern.compile(input.toString()));
            }
            final String moveTo = transitionInfo.getString("move_to");
            tokenizer.addTransition(state, checker, moveTo);
        }
        return tokenizer;
    }

    public static void main(String[] args)
    {
        final Path automatonPath;
        final Path filePath;
        try {
            automatonPath = FileSystems.getDefault().getPath("automaton.json").toAbsolutePath();
            filePath = FileSystems.getDefault().getPath("test.c").toAbsolutePath();
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getLocalizedMessage());
            System.exit(1);
            return;
        }
        try {
            final VirtualTokenizer tokenizer = readAutomatonTokenizer(automatonPath, filePath);
            final VirtualTokenizer tokenizer2 = readAutomatonTokenizer(automatonPath, filePath);
            PrintWriter out = new PrintWriter("output.txt");
            
            while (tokenizer.hasNext()) {
                Console.TokenPrinter.print(tokenizer.next(), out);
            }
            out.close();
            System.out.println("");
            while (tokenizer2.hasNext()) {
                Console.TokenPrinter.print1(tokenizer2.next());
            }
            File file = new File("output.txt");
            Scanner sc = new Scanner(file);

            PrintWriter cout = new PrintWriter("out.txt");

            while (sc.hasNextLine()) {
            String s = sc.nextLine().trim();
            cout.print(s);
            cout.println("");
            }
            cout.close();
            sc.close();
            file.delete();
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }
    }
}
