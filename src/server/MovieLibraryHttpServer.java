package javaserver.server;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Enumeration;

import org.json.JSONObject;
import org.json.JSONTokener;

import edu.asu.ser.jsonrpc.common.JsonRpcException;
import edu.asu.ser.jsonrpc.server.HttpServer;

import java.io.IOException;

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
 * @version October 2017
 */

public class MovieLibraryHttpServer  extends Object implements MovieLibrary{
    private Hashtable<String,MovieDescription> library;
    private static final boolean debugOn = false;
   private static final String moviesJsonFileName = "movies.json";

    /**
    * No parameter constructor. Just creates an empty Vector.
    */
    public MovieLibraryHttpServer(){
        debug("creating a new student collection");
        this.library = new Hashtable<String,MovieDescription>();
        try{
            this.resetFromJsonFile();
        }catch(Exception ex){
            System.out.println("error resetting from movies json file"+ex.getMessage());
        } 
    }

    /**
    * Movie Library constructor that takes a list as an argument.
    *
    * @param An old list of movies to initialize the library.
    */
    public MovieLibraryHttpServer(Hashtable<String,MovieDescription> oldLibrary){
        this.library = new Hashtable<String,MovieDescription>(oldLibrary);
    }

    private void debug(String message) {
        if (debugOn)
            System.out.println("debug: "+message);
    }

    /**
    * Movie Library constructor that gets populated by a json file.
    * 
    * @param The name of the json file.
    */
    public MovieLibraryHttpServer(String jsonFileName){
        try{
            FileInputStream in = new FileInputStream(jsonFileName);
            JSONObject obj = new JSONObject(new JSONTokener(in));
            String [] names = JSONObject.getNames(obj);
            System.out.print("Movies are: ");
            for(int j=0; j< names.length; j++){
                System.out.print(names[j]+", ");
            }
            System.out.println("");
            this.library = new Hashtable<String,MovieDescription>();
            for (int i=0; i< names.length; i++){
                MovieDescription aMovie = new MovieDescription((JSONObject)obj.getJSONObject(names[i]));
                this.library.put(aMovie.getTitle(),aMovie);
            }
            in.close();
        }catch (Exception ex) {
            System.out.println("Exception importing from json: "+ex.getMessage());
        }
    }

    /**
    * Outputs the content of the library into a string representation of json.
    * 
    * @return The string json content of the library.
    */
    public String toJSONString() throws JsonRpcException {
        String ret;
        JSONObject obj = new JSONObject();
        for (Enumeration<MovieDescription> e = this.library.elements(); e.hasMoreElements();){
            MovieDescription movie = (MovieDescription)e.nextElement();
            obj.put(movie.getTitle(),movie.toJSONObject());
        }
        ret = obj.toString();
        return ret;
    }

    /**
    * Adds a movie object to the library.
    * 
    * @param A MovieDescription object to be added to the library
    * @return True if successful and false if don't.
    */
    public boolean add(MovieDescription aClip) throws JsonRpcException {
        boolean toReturn = true;
        debug("adding movie titled: "+((aClip==null)?"unknown":aClip.getTitle()));
        try{
         library.put(aClip.getTitle(),aClip);
        }catch(Exception ex){
            toReturn = false;
        }
        return toReturn;
    }

    /**
    * Removes the movie witht he matching title.
    * 
    * @param  The tile of the movie that needs to be removed.
    * @return True if the movie was removed successfully, false if don't.
    */
    public boolean remove(String aTitle) throws JsonRpcException {
        debug("removing movie titled: "+aTitle);
      return ((library.remove(aTitle)==null)?false:true);
    }

    /**
    * Get the movie that matches the given title.
    * 
    * @param  The title of the movie that needs to be returned.
    * @return The movie that has the matching title.
    */
    public MovieDescription get(String aTitle) throws JsonRpcException {
        MovieDescription toReturn =  new MovieDescription("unknown", "unknown", "unknown", "unknown", "unknown",
                            "unknown", new ArrayList<String>(), new ArrayList<String>());
        MovieDescription temp = library.get(aTitle);
        if (temp != null) {
            toReturn = temp;
        }
        return toReturn;
    }

    /**
    * Imports the contents of the Movie Library from JSON file.
    * 
    * @return True if the library was successfully imported to a JSON file, 
    *          False if not.
    */
    public boolean resetFromJsonFile() throws JsonRpcException {
        boolean result = true;
        try{
            library.clear();
            File f = new File(moviesJsonFileName);
            FileInputStream in = new FileInputStream(f);
            JSONObject obj = new JSONObject(new JSONTokener(in));
            String [] names = JSONObject.getNames(obj);
            for (int i=0; i< names.length; i++){
                MovieDescription aMovie = new MovieDescription((JSONObject)obj.getJSONObject(names[i]));
                this.library.put(names[i], aMovie);
                debug("added "+names[i]+" : "+aMovie.toJSONString()+
                  "\nlibrary.size() is: " + library.size());
            }
            in.close();
            System.out.println("Done importing group in from movies.json");
            
        }catch (Exception ex) {
            System.out.println("Exception importing from json: "+ex.getMessage());
            return false;
        }

        return result;
    }

    /**
    * Export the contents of the Movie Library to a JSON file.
    * 
    * @return True if the library was successfully exported to a JSON file, 
    *          False if not.
    */
    public boolean saveToJsonFile() throws JsonRpcException {
        boolean result = true;
        try{
            PrintWriter out = new PrintWriter(moviesJsonFileName);
            out.println(this.toJSONString());
            out.close();
            System.out.println("Done exporting library to movies.json");
            
        }catch (Exception e){
            return false;
        }
        return result;
    }

    /**
    * This method collects all the the Movie Titles in the library and returns them.
    * 
    * @return An array of strings with all the movie titles in the library.
    */
    public String[] getTitles() throws JsonRpcException {
        String[] toReturn = {};
        debug("getting "+library.size()+" movie titles.");
        if(library.size()>0){
            toReturn = (String[])(library.keySet()).toArray(new String[0]);
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        String port = "8080";
        if (args.length > 0) {
            port = args[0];
        }
        HttpServer serv = new HttpServer(
            new MovieLibraryHttpServer(),Integer.parseInt(port));
        serv.start();
    }

}
