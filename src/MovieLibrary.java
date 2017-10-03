import java.util.LinkedList;
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

public class MovieLibrary {
    private List<MovieDescription> library;

    public MovieLibrary(){
        this.library = new LinkedList<MovieDescription>();
    }

    public MovieLibrary(List<MovieDescription> oldLibrary){
        this.library = new LinkedList<MovieDescription>(oldLibrary);
    }

    public boolean add(MovieDescription aClip){
        for(int i = 0; i < library.size(); i++){
            MovieDescription temp = library.get(i);
            if(aClip.getTitle().equals(temp.getTitle())){
                return false;
            }
        }
        return library.add(aClip);
    }

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

    public String[] getTitles(){
        String[] toReturn = new String[library.size()];
        for(int i = 0; i < library.size(); i++){
            MovieDescription temp = library.get(i);
            toReturn[i] = temp.getTitle();
        }
        return toReturn;
    }

}
