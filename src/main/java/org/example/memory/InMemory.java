package org.example.memory;

import org.example.messages.Messages;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InMemory {

    private final List<JSONObject> memory;
    private static final int MEMORY_SIZE = 11;


    public  InMemory() {

        memory = new ArrayList<>();

    }



    public void setSystemPrompt( Messages systemPrompt) {

        JSONObject systemObject = new JSONObject();
        systemObject.put("role", systemPrompt.getRole().getType());
        systemObject.put("content", systemPrompt.getContent());

        if (!memory.isEmpty() && memory.get(0).optString("role").equals("system")) {

            memory.set(0, systemObject);

        } else {

            memory.add(0, systemObject);

        }

    }

    public void addHumanMessage( Messages humanMessage) {

        JSONObject obj = new JSONObject();
        obj.put("role", humanMessage.getRole().getType());
        obj.put("content", humanMessage.getContent());

        memory.add(obj);

    }

    public void addAiMessage(Messages aiMessage) {

        JSONObject obj = new JSONObject();
        obj.put("role", aiMessage.getRole().getType());
        obj.put("content", aiMessage.getContent());
        obj.put("function_calls" , aiMessage.getFunctionCalls() );

        memory.add(obj);

        if (memory.size() > MEMORY_SIZE && memory.size() > 1) {
            memory.remove(1);
        }

    }

    public void addAiMessage( JSONObject aiMessage ) {

        memory.add( aiMessage );

        if (memory.size() > MEMORY_SIZE && memory.size() > 1) {

            memory.remove(1);

        }

    }

    public void addToolMessage( Messages toolMessage ) {


        JSONObject obj = new JSONObject();

        obj.put( "role" , toolMessage.getRole().getType() );
        obj.put("name" , toolMessage.getToolName());
        obj.put( "content" , toolMessage.getFunctionCalls().toString() );

        memory.add( obj );

    }

    public List<JSONObject> getAllMessages() {

        return new ArrayList<>( memory );

    }

    public void clearMemory() {

        memory.clear();

    }

    @Override
    public String toString( ) {

        System.out.println( memory );

        return null;
    }
}
