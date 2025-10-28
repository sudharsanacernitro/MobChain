package org.example.messages;

public enum MessageType {

    SYSTEM_MESSAGES("system" , 10 ) , HUMAN_MESSAGES( "user" , 5 ) , AI_MESSAGES( "assistant" , 2) ;

    private String type;
    private int priority;


    MessageType( String type , int priority ) {

        this.type = type;
        this.priority = priority;

    }

    public String getType( ) {

        return this.type;

    }

    public int getPriority( ) {

        return this.priority;

    }

    public static MessageType getUserTypeFromString( String type ) throws TypeNotPresentException {

        for( MessageType messageType : MessageType.values() ) {

            if( messageType.getType().equals( type )) {

                return messageType;

            }

        }

        throw new TypeNotPresentException( type , null );

    }

}
