package org.example.client.parsers;

import org.example.client.Response;
import org.example.models.OllamaModel;
import org.example.tools.OwnTools.Tool;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class QwenParser implements Parser {


    @Override
    public  Response fromJSONString( String jsonString ) {

        JSONObject root = new JSONObject(jsonString);
        JSONObject message = root.getJSONObject("message");

        String content = message.getString( "content" );

        JSONArray toolCalls = message.getJSONArray("tool_calls");


        List< Response.Function > functionObjects = new ArrayList<>();



        for( int index = 0 ; index < toolCalls.length() ; index++ ) {

            JSONObject toolCall = toolCalls.getJSONObject(index);
            JSONObject function = toolCall.getJSONObject("function");

            JSONObject arguments = function.getJSONObject("arguments");
            String functionName = function.getString( "name" );

            Response.Function functionObject = new Response.Function( functionName , arguments );

            functionObjects.add( functionObject );

        }

        return new Response( content , functionObjects , message );

    }


    public  static JSONObject createFunctionTool(

            String functionName,
            String description,
            Map<String, List<String>> parameters,
            String[] requiredParams

    ) {

        JSONObject properties = new JSONObject();

        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            String paramName = entry.getKey();
            List<String> values = entry.getValue();

            String paramType = values.size() > 0 ? values.get(0) : "string";
            String paramDescription = values.size() > 1 ? values.get(1) : "No description provided";

            JSONObject paramObject = new JSONObject()
                    .put("type", paramType)
                    .put("description", paramDescription);

            properties.put(paramName, paramObject);
        }


        JSONObject parametersObject = new JSONObject()
                .put("type", "object")
                .put("required", new JSONArray(requiredParams))
                .put("properties", properties);

        JSONObject functionObject = new JSONObject()
                .put("name", functionName)
                .put("description", description)
                .put("parameters", parametersObject);


        return new JSONObject()
                .put("type", "function")
                .put("function", functionObject);


    }



    @Override
    public JSONObject toJSON(Collection<Tool> toolsList , List<JSONObject> memory , OllamaModel model) {

        JSONObject[] toolArray = toolsList.stream()
                .map(tool -> {

                    return tool.getStructuredTool();

                })
                .toArray(JSONObject[]::new);


        JSONObject[] memoryArray = memory.stream()
                .toArray(JSONObject[]::new);

        boolean isStream = model.isStream();

        String modelName = model.getModel();



        JSONObject jsonOutput = new JSONObject();

        jsonOutput.put("model" , modelName );
        jsonOutput.put( "messages" , memoryArray );
        jsonOutput.put( "stream" , isStream );
        jsonOutput.put( "tools" , toolArray );


        return jsonOutput;

    }




}
