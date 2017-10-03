import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

public class MovieDescription {
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

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getRated(){
        return this.rated;
    }

    public void setRated(String rated){
        this.rated = rated;
    }

    public String getReleased(){
        return this.released;
    }

    public void setReleased(String released){
        this.released = released;
    }

    public String getRuntime(){
        return this.runtime;
    }

    public void setRuntime(String runtime){
        this.runtime = runtime;
    }

    public String getPlot(){
        return this.plot;
    }

    public void setPlot(String plot){
        this.plot = plot;
    }

    public String getFilename(){
        return this.filename;
    }

    public void setFilename(String filename){
        this.filename = filename;
    }

    public void setActorsList(List<String> actorsList) {
        if (genreList == null) {
            throw new NullPointerException("actorsList must not be null");
        }
        this.actorsList = actorsList;
    }

    public void addActor(String actor) {
        this.actorsList.add(actor);
    }

    public void addActors(Collection<String> actors) {
        this.actorsList.addAll(actors);
    }

    public List<String> getActorsList() {
        return Collections.unmodifiableList(this.actorsList);
    }

    public void setGenreList(List<String> genreList) {
        if (genreList == null) {
            throw new NullPointerException("genreList must not be null");
        }
        this.genreList = genreList;
    }

    public void addGenre(String genre) {
        this.genreList.add(genre);
    }

    public void addGernes(Collection<String> genres) {
        this.genreList.addAll(genres);
    }

    public List<String> getGenreList() {
        return Collections.unmodifiableList(this.genreList);
    }

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
