package javaserver.serialize;

import java.util.Vector;
import java.util.List;
import java.util.Arrays;
import org.json.JSONString;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.FileInputStream;
import java.util.Vector;
import java.util.Enumeration;
import java.io.Serializable;
import java.io.PrintWriter;

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

public class MovieLibrary  extends Object implements JSONString, Serializable {
    private Vector<MovieDescription> library;

    /**
    * No parameter constructor. Just creates an empty Vector.
    */
    public MovieLibrary(){
        this.library = new Vector<MovieDescription>();
    }

    /**
    * Movie Library constructor that takes a list as an argument.
    *
    * @param An old list of movies to initialize the library.
    */
    public MovieLibrary(List<MovieDescription> oldLibrary){
        this.library = new Vector<MovieDescription>(oldLibrary);
    }

    /**
    * Movie Library constructor that gets populated by a json file.
    * 
    * @param The name of the json file.
    */
    public MovieLibrary(String jsonFileName){
        try{
            FileInputStream in = new FileInputStream(jsonFileName);
            JSONObject obj = new JSONObject(new JSONTokener(in));
            String [] names = JSONObject.getNames(obj);
            System.out.print("Movies are: ");
            for(int j=0; j< names.length; j++){
                System.out.print(names[j]+", ");
            }
            System.out.println("");
            this.library = new Vector<MovieDescription>();
            for (int i=0; i< names.length; i++){
                MovieDescription aMovie = new MovieDescription((JSONObject)obj.getJSONObject(names[i]));
                this.library.add(aMovie);
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
    public String toJSONString(){
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
    public boolean add(MovieDescription aClip){
        for(int i = 0; i < library.size(); i++){
            MovieDescription temp = library.get(i);
            if(aClip.getTitle().equals(temp.getTitle())){
                return false;
            }
        }
        return library.add(aClip);
    }

    /**
    * Removes the movie witht he matching title.
    * 
    * @param  The tile of the movie that needs to be removed.
    * @return True if the movie was removed successfully, false if don't.
    */
    public boolean remove(String aTitle){
        for(int i = 0; i < library.size(); i++){
            MovieDescription temp = library.get(i);
            if(aTitle.equals(temp.getTitle())){
                temp = library.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
    * Get the movie that matches the given title.
    * 
    * @param  The title of the movie that needs to be returned.
    * @return The movie that has the matching title.
    */
    public MovieDescription get(String aTitle){
        MovieDescription toReturn =  null;
        for(int i = 0; i < library.size(); i++){
            MovieDescription temp = library.get(i);
            if(aTitle.equals(temp.getTitle())){
                toReturn = temp;
                return toReturn;
            }
        }
        return toReturn;
    }

    /**
    * Imports the contents of the Movie Library from JSON file.
    * 
    * @return True if the library was successfully imported to a JSON file, 
    *          False if not.
    */
    public boolean restoreFromFile(){
        try{
            FileInputStream in = new FileInputStream("movies.json");
            JSONObject obj = new JSONObject(new JSONTokener(in));
            String [] names = JSONObject.getNames(obj);
            for (int i=0; i< names.length; i++){
                MovieDescription aMovie = new MovieDescription((JSONObject)obj.getJSONObject(names[i]));
                System.out.println(aMovie);
                this.library.add(aMovie);
            }
            in.close();
            System.out.println("Done importing group in from movies.json");
            return true;
        }catch (Exception ex) {
            System.out.println("Exception importing from json: "+ex.getMessage());
            return false;
        }
    }

    /**
    * Export the contents of the Movie Library to a JSON file.
    * 
    * @return True if the library was successfully exported to a JSON file, 
    *          False if not.
    */
    public boolean saveToFile(){
        try{
            PrintWriter out = new PrintWriter("movies.json");
            out.println(this.toJSONString());
            out.close();
            System.out.println("Done exporting library to movies.json");
            return true;
        }catch (Exception e){
            System.out.println("Exception exporting from json: "+e.getMessage());
            return false;
        }
    }

    /**
    * This method collects all the the Movie Titles in the library and returns them.
    * 
    * @return An array of strings with all the movie titles in the library.
    */
    public String[] getTitles(){
        String[] toReturn = new String[library.size()];
        for(int i = 0; i < library.size(); i++){
            MovieDescription temp = library.get(i);
            toReturn[i] = temp.getTitle();
        }
        return toReturn;
    }

}
