package com.epam.task.reader;

import com.epam.task.entity.CashDesk;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.google.gson.JsonArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonReader {
    private Object object;
    private JSONObject jsonObject;

    public JsonReader(String path) {
        try {
            object = new JSONParser().parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        jsonObject = (JSONObject) object;
    }

    public String getRestaurantName() {
        return (String) jsonObject.get("restaurantName");
    }

    public Iterator getIteratorCashDesks() {
        JSONArray ja = (JSONArray) jsonObject.get("cashDesks");
        return ja.iterator();
    }

    public Iterator getIteratorClients(){
        JSONArray ja = (JSONArray) jsonObject.get("clients");
        return ja.iterator();
    }

    /*public static void main(String[] args) throws IOException, ParseException {
        Object jsonObject = new JSONParser().parse(new FileReader("src\\main\\resources\\restaurant.json"));
        JSONObject jo = (JSONObject) jsonObject;

        String name = (String)jo.get("restaurantName");
        System.out.println(name);

        *//*Map cashDesks = ((Map)jo.get("cashDesks"));
        Iterator iterator = cashDesks.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry cashDesk = iterator.next();
            System.out.println(cashDesk.getKey() + ":" + cashDesk.getValue());
        }*//*

        JSONArray ja = (JSONArray) jo.get("cashDesks");
        Iterator itr = ja.iterator();
        while (itr.hasNext())
        {
            Iterator<Map.Entry> itr1 = ((Map) itr.next()).entrySet().iterator();
            while (itr1.hasNext()) {
                Map.Entry pair = itr1.next();
                System.out.println(pair.getKey() + " : " + pair.getValue());
            }
        }
    }*/

   /* ObjectMapper mapper = new ObjectMapper();
    File file = new File("src\\test\\resources\\papersImplTest.xml");
    JsonNode rootNode = mapper.readTree(file);
    String s = rootNode.findValue("restaurantName").asText();*/


}
