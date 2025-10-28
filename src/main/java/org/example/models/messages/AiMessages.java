package org.example.models.messages;

import jdk.jfr.Experimental;

public class AiMessages implements Messages {

    private MessageType messageType = MessageType.AI_MESSAGES;
    private String modelName;
    List<Tool> 



    public MessageType getType( ) {

        return this.messageType;

    }

}
