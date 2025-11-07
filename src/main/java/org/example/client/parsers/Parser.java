package org.example.client.parsers;

import org.example.client.Response;
import org.example.models.OllamaModel;
import org.example.tools.OwnTools.Tool;
import org.json.JSONObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Parser {

    public  Response fromJSONString(String jsonString ) ;


    public JSONObject toJSON(Collection<Tool> toolsList , List<JSONObject> memory , OllamaModel model);


    }

