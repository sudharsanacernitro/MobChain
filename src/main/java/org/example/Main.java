package org.example;


import org.example.client.Response;
import org.example.memory.InMemory;
import org.example.messages.HumanMessages;
import org.example.messages.SystemMessages;
import org.example.models.OllamaModel;
import org.example.tools.OwnTools.RAGTool;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {


        InMemory memory = new InMemory();

        OllamaModel model = OllamaModel.build()
                        .baseURL("http://localhost:11434/api/chat")
                        .model("qwen2.5:3b-instruct")
                        .stream( false )
                        .memory( memory )
                        .tools(Arrays.asList( new RAGTool() ))
                        .build();

        memory.setSystemPrompt( new SystemMessages( "you are an usefull AI agri assistant,your name is sandy.....always reply in sentence , no json format") );


        model.chat( new HumanMessages("give weather report for Erode , India."));


        System.out.println(  memory );






    }


}





