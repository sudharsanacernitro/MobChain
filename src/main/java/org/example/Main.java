package org.example;


import org.example.messages.SystemMessages;
import org.example.models.ChatModel;
import org.example.models.OllamaModel;
import org.example.test.OllamaCaller;
import org.example.test.OllamaToolCalling;
import org.json.JSONObject;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {


        OllamaModel model = OllamaModel.build()
                        .baseURL("http://localhost:11434")
                        .model("qwen2.5:3b-instruct")
                        .stream( false )
                        .build();

        model.memory.setSystemPrompt( new SystemMessages( "You are an useful AI assistant" ));


    }


}





