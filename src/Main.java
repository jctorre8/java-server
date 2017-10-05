package javaserver.serialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 * @version August 2017
 */
public class Main {
    public static void main(String[] args)
    {
        MovieLibrary myLib = new MovieLibrary();
        System.out.println("Working Directory = " +
              System.getProperty("user.dir"));
        File file = new File ("movies.json");
        FileInputStream in = null;
        try{
            in = new FileInputStream(file);
            System.out.println("Loaded file");
        }catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        JSONObject obj = new JSONObject(new JSONTokener(in));
        String [] names = JSONObject.getNames(obj);
        try{
            in.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        

        String input = "";
        Scanner scanner = new Scanner( System.in );
        boolean result;
        int found = 0;

        while(!input.equalsIgnoreCase("end")){
        
            System.out.println("Please select an option: ");
            System.out.println(" 1) Search a Title");
            System.out.println(" 2) Save");
            System.out.println(" 3) Restore");
            System.out.println(" 4) End");
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("Search a Title")){
                System.out.println("Please input the title to search: ");
                String title = scanner.nextLine();
                System.out.println("Searching for Movie: " +title);
                for(int i = 0; i < names.length; i++){
                    if(title.equalsIgnoreCase(names[i])){
                        System.out.println("Movie "+names[i]+" was found in database.");
                        System.out.println("Adding movie "+names[i]+" to the library.");
                        MovieDescription newMovie = new MovieDescription(obj.getJSONObject(names[i]));
                        myLib.add(newMovie);
                        found = 1;
                        break;
                    }                    
                }
                if(found == 0){
                        System.out.println("Movie " +title + " was not found!!!\n");
                }
                found =0;
            }
            else if (input.equalsIgnoreCase("Save")) {
                result = myLib.saveToFile();               
            }
            else if (input.equalsIgnoreCase("Restore")) {
                result = myLib.restoreFromFile();                
            }
            else if (input.equalsIgnoreCase("end")) {
                continue;                
            }
            else{
                System.out.println("Please type one of the options!");
            }

        }
        return;
    }
}
