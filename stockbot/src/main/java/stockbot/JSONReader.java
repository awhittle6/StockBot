package stockbot;


import java.util.LinkedList;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONReader {
    public LinkedList<String> valueList = new LinkedList<>();
    public JSONReader(String jsonPath){
        JSONParser jsonParser = new JSONParser();
        System.out.println(System.getProperty("user.dir"));
        try {
            FileReader reader = new FileReader(jsonPath);
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;
            for (Object arrayObject : jsonArray){ 
                JSONObject jsonObject = (JSONObject) arrayObject; 
                parseWebsites(jsonObject); 
            } 
        } catch (FileNotFoundException e) { 
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    private void parseWebsites(JSONObject primaryKeyObject){
        if (primaryKeyObject == null){
            throw new IllegalArgumentException();
        }
        JSONObject innerObject = (JSONObject) primaryKeyObject.get("website");
        Set<String> keySet = innerObject.keySet();
        for (String key : keySet){
            String value = (String) innerObject.get(key);
            this.valueList.addFirst(value);
        }
    }
}
