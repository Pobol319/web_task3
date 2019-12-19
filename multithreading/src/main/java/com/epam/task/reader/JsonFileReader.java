package com.epam.task.reader;

import com.epam.task.exception.JsonReaderException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


public class JsonFileReader {
    private Object object;
    private JSONObject jsonObject;

    public JsonFileReader(String path) throws JsonReaderException {
        try {
            object = new JSONParser().parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            throw new JsonReaderException("JsonReaderException!", e);
        }
        jsonObject = (JSONObject) object;
    }

    public String getRestaurantName() {
        return (String) jsonObject.get("restaurantName");
    }

    public Iterator getCashDesksIterator() {
        JSONArray jsonArray = (JSONArray) jsonObject.get("cashDesks");
        return jsonArray.iterator();
    }

    public Iterator getClientsIterator() {
        JSONArray jsonArray = (JSONArray) jsonObject.get("clients");
        return jsonArray.iterator();
    }

}
