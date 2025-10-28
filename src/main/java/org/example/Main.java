package org.example;


import org.example.test.OllamaCaller;
import org.example.test.OllamaToolCalling;
import org.json.JSONObject;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {


        OllamaCaller.callOllama();

        OllamaToolCalling.call();
    }


}



class RAGTool {

    public static final String description = "Methode to retrieve data from records";

    public JSONObject getArg() {

        JSONObject arg = new JSONObject();

        arg.put("name"  , Arrays.asList("String" , "name of the user"));

        return arg;

    }


    public String getDetails() {

        return "";
    }

}

