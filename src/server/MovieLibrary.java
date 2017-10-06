package javaserver.server;

import edu.asu.ser.jsonrpc.common.JsonRpcException;

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

public interface MovieLibrary {
   public boolean saveToJsonFile() throws JsonRpcException;
   public boolean resetFromJsonFile() throws JsonRpcException;
   public boolean add(MovieDescription stud) throws JsonRpcException;
   public boolean remove(String aName) throws JsonRpcException;
   public MovieDescription get(String aName) throws JsonRpcException;
   public String[] getTitles() throws JsonRpcException;
   public String toJSONString() throws JsonRpcException;
}