import java.util.ArrayList;
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
public class Main {
    public static void main(String[] args)
    {
        MovieLibrary myLib = new MovieLibrary();
        String title = "Movie Minions Banana Song";
        String rated = "NR";
        String released = "12 Dec 2015";
        String runtime = "3 min";
        String plot = "  Banana is a song sung by The Minions in the teaser trailer of Despicable Me 2. It is\n" +
                "  a parody of the Beach Boys Barbara Ann. One minion gets annoyed by another, most likely Stuart,\n" +
                "  who keeps on plaaying his party horn while they are singing. So, at the end, he punches Stuart.";
        String filename = "MinionsBananaSong.mp4";
        List<String> genreList = new ArrayList<String>();
        genreList.add("Terror");
        genreList.add("Comedy");
        List<String> actorsList = new ArrayList<String>();
        actorsList.add("Minion 1");
        actorsList.add("Minion 2");
        MovieDescription movie1 = new MovieDescription(title, rated, released, runtime, plot,
                filename, genreList, actorsList);

        title = "Movie Minions Puppy";
        rated = "NR";
        released = "10 Dec 2015";
        runtime = "4:16 min";
        plot = "  Dave seeing many owners walk their dogs wants a puppy of his own. He finds a mini-UFO who becomes\n" +
                "  his pal. This short film released with Despicable Me 2 chronicles how Dave helps the \n" +
                "  UFO return home.";
        filename = "MinionsBananaSong.mp4";
        MovieDescription movie2 = new MovieDescription(title, rated, released, runtime, plot,
                filename, genreList, actorsList);

        boolean test = myLib.add(movie1);
        MovieDescription temp;
        if(test){
            System.out.print("Added: " + movie1 + "\n");
        }

        test = myLib.add(movie2);
        if(test){
            System.out.print("Added: " + movie2 + "\n");
        }

        temp = myLib.get(movie2.getTitle());
        if(temp.getTitle().equals(movie2.getTitle())){
            System.out.print("Tested the get method to get movie: " + movie2 + "\n");
        }

        System.out.println("After adding two movies, the library contains the titles:");
        String [] movieTitles = myLib.getTitles();
        for(int i = 0; i < movieTitles.length; i++){
            if(i == movieTitles.length-1){
                System.out.print(movieTitles[i] + "\n");
                continue;
            }
            System.out.print(movieTitles[i] + ", ");
        }


        myLib.remove(movie1.getTitle());
        String [] movieTitlesAfter = myLib.getTitles();
        System.out.println("\nMovie titles after removing " + movie1.getTitle() + " the titles are:");
        for(int i = 0; i < movieTitlesAfter.length; i++){
            if(i == movieTitlesAfter.length-1){
                System.out.print(movieTitlesAfter[i] + "\n");
                continue;
            }
            System.out.print(movieTitlesAfter[i]);
        }
    }
}
