package org.example.tools.OwnTools;

import org.json.JSONObject;

public interface Tool {

    public JSONObject runTool(JSONObject args);

    public String getToolName( );


    public String getDescription();

    public JSONObject getStructuredTool();

}
