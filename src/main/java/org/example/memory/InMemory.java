package org.example.memory;

import org.example.messages.Messages;
import org.example.messages.SystemMessages;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InMemory {

    private final List<JSONObject> memory;
    private static final int MEMORY_SIZE = 11;

    private static InMemory instance;

    private InMemory() {

        memory = new ArrayList<>();

    }

    public static InMemory getInstance( ) {

        if( instance == null ) instance = new InMemory();

        return instance;
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

        memory.add(obj);

        if (memory.size() > MEMORY_SIZE && memory.size() > 1) {
            memory.remove(1);
        }

    }

    public List<JSONObject> getAllMessages() {

        return new ArrayList<>( memory );

    }

    public void clearMemory() {

        memory.clear();

    }
}
