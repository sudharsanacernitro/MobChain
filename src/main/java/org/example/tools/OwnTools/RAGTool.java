package org.example.tools.OwnTools;

import org.example.tools.StructuredOllamaTool;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RAGTool implements Tool{


    public static final String toolName = "rag_tool";
    public static final String description = "Tool to give weather report for a city";


    private static  JSONObject structuredTool;


    static {

        Map<String, List<String>> parameters = new HashMap<>();
        parameters.put("city", Arrays.asList("string", "city mentioned in the input"));
        parameters.put("country", Arrays.asList("string", "country mentioned in the input"));

        structuredTool = StructuredOllamaTool.createFunctionTool(

                toolName,
                description,
                parameters,
                parameters.keySet().toArray(new String[0])

        );

    }


    @Override
    public String getToolName( ) {

        return toolName;

    }


    @Override
    public String getDescription( ) {

        return description;

    }

    @Override
    public JSONObject getStructuredTool( ) {

        return structuredTool;

    }


    @Override
    public JSONObject runTool( JSONObject args ) {

        if( ! args.has("city") || ! args.has( "country" ) ) {

            throw new IllegalArgumentException("The inputs 'city' and 'country' is not present");

        }

        String city = args.getString("city");
        String country = args.getString("country");

        JSONObject res = new JSONObject();
        res.put("weather" , "it seems to be rainy.");

        return res;

    }

}
