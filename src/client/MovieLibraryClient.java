package javaserver.client;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

import javaserver.server.MovieDescription;
import javaserver.server.MovieLibrary;
import javaserver.server.MovieDescriptionHttpProxy;

/**
 * Copyright 2017 Jean Torres,
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Purpose: demonstrate simple Java Fraction class with command line,
 * jdb debugging, and Ant build file.
 *
 * Ser321 Foundations of Distributed Applications
 * see http://pooh.poly.asu.edu/Ser321
 * @author Jean Torres jctorre8@asu.edu
 * @version August 2017
 */

public class MovieLibraryClient extends Object {

	static final boolean debugOn = false;

   	private static void debug(String message) {
      	if (debugOn)
         	System.out.println("debug: "+message);
   	}

   	public static void main(String args[]) {

      	String host = "localhost";
      	String port = "8080";
      
      	try {
         	if(args.length >= 2){
            	host = args[0];
            	port = args[1];
         	}
         	String url = "http://"+host+":"+port+"/";
         	System.out.println("Opening connection to: "+url);
         	MovieLibrary sc = (MovieLibrary)new MovieDescriptionHttpProxy(new URL(url));
         	BufferedReader stdin = new BufferedReader(
            	new InputStreamReader(System.in));
         	System.out.print("Enter end or {add|get|getTitles|remove} followed by args>");
         	String inStr = stdin.readLine();
         	StringTokenizer st = new StringTokenizer(inStr);
         	String opn = st.nextToken();
         	while(!opn.equalsIgnoreCase("end")) {
            	if(opn.equalsIgnoreCase("add")){
               		String name = "";
               		while(st.hasMoreTokens()){
                  		name = name + st.nextToken();
                  		if(st.hasMoreTokens()) name = name + " ";
               		}
               		MovieDescription aClip = new MovieDescription(name, "unknown", "unknown", "unknown", "unknown",
                            "unknown", new ArrayList<String>(), new ArrayList<String>());
               		boolean result = sc.add(aClip);
               		System.out.println("Add "+aClip.getTitle()+" result "+result);
            	}else if (opn.equalsIgnoreCase("get")) {
               		String name = st.nextToken();
               		while(st.hasMoreTokens()){
                  		name = name + " " + st.nextToken();
               		}
               		MovieDescription result = sc.get(name);
               		System.out.println("Got "+result.toString());
            	}else if (opn.equalsIgnoreCase("getTitles")) {
               		String[] result = sc.getTitles();
               		System.out.print("The collection has entries for the following movies: ");
               		for (int i = 0; i < result.length; i++){
                  		System.out.println(result[i]+", ");
               		}
            	}else if (opn.equalsIgnoreCase("remove")) {
               		String name = st.nextToken();
               		while(st.hasMoreTokens()){
                  		name = name + " " + st.nextToken();
               		}
               		boolean result = sc.remove(name);
               		System.out.println("remove "+name+" result "+result);
            	}
            	System.out.print("Enter end or {add|get|getTitles|remove} followed by args>");
            	inStr = stdin.readLine();
            	st = new StringTokenizer(inStr);
            	opn = st.nextToken();
         	}
      	}catch (Exception e) {
         	e.printStackTrace();
         	System.out.println("Oops, you didn't enter the right stuff");
      	}
   	}
}
