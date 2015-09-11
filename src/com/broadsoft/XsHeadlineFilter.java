package com.broadsoft;

import java.io.*;
import java.util.regex.*;
import java.util.*;

public class XsHeadlineFilter {

    public static void main(String[] args) throws IOException {

        Set<String> filters = new HashSet<>();
        for ( int i = 0; i < args.length; i++) {
            filters.add(args[i]);
        }

        String HEADLINE_REGEX = "\\|? (.*?)\\s+\\|";
        Pattern pattern = Pattern.compile(HEADLINE_REGEX);

        boolean filter = true;
        boolean headlineFound = false;

        // BufferedReader reader = new BufferedReader( new FileReader( args[0]) );
        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in) );

        String line;
        while ( (line = reader.readLine()) != null ) {
            Matcher matcher = pattern.matcher(line);
            Set<String> headlines = new HashSet<>();
            while ( matcher.find() ) {
                headlineFound = true;
                headlines.add( matcher.group(1) );
            }

            if ( headlineFound ) {
                headlines.retainAll(filters);
                filter = headlines.isEmpty() ? true : false;
                headlineFound = false;
            }

            if ( filter ) {
                continue;
            } else {
                System.out.println( line );
            }
        }
    }
}
