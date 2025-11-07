package org.example.messages;

import org.json.JSONObject;

public interface Messages {


    public MessageType getRole( );


    public String getContent( );

    public void setContent( String setContent );

    public JSONObject getFunctionCalls( );

    default public String getToolName() {

        return "";

    }

}

