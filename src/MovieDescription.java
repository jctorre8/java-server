package javaserver.serialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONString;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

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

public class MovieDescription extends Object implements Serializable {
    private String title;
    private String rated;
    private String released;
    private String runtime;
    private String plot;
    private String filename;
    private List<String> genreList;
    private List<String> actorsList;

    public MovieDescription(String title, String rated, String released, String runtime, String plot,
                            String filename, List<String> genreList, List<String> actorsList){

        this.title = title;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.plot = plot;
        this.filename = filename;
        this.genreList = new ArrayList<String>(genreList);
        this.actorsList = new ArrayList<String>(actorsList);
    }
    public MovieDescription(MovieDescription copy){

        this.title = copy.getTitle();
        this.rated = copy.getRated();
        this.released = copy.getReleased();
        this.runtime = copy.getRuntime();
        this.plot = copy.getPlot();
        this.filename = copy.filename;
        this.genreList = new ArrayList<String>(copy.getGenreList());
        this.actorsList = new ArrayList<String>(copy.getActorsList());
    }
    public MovieDescription(JSONObject obj){
        this.title = obj.getString("Title");
        this.rated = obj.getString("Rated");
        this.released = obj.getString("Released");
        this.runtime = obj.getString("Runtime");
        this.plot = obj.getString("Plot");
        this.filename = obj.getString("Filename");
        this.genreList = new ArrayList<String>();
        JSONArray genreArray = obj.getJSONArray("Genre"); 
        if (genreArray != null) { 
            for (int i=0;i<genreArray.length();i++){ 
                genreList.add(genreArray.getString(i));
            } 
        } 
        this.actorsList = new ArrayList<String>();
        JSONArray actorArray = obj.getJSONArray("Actors"); 
        if (genreArray != null) { 
            for (int i=0;i<actorArray.length();i++){ 
                actorsList.add(actorArray.getString(i));
            } 
        } 
    }

    public MovieDescription(String jsonObjAsString){
        JSONObject obj = new JSONObject(new JSONTokener(jsonObjAsString));
        this.title = obj.getString("Title");
        this.rated = obj.getString("Rated");
        this.released = obj.getString("Released");
        this.runtime = obj.getString("Runtime");
        this.plot = obj.getString("Plot");
        this.filename = obj.getString("Filename");
        this.genreList = new ArrayList<String>();
        JSONArray genreArray = obj.getJSONArray("Genre"); 
        if (genreArray != null) { 
            for (int i=0;i<genreArray.length();i++){ 
                genreList.add(genreArray.getString(i));
            } 
        } 
        this.actorsList = new ArrayList<String>();
        JSONArray actorArray = obj.getJSONArray("Actors"); 
        if (genreArray != null) { 
            for (int i=0;i<actorArray.length();i++){ 
                actorsList.add(actorArray.getString(i));
            } 
        } 
    }

    /**
    * Title getter. 
    * @return The Title of the movie.
    */
    public String getTitle(){
        return this.title;
    }

    /**
    * Title setter. 
    * @param The new title of the movie.
    */
    public void setTitle(String title){
        this.title = title;
    }

    /**
    * Rating getter. 
    * @return The rating of the movie.
    */
    public String getRated(){
        return this.rated;
    }

    /**
    * Rating setter. 
    * @param The new rating of the movie.
    */
    public void setRated(String rated){
        this.rated = rated;
    }

    /**
    * Release date getter. 
    * @return The release date of the movie.
    */
    public String getReleased(){
        return this.released;
    }

    /**
    * Release setter. 
    * @param The new release date of the movie.
    */
    public void setReleased(String released){
        this.released = released;
    }

    /**
    * Runtime getter. 
    * @return The runtime of the movie.
    */
    public String getRuntime(){
        return this.runtime;
    }

    /**
    * Runtime setter. 
    * @param The new runtime of the movie.
    */
    public void setRuntime(String runtime){
        this.runtime = runtime;
    }

    /**
    * Plot getter. 
    * @return The plot of the movie.
    */
    public String getPlot(){
        return this.plot;
    }

    /**
    * Plot setter. 
    * @param The new plot of the movie.
    */
    public void setPlot(String plot){
        this.plot = plot;
    }

    /**
    * Filename getter. 
    * @return The filename of the movie.
    */
    public String getFilename(){
        return this.filename;
    }

    /**
    * Filename setter. 
    * @param The new filename of the movie.
    */
    public void setFilename(String filename){
        this.filename = filename;
    }

    /**
    * Actor adder. 
    * @param The new actor of the movie.
    */
    public void setActorsList(List<String> actorsList) {
        if (genreList == null) {
            throw new NullPointerException("actorsList must not be null");
        }
        this.actorsList = actorsList;
    }

    
    /**
    * Actor adder. 
    * @param The new actor of the movie.
    */
    public void addActor(String actor) {
        this.actorsList.add(actor);
    }

    /**
    * Actor adder. 
    * @param The new actor of the movie.
    */
    public void addActors(Collection<String> actors) {
        this.actorsList.addAll(actors);
    }

    /**
    * Actor List getter. 
    * @return The list with all the actors that the movie has.
    */
    public List<String> getActorsList() {
        return Collections.unmodifiableList(this.actorsList);
    }

    /**
    * Genre adder. 
    * @param The new Genre of the movie.
    */
    public void setGenreList(List<String> genreList) {
        if (genreList == null) {
            throw new NullPointerException("genreList must not be null");
        }
        this.genreList = genreList;
    }

    /**
    * Genre adder. 
    * @param The new Genre of the movie.
    */
    public void addGenre(String genre) {
        this.genreList.add(genre);
    }

    /**
    * Genre adder. 
    * @param The new Genre of the movie.
    */
    public void addGernes(Collection<String> genres) {
        this.genreList.addAll(genres);
    }

    /**
    * Genre List getter. 
    * @return The list with all the genres that the movie belongs.
    */
    public List<String> getGenreList() {
        return Collections.unmodifiableList(this.genreList);
    }

    /**
    * Export the movie information into a JSONObject.
    * 
    * @return A JSONObject with all the Movie information.
    */
    public JSONObject toJSONObject(){
        JSONObject obj = new JSONObject();
        obj.put("Title",this.title);
        obj.put("Rated",this.rated);
        obj.put("Released",this.released);
        obj.put("Runtime",this.runtime);
        obj.put("Plot",this.plot);
        obj.put("Filename",this.filename);
        obj.put("Genre",this.genreList);
        obj.put("Actors",this.actorsList);
        return obj;
    }

    /**
    * Returns a String representation of the Movie Description.
    * 
    * @return A string with all the information.
    */
    public String toString(){
        String toReturn = "";
        toReturn += this.title + "\n";
        toReturn += "Rated " + this.rated + "\n";
        toReturn += "Released: " + this.released + "\n";
        toReturn += "Runtime " + this.runtime + "\n";
        toReturn += "Plot: " + this.plot + "\n";
        toReturn += "Filename: " + this.filename + "\n";
        toReturn += "Actors: ";
        for(int i = 0; i < actorsList.size(); i++){
            if(i == actorsList.size()-1){
                toReturn += actorsList.get(i);
                continue;
            }
            toReturn += actorsList.get(i) + ", ";
        }
        toReturn += "\nGenre: ";
        for(int i = 0; i < genreList.size(); i++){
            if(i == genreList.size()-1){
                toReturn += genreList.get(i);
                continue;
            }
            toReturn += genreList.get(i) + ", ";
        }
        toReturn += "\n";
        return toReturn;
    }
}
