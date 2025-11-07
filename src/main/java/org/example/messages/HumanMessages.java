package org.example.messages;

import org.json.JSONObject;

public class HumanMessages implements Messages {


    private MessageType messageType = MessageType.HUMAN_MESSAGES;
    private String content;

    //want to declare tools specifications


    public HumanMessages( String content ) {

        this.content = content;

    }

    @Override
    public MessageType getRole( ) {

        return this.messageType;

    }

    @Override
    public String getContent() {

        return this.content;

    }

    @Override
    public void setContent( String content ) {

        this.content = content;

    }

    @Override
    public JSONObject getFunctionCalls() {
        return null;
    }


}
