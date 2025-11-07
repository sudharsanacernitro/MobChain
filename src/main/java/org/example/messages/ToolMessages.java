package org.example.messages;

import org.example.tools.OwnTools.Tool;
import org.json.JSONObject;

public class ToolMessages  implements Messages{


    private MessageType role = MessageType.TOOL_MESSAGES;
    private String toolName;
    private JSONObject toolMessage;

    public ToolMessages(String toolName, JSONObject content) {

        this.toolName = toolName;
        this.toolMessage = content;

    }

    public MessageType getRole() {
        return role;
    }



    public JSONObject getFunctionCalls() {
        return toolMessage;
    }

    public String getToolName( ) {

        return this.toolName;

    }

    public String getContent( ) {

        return "";

    }

    public void setContent( String setContent ) {

    }



}
