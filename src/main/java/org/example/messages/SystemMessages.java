package org.example.messages;


public class SystemMessages implements Messages {

    private final MessageType type = MessageType.SYSTEM_MESSAGES ;
    private String content;


    public SystemMessages( String content ) {

        this.content = content;

    }

    @Override
    public MessageType getRole() {

        return this.type;

    }

    @Override
    public String getContent( ) {

        return this.content;

    }

    @Override
    public void setContent( String content ) {

        this.content = content;

    }




}
