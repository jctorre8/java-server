package javaserver.server;

import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

import edu.asu.ser.jsonrpc.common.PositionalParams;
import edu.asu.ser.jsonrpc.common.JsonRequest;
import edu.asu.ser.jsonrpc.common.JsonRpcException;
import edu.asu.ser.jsonrpc.client.TCPClient;
import edu.asu.ser.jsonrpc.client.HttpClient;

import javaserver.server.MovieDescription;

public class MovieDescriptionHttpProxy extends HttpClient implements MovieLibrary { 

  private static int id = 0;

  public MovieDescriptionHttpProxy(URL url) {
    super(url);
  }

  public boolean add(MovieDescription param0) throws JsonRpcException {
    JsonRequest req = new JsonRequest(); 
    JSONArray arr = new JSONArray();
    JSONObject obj = param0.toJson();
    arr.put(obj);
    PositionalParams params = new PositionalParams(arr); 
    req.setParams(params); 
    req.setMethod("add"); 
    req.setId(id++);
    Object result = sendRequest(req.toString());
    return (boolean)result;
  }
  public boolean remove(String param0) throws JsonRpcException {
    JsonRequest req = new JsonRequest(); 
    JSONArray arr = new JSONArray();
    arr.put(param0);
    PositionalParams params = new PositionalParams(arr); 
    req.setParams(params); 
    req.setMethod("remove"); 
    req.setId(id++);
    Object result = sendRequest(req.toString());
    return (boolean)result;
  }
  public MovieDescription get(String param0) throws JsonRpcException {
    JsonRequest req = new JsonRequest(); 
    JSONArray arr = new JSONArray();
    arr.put(param0);
    PositionalParams params = new PositionalParams(arr); 
    req.setParams(params); 
    req.setMethod("get"); 
    req.setId(id++);
    Object result = sendRequest(req.toString());
    return new MovieDescription((JSONObject)result);
  }

  public boolean saveToJsonFile() throws JsonRpcException {
    JsonRequest req = new JsonRequest(); 
    JSONArray arr = new JSONArray(); 
    PositionalParams params = new PositionalParams(arr); 
    req.setParams(params);
    req.setMethod("saveToJsonFile"); 
    req.setId(id++);
    Object result = sendRequest(req.toString());
    return (boolean)result;
  }
  public boolean resetFromJsonFile() throws JsonRpcException {
    JsonRequest req = new JsonRequest(); 
    JSONArray arr = new JSONArray(); 
    PositionalParams params = new PositionalParams(arr); 
    req.setParams(params);
    req.setMethod("resetFromJsonFile"); 
    req.setId(id++);
    Object result = sendRequest(req.toString());
    return (boolean)result;
  }
  public String[] getTitles() throws JsonRpcException {
    JsonRequest req = new JsonRequest(); 
    JSONArray arr = new JSONArray(); 
    PositionalParams params = new PositionalParams(arr); 
    req.setParams(params);
    req.setMethod("getTitles"); 
    req.setId(id++);
    Object result = sendRequest(req.toString());
    ArrayList<String> al = new ArrayList<String>();
    JSONArray ja = (JSONArray)result;
    for (int i=0; i< ja.length(); i++){
      al.add((String)ja.getString(i));
    }
    return (String[])al.toArray(new String[0]);
  }

  public String toJSONString() throws JsonRpcException {
    JsonRequest req = new JsonRequest(); 
    JSONArray arr = new JSONArray(); 
    PositionalParams params = new PositionalParams(arr); 
    req.setParams(params);
    req.setMethod("toJSONString"); 
    req.setId(id++);
    Object result = sendRequest(req.toString());
    return (String)result;
  }}