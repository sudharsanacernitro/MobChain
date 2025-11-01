package org.example.messages;

public class AiMessages implements Messages {

    private MessageType messageType = MessageType.AI_MESSAGES;
    private String content;

    //want to  selected tool


    public AiMessages( String content ) {

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


}
